package com.prestongarno.ktq.compiler

import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.QUnionType
import com.prestongarno.ktq.org.antlr4.gen.GraphQLSchemaParser
import com.prestongarno.ktq.stubs.BooleanArrayDelegate
import com.prestongarno.ktq.stubs.BooleanDelegate
import com.prestongarno.ktq.stubs.FloatArrayDelegate
import com.prestongarno.ktq.stubs.FloatDelegate
import com.prestongarno.ktq.stubs.IntArrayDelegate
import com.prestongarno.ktq.stubs.IntDelegate
import com.prestongarno.ktq.stubs.ScalarArrayDelegate
import com.prestongarno.ktq.stubs.ScalarDelegate
import com.prestongarno.ktq.stubs.StringArrayDelegate
import com.prestongarno.ktq.stubs.StringDelegate
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import org.antlr.v4.runtime.ParserRuleContext
import kotlin.reflect.KClass

sealed class SchemaType<out T : ParserRuleContext>(val context: T) : KotlinTypeElement, NamedElement {

  abstract val schemaTypeClass: KClass<out QSchemaType>
  // the delegate stub creator objects which you invoke method for the right delegate field on
  abstract val delegateStubClass: KClass<*>
  abstract val delegateListStubClass: KClass<*>

  // this is literally hundreds of lines simpler than the last one
  open fun getStubDelegationCall(field: FieldDefinition): CodeBlock = when {
    field.arguments.isEmpty() -> "stub<%T>()" to listOf(name)
    field.arguments.isNotEmpty() && field.arguments.find { !it.nullable } == null ->
      "optionalConfigStub<%T, %T>()" to listOf(name, field.argBuilder!!.name)
    else -> "configStub<%T, %T>()" to listOf(name, field.argBuilder!!.name)
  }.let { (format, typeNames) ->
    CodeBlock.of("%T.$format", *stubFor(field, typeNames).toTypedArray())
  }
}

sealed class ScopedDeclarationType<out T : ParserRuleContext>(context: T) : SchemaType<T>(context) {
  abstract val fields: Set<FieldDefinition>

  val symtab by lazy {
    fields.map { it.name to it }.toMap()
  }
}

class TypeDef(context: GraphQLSchemaParser.TypeDefContext)
  : ScopedDeclarationType<GraphQLSchemaParser.TypeDefContext>(context) {

  override val name: String = context.typeName().Name().text

  override val fields = context.fieldDef().map { FieldDefinition(it) }.toSet()

  lateinit var supertypes: Set<InterfaceDef>

  override fun toKotlin(): TypeSpec = TypeSpec.objectBuilder(name).apply {
    addSuperinterface(schemaTypeClass)
    addProperties(fields.map { it.toKotlin() })
    addSuperinterfaces(supertypes.map {
      it.name.asTypeName()
    })
    addTypes(fields.mapNotNull {
      it.argBuilder?.toKotlin()
    })
  }.build()

  override val schemaTypeClass = QType::class
  override val delegateStubClass = QSchemaType.QTypes::class
  override val delegateListStubClass = QSchemaType.QTypes.List::class
}

class InterfaceDef(context: GraphQLSchemaParser.InterfaceDefContext)
  : ScopedDeclarationType<GraphQLSchemaParser.InterfaceDefContext>(context) {
  override val name: String = context.typeName().Name().text

  override val fields = context.fieldDef().map(::FieldDefinition).onEach {
    it.isAbstract = true // flag as abstract
    it.inheritsFrom = emptySet() // initialize lateinit var
  }.toSet()

  override fun toKotlin(): TypeSpec = TypeSpec.interfaceBuilder(name).apply {
    // interface needs to subtype QType *&* QInterface for interface fragment stubs
    addSuperinterface(QType::class)
    addSuperinterface(schemaTypeClass)
    addProperties(fields.map { it.toKotlin() })
  }.build()

  override val schemaTypeClass = QInterface::class
  override val delegateStubClass: KClass<*> = QSchemaType.QInterfaces::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QInterfaces.List::class

}

class UnionDef(context: GraphQLSchemaParser.UnionDefContext)
  : SchemaType<GraphQLSchemaParser.UnionDefContext>(context) {

  override val name: String = context.typeName().Name().text

  lateinit var possibilities: Set<TypeDef>

  override fun toKotlin(): TypeSpec = TypeSpec.objectBuilder(name).apply {
    addSuperinterface(schemaTypeClass.qualifiedName
        .let { it + CLASS_DELEGATE_MARKER }
        .asTypeName()) // TODO use delegate supporting vers
    possibilities.map { type ->

      FunSpec.builder("on" + type.name[0].toUpperCase() + type.name.let {
        if (it.length > 1) it.substring(1) else ""
      }).apply {
        // ugly java calling code wtf
        addParameter(ParameterSpec.builder("init",
            LambdaTypeName.get(returnType = ParameterizedTypeName.get(QModel::class.asTypeName(),
                type.name.asTypeName()))).build())
        addCode("on(init)")
      }.build()
    }.forEach {
      addFunction(it)
    }

  }.build()

  override val schemaTypeClass = QUnionType::class
  override val delegateStubClass: KClass<*> = QSchemaType.QInterfaces::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QInterfaces.List::class

  companion object {
    val CLASS_DELEGATE_MARKER = "_BY_UNION_CLASS_DELEGATE_"
  }
}

class ScalarDef(context: GraphQLSchemaParser.ScalarDefContext)
  : SchemaType<GraphQLSchemaParser.ScalarDefContext>(context) {

  override val name = context.typeName().Name().text

  override fun toKotlin(): TypeSpec = TypeSpec.objectBuilder(name)
      .addSuperinterface(schemaTypeClass)
      .build()

  override val schemaTypeClass = CustomScalar::class
  override val delegateStubClass: KClass<*> = QSchemaType.QInterfaces::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QInterfaces.List::class
}

class EnumDef(context: GraphQLSchemaParser.EnumDefContext)
  : SchemaType<GraphQLSchemaParser.EnumDefContext>(context) {

  override val name: String = context.typeName().Name().text

  val options = context.scalarName().map { it.Name().text }

  override fun toKotlin(): TypeSpec = TypeSpec.enumBuilder(name).apply {
    options.forEach { addEnumConstant(it) }
    addSuperinterface(schemaTypeClass)
  }.build()

  override val schemaTypeClass = QEnumType::class
  override val delegateStubClass: KClass<*> = QSchemaType.QEnum::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QEnum.List::class

}

class InputDef(context: GraphQLSchemaParser.InputTypeDefContext)
  : ScopedDeclarationType<GraphQLSchemaParser.InputTypeDefContext>(context) {

  override val name = context.typeName().Name().text

  override val fields = context.fieldDef().map(::FieldDefinition).toSet()

  override fun toKotlin(): TypeSpec = TypeSpec.classBuilder(name).apply {
    addSuperinterface(QSchemaType::class)
    // add required (non-nullable) fields to primary constructor
    this@InputDef.fields.filterNot { it.nullable }.map { required ->
      ParameterSpec.builder(required.name, required.type.name.asTypeName()).build()
    }.also { params ->
      FunSpec.constructorBuilder()
          .addParameters(params)
          .build()
          .let(this::primaryConstructor)
    }
    // add others as nullable props
    this@InputDef.fields.filter { it.nullable }.map {
      PropertySpec.builder(it.name, it.type.name.asTypeName().asNullable())
          .initializer("null") // TODO boxed primitives
          .build()
    }.let(this::addProperties)

  }.build()

  override val schemaTypeClass = throw UnsupportedOperationException()
  override val delegateStubClass: KClass<*> = throw UnsupportedOperationException()
  override val delegateListStubClass: KClass<*> = delegateStubClass

}

sealed class ScalarType : SchemaType<PlatformTypeContext>(PlatformTypeContext) {

  abstract val stubClass: KClass<out ScalarDelegate<*>>
  abstract val arrayStubClass: KClass<out ScalarArrayDelegate<*>>

  override val schemaTypeClass
    get() = throw IllegalArgumentException("No schema stub class for primitives!")

  override fun getStubDelegationCall(field: FieldDefinition): CodeBlock = when {
    field.arguments.isEmpty() -> "stub()" to emptyList<String>()
    field.arguments.isNotEmpty() && field.arguments.find { !it.nullable } == null ->
      "optionalConfigStub<%T>()" to listOf(field.argBuilder!!.name)
    else -> "configStub<%T>()" to listOf(field.argBuilder!!.name)
  }.let { (format, typeNames) ->
    CodeBlock.of("%T.$format", *stubFor(field, typeNames).toTypedArray())
  }
}

object IntType : ScalarType() {
  override val stubClass = IntDelegate::class
  override val arrayStubClass = IntArrayDelegate::class
  override val name: String get() = "Int"
  override fun toKotlin(): TypeSpec = throw UnsupportedOperationException()

  override val delegateStubClass: KClass<*> = QSchemaType.QScalar.Int::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QScalar.List.Int::class
}

object StringType : ScalarType() {
  override val stubClass = StringDelegate::class
  override val arrayStubClass = StringArrayDelegate::class
  override val name: String get() = "String"
  override fun toKotlin(): TypeSpec = throw UnsupportedOperationException()

  override val delegateStubClass: KClass<*> = QSchemaType.QScalar.String::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QScalar.List.String::class
}

object FloatType : ScalarType() {
  override val stubClass = FloatDelegate::class
  override val arrayStubClass = FloatArrayDelegate::class
  override val name: String get() = "Float"
  override fun toKotlin(): TypeSpec = throw UnsupportedOperationException()

  override val delegateStubClass: KClass<*> = QSchemaType.QScalar.Float::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QScalar.List.Float::class
}

object BooleanType : ScalarType() {
  override val stubClass = BooleanDelegate::class
  override val arrayStubClass = BooleanArrayDelegate::class
  override val name: String get() = "Boolean"
  override fun toKotlin(): TypeSpec = throw UnsupportedOperationException()

  override val delegateStubClass: KClass<*> = QSchemaType.QScalar.Boolean::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QScalar.List.Boolean::class
}

object PlatformTypeContext : ParserRuleContext()

fun String.asTypeName(): TypeName = ClassName.bestGuess(this)

private fun SchemaType<*>.stubFor(field: FieldDefinition, typeArgs: List<String>): List<ClassName> =
    mutableListOf((if (field.isList) delegateListStubClass else delegateStubClass).asTypeName()).apply {
      // all are generated in same package for now
      addAll(typeArgs.map { ClassName("", it) })
    }.toList()

