package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QType
import com.prestongarno.transpiler.qlang.spec.*
import com.squareup.kotlinpoet.*

class QTypeBuilder(val packageName: String) {

	fun createType(qType: QStatefulType, packageName: String = "com.prestongarno.ktq"): TypeSpec {
		val builder = TypeSpec.classBuilder(ClassName.invoke(packageName, qType.name))
				.superclass(QType::class)
				.addModifiers(KModifier.ABSTRACT)
		qType.fields.map { buildPropertySpec(it as QField) }
				.forEach { builder.addProperty(it) }
		qType.fields.filter { it.args.isNotEmpty() }
				.map { createPayloadClasses(it.args.map { it as QFieldInputArg }, it as QField) }
				.forEach { builder.addType(it) }
		return builder.build()
	}

	fun buildPropertySpec(field: QField): PropertySpec =
			PropertySpec.builder(field.name, determineTypeName(field))
					.delegate("lazy { throw SchemaStub() }")
					.addModifiers(KModifier.OPEN, KModifier.PROTECTED)
					.build()

	fun createPayloadClasses(args: List<QFieldInputArg>, field: QField): TypeSpec {
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

	private fun createBuilderMethodUsingPoetBuilderMethod(typeName: TypeName, param: QFieldInputArg, inputClazzName: String) =
			FunSpec.builder(param.name)
					.addParameter("value", typeName)
					.addCode(" addArg(\"${param.name}\", value); return this;\n")
					.returns(ClassName.invoke("", inputClazzName))
					.build()


	fun determineTypeName(f: QSymbol): TypeName {
		var result: TypeName
		if (f is QScalarType)
			result = ClassName.bestGuess((f as QScalarType).clazz.qualifiedName!!)
		else
			result = ClassName.invoke(packageName, f.type.name)

		if (f.isList) result = ParameterizedTypeName.get(ClassName.invoke("kotlin.collections", "List"), result)
		return result
	}

	fun inputBuilderClassName(forField: String): String = "${forField[0].toUpperCase()}${forField.substring(1)}Args"
}
