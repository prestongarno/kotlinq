package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.transpiler.qlang.spec.*


object ScalarBuilder {
	fun toType(from: QScalarType) = QTypeDef(from.name,
			emptyList(),
			listOf(QField("value",
					Scalar.getType(Scalar.STRING),
					emptyList(),
					QDirectiveSymbol.default,
					false,
					false)))
}