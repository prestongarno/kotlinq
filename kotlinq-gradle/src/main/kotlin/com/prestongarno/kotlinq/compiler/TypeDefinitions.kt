/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.prestongarno.kotlinq.compiler

import com.prestongarno.kotlinq.core.CustomScalar
import com.prestongarno.kotlinq.core.QEnumType
import com.prestongarno.kotlinq.core.QInputType
import com.prestongarno.kotlinq.core.QInterface
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.QUnionType
import com.prestongarno.kotlinq.core.org.antlr4.gen.GraphQLSchemaParser
import com.prestongarno.kotlinq.core.stubs.BooleanArrayDelegate
import com.prestongarno.kotlinq.core.stubs.BooleanDelegate
import com.prestongarno.kotlinq.core.stubs.FloatArrayDelegate
import com.prestongarno.kotlinq.core.stubs.FloatDelegate
import com.prestongarno.kotlinq.core.stubs.IntArrayDelegate
import com.prestongarno.kotlinq.core.stubs.IntDelegate
import com.prestongarno.kotlinq.core.stubs.ScalarArrayDelegate
import com.prestongarno.kotlinq.core.stubs.ScalarDelegate
import com.prestongarno.kotlinq.core.stubs.StringArrayDelegate
import com.prestongarno.kotlinq.core.stubs.StringDelegate
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

private const val DEFAULT_NO_ARG = "stub<%T>()"
private const val DEFAULT_OPTIONAL_ARG = "optionalConfigStub<%T, %T>()"
private const val DEFAULT_REQ_ARG = "configStub<%T, %T>()"

sealed class SchemaType<out T : ParserRuleContext>(val context: T) : KotlinTypeElement, NamedElement {

  abstract val schemaTypeClass: KClass<out QSchemaType>
  // the delegate stub creator objects which you invoke method for the right delegate field on
  abstract val delegateStubClass: KClass<*>
  abstract val delegateListStubClass: KClass<*>

  // this is literally hundreds of lines simpler than the last one
  open fun getStubDelegationCall(field: FieldDefinition): CodeBlock = when {
    field.arguments.isEmpty() -> DEFAULT_NO_ARG to listOf(name)
    field.arguments.isNotEmpty() && field.arguments.find { !it.nullable } == null ->
      DEFAULT_OPTIONAL_ARG to listOf(name, field.argBuilder!!.name)
    else -> DEFAULT_REQ_ARG to listOf(name, field.argBuilder!!.name)
  }.let { (format, typeNames) ->
    CodeBlock.of("%T.$format", *stubFor(field, typeNames).toTypedArray())
  }

  companion object {
    val CLASS_DELEGATE_MARKER = "__BY_CLASS_DELEGATE__"
  }

}


sealed class ScopedDeclarationType<out T : ParserRuleContext>(context: T) : SchemaType<T>(context) {
  abstract val fields: Set<FieldDefinition>

  val symtab by lazy {
    fields.associate { it.name to it }.toMap()
  }
}

class TypeDef(context: GraphQLSchemaParser.TypeDefContext)
  : ScopedDeclarationType<GraphQLSchemaParser.TypeDefContext>(context) {

  override val name: String = context.typeName().Name().text

  override val fields = context.fieldDef().map(::FieldDefinition).toSet()

  lateinit var supertypes: Set<InterfaceDef>

  override fun toKotlin(): TypeSpec = TypeSpec.objectBuilder(name).apply {
    addSuperinterface(schemaTypeClass)
    addProperties(fields.map(FieldDefinition::toKotlin))
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
    addProperties(fields.map(FieldDefinition::toKotlin))
    addTypes(fields
        .mapNotNull(FieldDefinition::argBuilder)
        .map(ArgumentSpecDef::toKotlin))
  }.build()

  override val schemaTypeClass = QInterface::class
  override val delegateStubClass: KClass<*> = QSchemaType.QInterfaces::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QInterfaces.List::class

}

class UnionDef(context: GraphQLSchemaParser.UnionDefContext)
  : SchemaType<GraphQLSchemaParser.UnionDefContext>(context) {

  override val name: String = context.typeName().Name().text

  lateinit var possibilities: Set<TypeDef>

  fun parametersRequiredFrom(field: FieldDefinition): List<TypeName> {
    return listOf(name.asTypeName())
  }

  override fun getStubDelegationCall(field: FieldDefinition): CodeBlock {
    return super.getStubDelegationCall(field).toString().split("()").let { before ->
      require(before.size == 2)
      // TODO@preston this will mess up imports
      return@let CodeBlock.of(before[0] + "($name)" + before[1])
    }
  }

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
  override val delegateStubClass: KClass<*> = QSchemaType.QUnion::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QUnion.List::class
}

class ScalarDef(context: GraphQLSchemaParser.ScalarDefContext)
  : SchemaType<GraphQLSchemaParser.ScalarDefContext>(context) {

  override val name = context.typeName().Name().text

  override fun toKotlin(): TypeSpec = TypeSpec.objectBuilder(name)
      .addSuperinterface(schemaTypeClass)
      .build()

  override val schemaTypeClass = CustomScalar::class
  override val delegateStubClass: KClass<*> = QSchemaType.QCustomScalar::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QCustomScalar.List::class
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
    addSuperinterface((schemaTypeClass.qualifiedName + CLASS_DELEGATE_MARKER).asTypeName())
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
      PropertySpec.builder(it.name, it.type.name.asTypeName()
          .asNullable())
          .delegate("input")
          .build()
    }.let(this::addProperties)
        .addProperties(
            this@InputDef.fields.filterNot { it.nullable }
                .map {
                  PropertySpec.builder(it.name, it.type.name.asTypeName())
                      .delegate(notNullDelegateCode(arg = it, targetName = "input"))
                      .build()
                })
  }.build()

  override val schemaTypeClass = QInputType::class
  override val delegateStubClass: KClass<*> = Nothing::class
  override val delegateListStubClass: KClass<*> = Nothing::class

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

