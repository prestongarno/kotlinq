package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.transpiler.qlang.spec.QDefinedType
import com.prestongarno.transpiler.qlang.spec.QScalarType
import com.prestongarno.transpiler.qlang.spec.QTypeDef
import com.prestongarno.transpiler.qlang.spec.Scalar
import com.squareup.kotlinpoet.*
import java.lang.reflect.TypeVariable

object QTypeBuilder {
	fun createType(qType: QTypeDef, packageName: String = "com.prestongarno.ktq"): TypeSpec {
		val builder = TypeSpec.classBuilder(ClassName.invoke(packageName, qType.name))
		qType.fields.filter { f -> f.type is QScalarType }.forEach { f ->
			builder.addProperty(f.name, (f.type as QScalarType).clazz)
		}
		return builder.build()
	}

	fun createQueryDataType(qType: QTypeDef): TypeSpec {
		TODO()
	}
}
