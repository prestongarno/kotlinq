package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.ktq.QType
import com.prestongarno.transpiler.qlang.spec.QEnumDef
import com.squareup.kotlinpoet.TypeSpec

fun createEnums(qEnums: List<QEnumDef>): List<TypeSpec> = qEnums.map { qenum ->
	val spec = TypeSpec.enumBuilder(qenum.name)
			.addSuperinterface(QType::class)
	qenum.options.forEach { str -> spec.addEnumConstant(str) }
	spec.build()
			.also { qenum.kotlinSpec = it }
}