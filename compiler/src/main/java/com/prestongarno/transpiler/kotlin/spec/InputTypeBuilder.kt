package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.ktq.QInput
import com.prestongarno.transpiler.qlang.spec.QInputType
import com.prestongarno.transpiler.qlang.spec.QSymbol
import com.squareup.kotlinpoet.*

/**
 * Creates data classes to represent input types. Constructor invocation requires parameters only
 * specified as not-null in the graphql schema, and other values set using builder style functions
 * TIL about 'apply' functions in kotlin
 */
object InputTypeBuilder {

	fun createInputSpec(of: QInputType, packageName: String = "com.prestongarno.ktq"): TypeSpec = TypeSpec.classBuilder(of.name)
			.addModifiers(KModifier.DATA)
			.addSuperinterface(QInput::class)
			.addProperties(of.fields.map {
				PropertySpec.builder(it.name, ClassName.bestGuess(it.type.name), KModifier.PRIVATE)
            .mutable(it.nullable)
						.initializer(if(it.nullable) "null" else it.name)
						.build()
			})
			.primaryConstructor(createConstructor(of.fields.filterNot { it.nullable }))
			.addFunctions(of.fields.filter { it.nullable }.map {
				FunSpec.builder(it.name)
						.addParameter(ParameterSpec.builder("value", ClassName.bestGuess(it.type.name))
								.build())
						.addCode(CodeBlock.builder().addStatement("return apply { ${it.name} = value }").build())
						.build()
			}).build()

	private fun createConstructor(params: List<QSymbol>): FunSpec = FunSpec.constructorBuilder()
			.addParameters(params.map {
				ParameterSpec.builder(it.name, ClassName.bestGuess(it.type.name)).build()
			}).build()
}
