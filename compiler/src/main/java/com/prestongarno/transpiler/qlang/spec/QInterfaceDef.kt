package com.prestongarno.transpiler.qlang.spec

import com.squareup.kotlinpoet.TypeSpec
import com.prestongarno.ktq.*
import com.prestongarno.ktq.QType
import com.prestongarno.transpiler.QCompiler
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ClassName.Companion.bestGuess

open class QInterfaceDef(name: String, fields: List<QField>) : QStatefulType(name, fields) {

  private val noOpBuilder = mutableSetOf<QField>()

  override fun toKotlin(): TypeSpec {
    if (this.kotlinSpec == null)
      kotlinSpec = build()
    return kotlinSpec!!
  }

  fun tagMultiOverride(forField: QField) {
    noOpBuilder += fields.find { it == forField }?: throw IllegalStateException("no such field $forField")
  }

  private fun build(): TypeSpec = TypeSpec.interfaceBuilder(this.name)
      .addSuperinterface(QType::class)
      .addProperties(this.fields.map { field -> field.toKotlin().first })
      .addTypes(this.fields
          .filter { !noOpBuilder.contains(it) && it.toKotlin().second.isPresent }
          .map { it.toKotlin().second.get() })
      .build()
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

  val paramSignature = if (rawType == ArgBuilder::class) ArgBuilder::class else TypeArgBuilder::class

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

fun determineTypeName(f: QFieldInputArg): TypeName {
  return if (f.type is QScalarType)
    bestGuess((f.type as QScalarType).clazz.simpleName!!)
  else
    bestGuess(f.type.name.split(".").last())
}

fun determineTypeName(f: QField): TypeName {
  return if (f.type is QScalarType)
    bestGuess((f.type as QScalarType).clazz.simpleName!!)
  else
    bestGuess(f.type.name.split(".").last())
}

fun inputBuilderClassName(forField: String): String = "${forField[0].toUpperCase()}${forField.substring(1)}Args"
