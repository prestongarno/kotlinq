package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.transpiler.qlang.spec.QDefinedType
import com.prestongarno.transpiler.qlang.spec.QUnionTypeDef
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec

class UnionBuilder {

	fun createSpec(union: QUnionTypeDef) : TypeSpec {
		val builder = TypeSpec.classBuilder(union.name)
		union.possibleTypes.forEach { t -> builder.addFun(createFunctionSpec(t))}
		TODO()
	}

	private fun createFunctionSpec(unionOption: QDefinedType): FunSpec {
		return FunSpec.builder("of" + unionOption.name) .build()
	}
}