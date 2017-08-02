package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.ktq.runtime.QueryData
import com.prestongarno.transpiler.qlang.spec.QField
import com.prestongarno.transpiler.qlang.spec.QSymbol
import com.prestongarno.transpiler.qlang.spec.QTypeDef
import com.squareup.kotlinpoet.*

object QueryTypeBuilder {
	fun buildRootQueryClass(qType: QTypeDef, packageName: String = "com.prestongarno.ktq"): KotlinFile {
		val file = KotlinFile.builder(packageName, "Query")
		val typeVariable = TypeVariableName.Companion.invoke("E").withBounds(QueryData::class)

		val onSuccess = LambdaTypeName.get(null, listOf(typeVariable), UNIT)
		val onError = LambdaTypeName.get(null, listOf(INT.topLevelClassName(), ClassName.invoke("java.lang", "String")), UNIT)

		val companionObject = TypeSpec.companionObjectBuilder()
		companionObject.addFunctions(qType.fields.map{ f -> createQueryFun(f) })

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

	fun createQueryFun(field: QSymbol): FunSpec {
		val builder = FunSpec.builder(field.name)
		val returnType = QTypeBuilder.createQueryDataType(field.type as QTypeDef)
		TODO()
	}
}

/**
 *

class Query<E>(val onSuccess: (E) -> Unit, val onError: (Int, String) -> Unit = { i, m -> }) {

companion object builder {

val queue = LinkedList<Any>()

fun exec() = (queue[0] as Query<SearchResultItemConnection<*,*,*>>).onError(400, "Not Found!")

fun <T : SearchResultItemConnection<*,*,*>> search(builder: Search.builder<T>): Query.builder {
println("Created object '$builder'")
queue.add(builder.search)
return this
}
}

}

 */
