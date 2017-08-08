package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.transpiler.qlang.spec.*


object ScalarBuilder {
	fun toType(from: QScalarType) = QTypeDef(from.name,
			emptyList(),
			listOf(QField("value",
					Scalar.getType(Scalar.UNKNOWN),
					emptyList(),
					QDirectiveSymbol.default,
					false,
					false)))
}