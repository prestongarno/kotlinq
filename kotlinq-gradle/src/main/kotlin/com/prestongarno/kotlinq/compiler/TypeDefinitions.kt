/*
 * Copyright (C) 2018 Preston Garno
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

import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.schema.CustomScalar
import com.prestongarno.kotlinq.core.schema.QEnumType
import com.prestongarno.kotlinq.core.schema.QInputType
import com.prestongarno.kotlinq.core.schema.QInterface
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.schema.QUnionType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ClassName.Companion.bestGuess
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import kotlin.reflect.KClass

const val ALIAS_IMPORT_ROOT = "com.prestongarno.kotlinq.core.schema.properties."

private const val DEFAULT_NO_ARG = "stub<%T>()"
private const val DEFAULT_OPTIONAL_ARG = "optionallyConfigured<%T, %T>()"
private const val DEFAULT_REQ_ARG = "configured<%T, %T>()"

sealed class SchemaType : KotlinTypeElement, NamedElement {

  abstract val schemaTypeClass: KClass<out QSchemaType>

  abstract val delegateStubClass: KClass<*>
  abstract val delegateListStubClass: KClass<*>

  open val canBeExplicitlyNulled = false
  abstract val packageDirective: String

  open fun getPropertyStubTypeAlias(field: FieldDefinition) =
      calculateTypeAlias(this, field)

  // this is literally hundreds of lines simpler than the last one
  open fun getStubDelegationCall(field: FieldDefinition): CodeBlock = when {

    field.arguments.isEmpty() -> DEFAULT_NO_ARG to listOf(name)

    field.arguments.isNotEmpty() && field.arguments.find { !it.nullable } == null ->
      DEFAULT_OPTIONAL_ARG to listOf(name, field.argBuilder!!.name)

    else -> DEFAULT_REQ_ARG to listOf(name, field.argBuilder!!.name)
  }.let { if (field.nullable && canBeExplicitlyNulled) it.first.plus(".asNullable()") to it.second else it }
      .let { (format, typeNames) -> CodeBlock.of("%T.$format", *stubFor(field, typeNames).toTypedArray()) }

  companion object {
    val CLASS_DELEGATE_MARKER = "__BY_CLASS_DELEGATE__"
  }

}


sealed class ScopedDeclarationType : SchemaType() {
  abstract val fields: Set<FieldDefinition>

  val symtab by lazy {
    fields.associate { it.name to it }.toMap()
  }
}


class TypeDef(
    override val name: String,
    val supertypeNames: List<String>,
    override val fields: Set<FieldDefinition>
) : ScopedDeclarationType() {

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

  override val canBeExplicitlyNulled = true
  override val packageDirective = "type"
}


class InterfaceDef(
    override val name: String,
    fields: Set<FieldDefinition>
) : ScopedDeclarationType() {

  override val fields = fields.onEach {
    it.isAbstract = true // flag as abstract
    it.inheritsFrom = emptySet() // initialize lateinit var
  }.toSet()

  override fun toKotlin(): TypeSpec = TypeSpec.interfaceBuilder(name)
      // iface needs to subtype QType *&* QInterface for iface fragment stubs
      .addSuperinterface(QType::class)
      .addSuperinterface(schemaTypeClass)
      .addProperties(fields.map(FieldDefinition::toKotlin))
      .addTypes(fields
          .mapNotNull(FieldDefinition::argBuilder)
          .map(ArgumentSpecDef::toKotlin))
      .build()

  override val schemaTypeClass = QInterface::class

  override val delegateStubClass: KClass<*> = QSchemaType.QInterfaces::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QInterfaces.List::class

  override val packageDirective = "iface"
}


class UnionDef(override val name: String, val types: List<String>) : SchemaType() {

  lateinit var possibilities: Set<TypeDef>

  override fun getStubDelegationCall(field: FieldDefinition): CodeBlock {
    return super.getStubDelegationCall(field).toString().split("()").let { before ->
      require(before.size == 2)
      // TODO@preston this will mess up imports
      return@let CodeBlock.of(before[0] + "($name)" + before[1])
    }
  }

  override fun toKotlin(): TypeSpec = TypeSpec.objectBuilder(name).apply {
    addSuperinterface((schemaTypeClass.qualifiedName + CLASS_DELEGATE_MARKER)
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
  override val packageDirective = "union"
}


class ScalarDef(override val name: String) : SchemaType() {

  override fun toKotlin(): TypeSpec = TypeSpec.objectBuilder(name)
      .addSuperinterface(schemaTypeClass)
      .build()

  override val schemaTypeClass = CustomScalar::class
  override val delegateStubClass: KClass<*> = QSchemaType.QCustomScalar::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QCustomScalar.List::class
  override val packageDirective = "scalar"
}


class EnumDef(override val name: String, private val options: List<String>)
  : SchemaType() {

  override fun toKotlin(): TypeSpec = TypeSpec.enumBuilder(name).apply {
    options.forEach { addEnumConstant(it) }
    addSuperinterface(schemaTypeClass)
  }.build()

  override val schemaTypeClass = QEnumType::class
  override val delegateStubClass: KClass<*> = QSchemaType.QEnum::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QEnum.List::class
  override val packageDirective = "enums"
}


class InputDef(override val name: String, override val fields: Set<FieldDefinition>)
  : ScopedDeclarationType() {

  override fun toKotlin(): TypeSpec = TypeSpec.classBuilder(name).apply {
    addSuperinterface((schemaTypeClass.qualifiedName + CLASS_DELEGATE_MARKER).asTypeName())
    // add required (non-nullable) fields to primary constructor
    this@InputDef.fields.filterNot { it.nullable }.map { required ->
      ParameterSpec.builder(required.name, required.type.name.asTypeName().let {
        if (required.isList) ParameterizedTypeName.get(ClassName("kotlin.collections", "List"), it) else it
      }).build()
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
                  PropertySpec.builder(it.name, it.type.name.asTypeName().let { name ->
                    if (it.isList) ParameterizedTypeName.get(ClassName("kotlin.collections", "List"), name) else name
                  }).delegate(notNullDelegateCode(arg = it, targetName = "input"))
                      .build()
                })
  }.build()

  override val schemaTypeClass = QInputType::class
  override val delegateStubClass: KClass<*> = Nothing::class
  override val delegateListStubClass: KClass<*> = Nothing::class

  override val canBeExplicitlyNulled get() = null!!
  override val packageDirective get() = null!!
}


sealed class ScalarType : SchemaType() {

  override val schemaTypeClass
    get() = throw IllegalArgumentException("No schema stub class for primitives!")

  override fun getStubDelegationCall(field: FieldDefinition): CodeBlock = when {
    field.arguments.isEmpty() -> "stub()" to emptyList()
    field.arguments.isNotEmpty() && field.arguments.find { !it.nullable } == null ->
      "optionallyConfigured<%T>()" to listOf(field.argBuilder!!.name)
    else -> "configured<%T>()" to listOf(field.argBuilder!!.name)
  }.let { (format, typeNames) ->
    CodeBlock.of("%T.$format", *stubFor(field, typeNames).toTypedArray())
  }

  override val packageDirective = "scalar"
}


object IntType : ScalarType() {
  override val name: String get() = "Int"
  override fun toKotlin(): TypeSpec = throw UnsupportedOperationException()

  override val delegateStubClass: KClass<*> = QSchemaType.QScalar.Int::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QScalar.List.Int::class
}


object StringType : ScalarType() {
  override val name: String get() = "String"
  override fun toKotlin(): TypeSpec = throw UnsupportedOperationException()

  override val delegateStubClass: KClass<*> = QSchemaType.QScalar.String::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QScalar.List.String::class
}


object FloatType : ScalarType() {
  override val name: String get() = "Float"
  override fun toKotlin(): TypeSpec = throw UnsupportedOperationException()

  override val delegateStubClass: KClass<*> = QSchemaType.QScalar.Float::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QScalar.List.Float::class
}


object BooleanType : ScalarType() {
  override val name: String get() = "Boolean"
  override fun toKotlin(): TypeSpec = throw UnsupportedOperationException()

  override val delegateStubClass: KClass<*> = QSchemaType.QScalar.Boolean::class
  override val delegateListStubClass: KClass<*> = QSchemaType.QScalar.List.Boolean::class
}

fun String.asTypeName(): TypeName {
  return try {
    ClassName("", this)
  } catch (ex: IllegalArgumentException) {
    throw IllegalArgumentException("Class name '$this' is unsupported at the moment", ex)
  }
}

private fun SchemaType.stubFor(field: FieldDefinition, typeArgs: List<String>): List<ClassName> =
    mutableListOf((if (field.isList) delegateListStubClass else delegateStubClass).asTypeName()).apply {
      // all are generated in same package for now
      addAll(typeArgs.map { ClassName("", it) })
    }.toList()

private
fun calculateTypeAlias(type: SchemaType, property: FieldDefinition): TypeName =
    property.getArgumentContext()
        .prefix
        .plus(type.baseName())
        .let { if (property.isList) it + "List" else it }
        .plus("Property").let {
      if (property.arguments.isEmpty())
        bestGuess(it)
      else
        ParameterizedTypeName.get(bestGuess(it.prepend(type.packageDirective.plus(".")).prepend(ALIAS_IMPORT_ROOT)),
            bestGuess(type.name),
            bestGuess(property.argBuilder!!.name))
    }

private
fun FieldDefinition.getArgumentContext(): ArgumentContext =
    if (arguments.isEmpty()) ArgumentContext.NO_ARG
    else if (arguments.isNotEmpty() && !requiresConfiguration) ArgumentContext.OPTIONAL
    else ArgumentContext.REQUIRED
private enum class ArgumentContext(val prefix: String) {
  NO_ARG(""), OPTIONAL("OptionallyConfigured"), REQUIRED("Configured")
}

private
fun SchemaType.baseName() = when (this) {
  is ScalarType -> name
  is TypeDef -> "Type"
  is EnumDef -> "Enum"
  is InterfaceDef -> "Interface"
  is UnionDef -> "Union"
  else -> null!!
}



