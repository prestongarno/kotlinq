package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QType
import com.prestongarno.transpiler.qlang.spec.*
import com.squareup.kotlinpoet.*

class QTypeBuilder(val packageName: String) {

  fun createType(qType: QStatefulType, packageName: String = "com.prestongarno.ktq"): TypeSpec {
    val builder = TypeSpec.interfaceBuilder(qType.name)
        .addSuperinterface(QType::class)

    qType.fields.filter { it.args.isEmpty() && it is QField }
        .map {
          if (it.type is QScalarType || it.type is QEnumDef) {
            builder.addFunctions(listOf(buildPropertySpec(it as QField)))
          } else {
            builder.addFunctions(listOf(buildInitializerFun(it as QField)))
          }
        }

    qType.fields.filter { it.args.isNotEmpty() && it is QField }
        .map { Pair(it as QField, createPayloadClasses(it.args.map { it as QFieldInputArg }, it)) }
        .forEach {
          builder.addType(it.second)
          builder.addFunctions(listOf(buildInitializerConfigFun(it.first, it.second)))
        }
    return builder.build()
  }

  private fun buildInitializerConfigFun(field: QField, forArgType: TypeSpec): FunSpec {
    val type = determineTypeName(field)
    val typeVariable = TypeVariableName.Companion.invoke("T").withBounds(type)
    return FunSpec.builder(field.name)
        .returns(ParameterizedTypeName
            .get(ClassName.bestGuess(if (field.nullable) "NullableStub" else "Stub"),
                typeVariable,
                ClassName.invoke("", forArgType.name!!)))
        .addTypeVariable(typeVariable)
        .addParameter("init", LambdaTypeName.get(null, emptyList(), typeVariable))
        .addParameter(ParameterSpec.builder("argBuilder", ClassName.bestGuess(forArgType.name!!))
            .defaultValue(CodeBlock.of("${forArgType.name}()"))
            .build())
        .addCode(CodeBlock.builder()
            .addStatement(if (field.nullable) " return configNullableStub(init, argBuilder)"
            else "return configStub(init, argBuilder)")
            .build())
        .build()
  }

  private fun buildInitializerFun(field: QField): FunSpec {
    val type = determineTypeName(field)
    val typeVariable = TypeVariableName.Companion.invoke("T").withBounds(type)
    return FunSpec.builder(field.name)
        .returns(ParameterizedTypeName
            .get(ClassName.bestGuess(if (field.nullable) "NullableStub" else "Stub"),
                ClassName.invoke("", type.toString()),
                ClassName.bestGuess("ArgBuilder")))
        .addTypeVariable(typeVariable)
        .addParameter("init", LambdaTypeName.get(null, emptyList(), typeVariable))
        .addCode(CodeBlock.builder()
            .addStatement("return ${if (field.nullable) "nullableStub" else "stub"}()")
            .build())
        .build()
  }

  private fun buildPropertySpec(field: QField): FunSpec {
    return FunSpec.builder(field.name)
        .addModifiers(KModifier.PUBLIC)
        .returns(ParameterizedTypeName
            .get(ClassName.bestGuess(if (field.nullable) "NullableStub" else "Stub"),
                ClassName.invoke("", determineTypeName(field).toString()),
                ClassName.bestGuess("ArgBuilder")))
        .addCode(CodeBlock.builder()
            .addStatement("return ${if (field.nullable) "nullableStub" else "stub"}()")
            .build())
        .build()
  }

  private fun createPayloadClasses(args: List<QFieldInputArg>, field: QField): TypeSpec {
    val inputClazzName = inputBuilderClassName(field.name)
    val argBuilderSpec = TypeSpec.classBuilder(inputClazzName)
        .primaryConstructor(FunSpec.constructorBuilder()
            .addParameter(ParameterSpec.builder("builder", ArgBuilder::class)
                .defaultValue("ArgBuilder.create()")
                .build())
            .build())
        .addSuperinterface(ClassName.invoke("", "ArgBuilder_by_builder"))

    args.map { createBuilderMethodUsingPoetBuilderMethod(determineTypeName(it), it, inputClazzName) }
        .forEach { argBuilderSpec.addFun(it) }

    return argBuilderSpec.build()
  }

  private fun createBuilderMethodUsingPoetBuilderMethod(typeName: TypeName,
      param: QFieldInputArg,
      inputClazzName: String) =
      FunSpec.builder(param.name)
          .addParameter("value", typeName)
          .addCode(CodeBlock.builder().addStatement("return apply { addArg(\"${param.name}\", value) }\n").build())
          .returns(ClassName.invoke("", inputClazzName))
          .build()

  fun determineTypeName(f: QSymbol): TypeName {
    var result: TypeName
    if (f is QScalarType)
      result = ClassName.invoke("", (f as QScalarType).clazz.simpleName!!)
    else
      result = ClassName.invoke("", f.type.name.split(".").last())
    return result
  }

  fun inputBuilderClassName(forField: String): String = "${forField[0].toUpperCase()}${forField.substring(1)}Args"
}
