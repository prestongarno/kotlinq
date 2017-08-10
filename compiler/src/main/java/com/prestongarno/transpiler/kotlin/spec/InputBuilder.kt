package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.ktq.runtime.QInput
import com.prestongarno.transpiler.qlang.spec.QInputType
import com.prestongarno.transpiler.qlang.spec.QScalarType
import com.prestongarno.transpiler.qlang.spec.QSymbol
import com.prestongarno.transpiler.qlang.spec.QTypeDef
import com.squareup.kotlinpoet.*
import kotlin.reflect.full.memberProperties

/**
 * Creates data classes to represent input types. Constructor invocation requires parameters only
 * specified as not-null in the graphql schema, and other values set using builder style functions
 * TIL about 'apply' functions in kotlin
 */
object InputBuilder {
	fun createInputSpec(of: QInputType, packageName: String = "com.prestongarno.ktq"): TypeSpec = TypeSpec.classBuilder(of.name)
			.addModifiers(KModifier.DATA)
			.addSuperinterface(QInput::class)
			.addProperties(of.fields.map {
				PropertySpec.builder(it.name, ClassName.bestGuess(it.type.name))
						.initializer(if(it.nullable) "null" else it.name)
						.mutable(it.nullable)
						.build()
			})
			.primaryConstructor(createConstructor(of.fields.filterNot { it.nullable }))
			.addFunctions(of.fields.filter { it.nullable }.map {
				FunSpec.builder(it.name)
						.addParameter(ParameterSpec.builder("value", ClassName.bestGuess(it.type.name))
								.build())
						.addCode(CodeBlock.of("apply { ${it.name} = value }"))
						.build()
			}).build()

	private fun createConstructor(params: List<QSymbol>): FunSpec = FunSpec.constructorBuilder()
			.addParameters(params.map {
				ParameterSpec.builder(it.name, ClassName.bestGuess(it.type.name)).build()
			}).build()
}
