package com.prestongarno.transpiler.qlang.spec

import com.prestongarno.ktq.*
import com.prestongarno.transpiler.QCompiler
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ClassName.Companion.bestGuess
import java.util.*

class QField(name: String,
    var type: QDefinedType,
    var args: List<QFieldInputArg>,
    var directive: QDirectiveSymbol,
    var isList: Boolean = false,
    var nullable: Boolean = false,
    private var builderStatus: BuilderStatus =
    if (args.isEmpty())
      BuilderStatus.NONE
    else
      BuilderStatus.ENCLOSING)
  : QSchemaType<Pair<PropertySpec, Optional<TypeSpec>>>(name) {

  enum class BuilderStatus {
    NONE,
    ENCLOSING,
    TOP_LEVEL
  }

  fun flag(status: BuilderStatus) = apply { this.builderStatus = status }

  fun getStatus() = builderStatus

  var inheritedFrom = mutableListOf<QInterfaceDef>()

  override fun toKotlin(): Pair<PropertySpec, Optional<TypeSpec>> {
    val typeName = determineTypeName(this)
    val rawTypeName = if (this.args.isEmpty()) {
      if (this.type is QScalarType || this.type is QEnumDef)
        ParameterizedTypeName.get(ClassName.bestGuess("${Stub::class.simpleName}"), ClassName.bestGuess(this.type.name))
      else
        ParameterizedTypeName.get(ClassName.bestGuess("${InitStub::class.simpleName}"), typeName)
    } else {
      val configName =
          if (this.type is QScalarType)
            Config::class.simpleName
          else
            ConfigType::class.simpleName
      ParameterizedTypeName.get(ClassName.bestGuess("$configName"), typeName,
          when (builderStatus) {
            BuilderStatus.NONE -> throw IllegalStateException("the world is ending")
            BuilderStatus.ENCLOSING -> ClassName.bestGuess(if (inheritedFrom.isNotEmpty()) "Base" else "" + inputBuilderClassName(this.name))
            BuilderStatus.TOP_LEVEL -> if (builderStatus == BuilderStatus.TOP_LEVEL && this.inheritedFrom.isEmpty())
              ClassName.bestGuess("Base" + inputBuilderClassName(this.name))
            else
              bestGuess(inputBuilderClassName(this.name))
          })
    }
    return Pair(PropertySpec.builder(this.name, rawTypeName)
        .also {
          if (inheritedFrom.isEmpty())
            it.addModifiers(KModifier.ABSTRACT)
        }
        .build(),
        if (this.args.isEmpty()) {
          Optional.empty()
        } else if (inheritedFrom.isEmpty() && builderStatus == BuilderStatus.TOP_LEVEL)
          Optional.empty()
        else if (this.args.isNotEmpty() && this.inheritedFrom.isNotEmpty())
          if (inheritedFrom[0].fields.find { it.name == this.name }!!.getStatus() == BuilderStatus.TOP_LEVEL)
            Optional.of(buildArgBuilder(rawTypeName.typeArguments[1], this, false).build())
          else Optional.of(buildArgBuilder(this).build())
        else
          Optional.empty()
    )}

override fun equals(other: Any?): Boolean {
  if (this === other) return true
  if (javaClass != other?.javaClass) return false

  other as QField

  if (type != other.type) return false
  if (isList != other.isList) return false
  if (nullable != other.nullable) return false

  return true
}

override fun hashCode(): Int {
  var result = type.hashCode()
  result = 31 * result + args.hashCode()
  result = 31 * result + isList.hashCode()
  result = 31 * result + nullable.hashCode()
  return result
}

}
fun buildArgBuilder(field: QField): TypeSpec.Builder {
  return buildArgBuilder(bestGuess(inputBuilderClassName(field.name)), field, true)
}

fun buildArgBuilder(typeName: TypeName, field: QField, delegateToParam: Boolean): TypeSpec.Builder {
  val rawType =
      if (field.type is QScalarType)
        ArgBuilder::class
      else
        TypeArgBuilder::class

  val paramSignature = ClassName.bestGuess(inputBuilderClassName(field.name))

  val argBuilderSpec = TypeSpec.classBuilder(typeName.toString())
      .primaryConstructor(FunSpec.constructorBuilder()
          .addParameter(ParameterSpec.builder("args", paramSignature)
              .defaultValue("${rawType.simpleName}.create<${determineTypeName(field)}, $typeName>()")
              .build())
          .build())
  if (delegateToParam)
    argBuilderSpec.addSuperinterface(ClassName.bestGuess("${paramSignature.toString()
        .replace(", ", QCompiler.COMMA)
        .replace("<", QCompiler.LESS_THAN)
        .replace(">", QCompiler.GREATER_THAN)}_by_args"))
  else
    argBuilderSpec.superclass(typeName)
        .addSuperclassConstructorParameter(CodeBlock.of("args"))

  field.args.map {
    builderTypesMethod(determineTypeName(it), it, typeName.toString())
  }.let { argBuilderSpec.addFunctions(it) } // kotlinpoet bug

  return argBuilderSpec
}

private fun builderTypesMethod(typeName: TypeName, param: QFieldInputArg, inputClazzName: String) =
    FunSpec.builder(param.name)
        .addParameter("value", typeName)
        .addCode(CodeBlock.builder().addStatement("return apply { addArg(\"${param.name}\", value) }\n").build())
        .returns(ClassName.bestGuess(inputClazzName))
        .build()

fun inputBuilderClassName(forField: String): String = "${forField[0].toUpperCase()}${forField.substring(1)}Args"

fun inputClazzTypeName(field: QField): TypeName {
  return ClassName.bestGuess(when (field.getStatus()) {
    QField.BuilderStatus.NONE -> throw IllegalStateException()
    QField.BuilderStatus.ENCLOSING -> if (field.inheritedFrom.isNotEmpty()) "${field.inheritedFrom[0].name}.${inputBuilderClassName(field.name)}" else inputBuilderClassName(field.name)
    QField.BuilderStatus.TOP_LEVEL -> "Base" + inputBuilderClassName(field.name)
  })
}
