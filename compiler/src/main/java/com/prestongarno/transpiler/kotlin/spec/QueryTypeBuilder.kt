package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.ktq.QType
import com.prestongarno.transpiler.qlang.spec.QTypeDef
import com.squareup.kotlinpoet.*

object QueryTypeBuilder {
	fun buildRootQueryClass(qType: QTypeDef, packageName: String = "com.prestongarno.ktq"): KotlinFile {
		val file = KotlinFile.builder(packageName, "Query")
		val typeVariable = TypeVariableName.Companion.invoke("E").withBounds(QType::class)

		val onSuccess = LambdaTypeName.get(null, listOf(typeVariable), UNIT)
		val onError = LambdaTypeName.get(null, listOf(INT.topLevelClassName(), ClassName.bestGuess("String")), UNIT)

		val companionObject = TypeSpec.companionObjectBuilder()

		return file.addType(TypeSpec.classBuilder(qType.name)
				.addTypeVariable(typeVariable)
				.addFun(FunSpec.constructorBuilder()
						.addParameter(ParameterSpec.builder("onSuccess", onSuccess).build())
						.addParameter(ParameterSpec.builder("onError", onError).defaultValue(CodeBlock.builder()
								.add(" { code, message -> }")
								.build()).build())
						.build()).companionObject(companionObject.build())
				.build()).build()
	}
}

