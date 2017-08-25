package com.prestongarno.transpiler.qlang.spec

import com.squareup.kotlinpoet.TypeSpec
import kotlin.reflect.KClass

sealed class QScalarType(name: String, val clazz: KClass<*>) : QDefinedType(name) {

  override fun toKotlin(): TypeSpec {
    throw IllegalStateException("no")
  }
}

class QCustomScalarType(name: String) : QScalarType(name, String::class) {
  override fun toKotlin(): TypeSpec {
    if (this.kotlinSpec == null) this.kotlinSpec = QTypeDef(name,
        emptyList(),
        listOf(QField("value",
            Scalar.getType(Scalar.STRING),
            emptyList(),
            QDirectiveSymbol.default,
            false,
            false))).toKotlin()

    return this.kotlinSpec!!
  }
}

class QInt(val defValue: Int = 0) : QScalarType("Int", Int::class)

class QFloat(val defValue: Float = 0f) : QScalarType("Float", Float::class)

class QBool(val defValue: Boolean = false) : QScalarType("Boolean", Boolean::class)

class QString(val defValue: String = "") : QScalarType("String", String::class)
