package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.ktq.runtime.ArgBuilder
import com.prestongarno.transpiler.qlang.spec.*
import com.squareup.kotlinpoet.*
import kotlin.reflect.KClass

class QTypeBuilder(val packageName: String) {
	fun createType(qType: QTypeDef, packageName: String = "com.prestongarno.ktq"): TypeSpec {
		val builder = TypeSpec.classBuilder(ClassName.invoke(packageName, qType.name))
		qType.fields.map { buildPropertySpec(it as QField) }
				.forEach { builder.addProperty(it) } //TODO fix weird union type below. I think it was caused by reursive parsing nested args
		qType.fields.filter { it.args.isNotEmpty() }.map { createPayloadClasses(it.args.map { it as QFieldInputArg }, it as QField) }
				.forEach { builder.addType(it) }
		return builder.build()
	}

	fun buildPropertySpec(field: QField): PropertySpec {
		return PropertySpec.builder(field.name, determineTypeName(field))
				.initializer("by lazy { throw SchemaStub() }")
				.build()
	}

	fun createPayloadClasses(args: List<QFieldInputArg>, field: QField): TypeSpec {
		val argBuilderSpec = TypeSpec.classBuilder(field.name) // TODO change this to correct case for style format
				.primaryConstructor(FunSpec.constructorBuilder()
						.addParameter(ParameterSpec.builder("builder",
								ArgBuilder::class,
								KModifier.PRIVATE, KModifier.CONST)
								.defaultValue("ArgBuilder.create()").build())
						.addAnnotation(ClassName.invoke("com.prestongarno.ktq.runtime.annotations", "Payload"))
						.build())
				.addSuperinterface(ArgBuilder::class)

		args.map { createBuilderMethodUsingPoetBuilderMethod(determineTypeName(it), it) }
				.forEach { argBuilderSpec.addFun(it) }
		return argBuilderSpec.build()
	}

	private fun createBuilderMethodUsingPoetBuilderMethod(typeName: TypeName, param: QFieldInputArg) =
			FunSpec.builder(param.name)
					.addParameter("value", typeName)
					.addCode(CodeBlock.of("addArg ${param.name}"))
					.addCode(CodeBlock.of("return this"))
					.returns(typeName)
					.build()


	fun determineTypeName(f: QSymbol): TypeName {
		var result: TypeName
		if (f is QScalarType)
			result = ClassName.bestGuess((f as QScalarType).clazz.qualifiedName!!)
		else
			result = ClassName.invoke(packageName, f.name)
		if (f.isList) result = ClassName.invoke("kotlin.collections", "List", result.simpleName())
		return result
	}
}
