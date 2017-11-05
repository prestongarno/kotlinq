package com.prestongarno.ktq.properties

enum class PropertyType {
  INT,
  BOOLEAN,
  STRING,
  FLOAT,
  ENUM,
  OBJECT,
  CUSTOM_SCALAR;

  companion object {
    fun from(name: String): PropertyType = all[name.toUpperCase()] ?: OBJECT

    private val all = PropertyType.values().map { Pair(it.name, it) }.toMap()
  }
}