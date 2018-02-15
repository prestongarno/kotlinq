package org.kotlinq.api

import kotlin.reflect.KType


data class GraphQlPropertyInfo(
    val graphQlName: String,
    val graphQlType: String,
    val platformType: KType,
    val arguments: Map<String, Any> = emptyMap()) {

  val isNullable get() = platformType.isMarkedNullable

  companion object BuiltIn {
    const val STRING = "String"
    const val INT = "Int"
    const val BOOL = "Boolean"
    const val FLOAT = "Float"
  }
}

