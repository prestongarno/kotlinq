package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QType
import com.prestongarno.transpiler.qlang.spec.*
import com.squareup.kotlinpoet.*

class QTypeBuilder(val packageName: String) {

  fun createType(qType: QStatefulType, packageName: String = "com.prestongarno.ktq"): TypeSpec {
    val builder = TypeSpec.interfaceBuilder(qType.name)
        .addSuperinterface(QType::class)

    qType.fields.map {
      if (it.type is QScalarType || it.type is QEnumDef) {
        builder.addFunctions(listOf(buildPropertySpec(it as QField)))
      } else {
        builder.addFunctions(listOf(buildInitializerFunction(it as QField),
            buildMappingField(it)))
      }
    }

    qType.fields.filter { it.args.isNotEmpty() }
        .map { createPayloadClasses(it.args.map { it as QFieldInputArg }, it as QField) }
        .forEach { builder.addType(it) }
    return builder.build()
  }

  //builds something like :: fun <T> boss(of: KFunction1<Person, Stub<T>>) = stub<Person, T>("boss", of)
  private fun buildMappingField(field: QField): FunSpec {
    val typeVariable = TypeVariableName.Companion.invoke("T")
    val type = determineTypeName(field)
    return FunSpec.builder(field.name)
        .returns(ParameterizedTypeName.get(ClassName.bestGuess("Stub"), typeVariable))
        .addTypeVariable(typeVariable)
        .addParameter("of", ParameterizedTypeName.get(ClassName.invoke("kotlin.reflect", "KFunction1"),
            type,
            ParameterizedTypeName.get(ClassName.bestGuess("Stub"), typeVariable)))
        .addCode(CodeBlock.builder()
            .addStatement("return stub<$type, T>(\"${field.name}\", of)")
            .build())
        .build()
  }

  private fun buildInitializerFunction(field: QField): FunSpec {
    val type = determineTypeName(field)
    val typeVariable = TypeVariableName.Companion.invoke("T").withBounds(type)
    return FunSpec.builder(field.name)
        .returns(ParameterizedTypeName
            .get(ClassName.bestGuess("Stub"), ClassName.bestGuess(determineTypeName(field).toString())))
        .addTypeVariable(typeVariable)
        .addParameter("init", LambdaTypeName.get(null, emptyList(), typeVariable))
        .addCode(CodeBlock.builder()
            .addStatement("stub(init)")
            .build())
        .build()
  }

  private fun buildPropertySpec(field: QField): FunSpec =
      FunSpec.builder(field.name)
          .addModifiers(KModifier.PUBLIC)
          .returns(ParameterizedTypeName
              .get(ClassName.bestGuess("Stub"), ClassName.bestGuess(determineTypeName(field).toString())))
          .addCode(CodeBlock.builder()
              .addStatement("stub()")
              .build())
          .build()

  private fun createPayloadClasses(args: List<QFieldInputArg>, field: QField): TypeSpec {
    val inputClazzName = inputBuilderClassName(field.name)
    val argBuilderSpec = TypeSpec.classBuilder(inputClazzName)
        .primaryConstructor(FunSpec.constructorBuilder()
            .addParameter(ParameterSpec.builder("builder", ArgBuilder::class)
                .defaultValue("ArgBuilder.create()")
                .build())
            .addAnnotation(ClassName.invoke("com.prestongarno.ktq.runtime.annotations", "Payload"))
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
          .addCode("return apply { addArg(\"${param.name}\", value) }\n")
          .returns(ClassName.invoke("", inputClazzName))
          .build()

  fun determineTypeName(f: QSymbol): TypeName {
    var result: TypeName
    if (f is QScalarType)
      result = ClassName.bestGuess((f as QScalarType).clazz.simpleName!!)
    else
      result = ClassName.bestGuess(f.type.name.split(".").last())

    //if (f.isList) result = ParameterizedTypeName.get(ClassName.bestGuess("List"), result)
    return result
  }

  fun inputBuilderClassName(forField: String): String = "${forField[0].toUpperCase()}${forField.substring(1)}Args"
}
