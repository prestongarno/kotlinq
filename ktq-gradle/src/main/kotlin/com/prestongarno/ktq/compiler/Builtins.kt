package com.prestongarno.ktq.compiler

enum class ScalarPrimitives(val typeDef: ScalarType) {
  INT(IntType),
  BOOLEAN(BooleanType),
  FLOAT(FloatType),
  STRING(StringType);

  companion object {
    val named: Map<String, ScalarPrimitives> = values().map { Pair(it.typeDef.name, it) }.toMap()
  }
}


internal fun String.prepend(prefix: String) = prefix + this