package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.ktq.*
import com.prestongarno.ktq.QType
import com.prestongarno.transpiler.QCompiler
import com.prestongarno.transpiler.qlang.spec.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ClassName.Companion.bestGuess

class QInterfaceBuilder(private val allIfaces: List<QInterfaceDef>) {

  fun buildAll(): List<Pair<QInterfaceDef, TypeSpec>>
      = allIfaces.mapTo(ArrayList(allIfaces.size), {
    Pair(it, TypeSpec.interfaceBuilder(it.name)
        .addSuperinterface(QType::class)
        .addProperties(it.fields.map {
          buildAbstractProperty(it)
        })
        .addTypes(
            it.fields.filter {
              it.args.isNotEmpty()
            }.map {
              buildInputArgTypes(it)
            })
        .build())
  }).sortedBy { it.first.name }

  private fun buildAbstractProperty(field: QSymbol): PropertySpec {
    val typeName = determineTypeName(field)
    val rawTypeName =
        if (field.args.isEmpty()) {
          if (field.type is QScalarType || field.type is QEnumDef)
            ParameterizedTypeName.get(bestGuess("${Stub::class.simpleName}"), bestGuess(field.type.name))
          else ParameterizedTypeName.get(bestGuess("${InitStub::class.simpleName}"), typeName)
        } else {
          val inputTypeName = bestGuess(inputBuilderClassName(field.name))
          if (field.type is QScalarType)
            ParameterizedTypeName.get(bestGuess("${Config::class.simpleName}"), inputTypeName, typeName)
          else {
            ParameterizedTypeName.get(bestGuess("${ConfigType::class.simpleName}"), inputTypeName, typeName)
          }
        }
    return PropertySpec.builder(field.name, rawTypeName).build()
  }

  companion object {

    fun buildInputArgTypes(field: QSymbol): TypeSpec {

      if(field.type is QEnumDef)
        println(field.args)

      val inputClazzName = inputBuilderClassName(field.name)
      val rawType = if (field.type is QScalarType) ArgBuilder::class else TypeArgBuilder::class

      val fieldType = determineTypeName(field)
      val argBuilderSig = ParameterizedTypeName.get(bestGuess("${rawType.simpleName}"), fieldType)
      val typeArgBuilderSig = ParameterizedTypeName.get(bestGuess("TypeArgBuilder"),
          fieldType,
          ParameterizedTypeName.get(bestGuess("QModel"),
              fieldType))

      val paramSignature = if (rawType == ArgBuilder::class) argBuilderSig else typeArgBuilderSig

      val argBuilderSpec = TypeSpec.classBuilder(inputClazzName)
          .primaryConstructor(FunSpec.constructorBuilder()
              .addParameter(ParameterSpec.builder("args", paramSignature)
                  .defaultValue("${rawType.simpleName}.create()")
                  .build())
              .build())
          .addSuperinterface(ClassName.bestGuess("${paramSignature.toString()
              .replace(", ", QCompiler.COMMA)
              .replace("<", QCompiler.LESS_THAN)
              .replace(">", QCompiler.GREATER_THAN)}_by_args"))

      field.args.map { createBuilderMethodUsingPoetBuilderMethod(determineTypeName(it), it as QFieldInputArg, inputClazzName) }
          .let { argBuilderSpec.addFunctions(it) }

      return argBuilderSpec.build()
    }

    private fun createBuilderMethodUsingPoetBuilderMethod(typeName: TypeName,
        param: QFieldInputArg,
        inputClazzName: String) =

        FunSpec.builder(param.name)
            .addParameter("value", typeName)
            .addCode(CodeBlock.builder().addStatement("return apply { addArg(\"${param.name}\", value) }\n").build())
            .returns(ClassName.bestGuess(inputClazzName))
            .build()

    fun determineTypeName(f: QSymbol): TypeName {
      return if (f is QScalarType)
        bestGuess((f.type as QScalarType).clazz.simpleName!!)
      else
        bestGuess(f.type.name.split(".").last())
    }

    fun inputBuilderClassName(forField: String): String = "${forField[0].toUpperCase()}${forField.substring(1)}Args"
  }
}
