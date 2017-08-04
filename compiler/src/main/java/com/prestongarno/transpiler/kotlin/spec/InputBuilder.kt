package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.transpiler.qlang.spec.QInputType
import com.prestongarno.transpiler.qlang.spec.QTypeDef

/**
 * TODO Generify the type builders to take any type to get rid of mapping all to QTypeDefs before generating KT types
 */
object InputBuilder {
	fun asTypeDef(of: QInputType) = QTypeDef(of.name, emptyList(), of.fields)
}