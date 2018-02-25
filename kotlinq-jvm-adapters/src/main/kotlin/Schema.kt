package org.kotlinq.api.schema

import org.kotlinq.api.Fragment
import org.kotlinq.api.GraphVisitor


class Schema private constructor(val typeMappers: Map<String, TypeMapping<*>>) {

  fun canResolve(fragment: Fragment): Boolean {
    var result = true
    GraphVisitor.builder()
        .onNotifyEnter {
          if (typeMappers[it.typeName] == null) {
            result = false
          }
          result
        }.build()
        .let(fragment::traverse)
    return result
  }

  @Suppress("UNCHECKED_CAST")
  fun <T> resolve(fragment: Fragment, values: Map<String, Any>): T? {
    if (!canResolve(fragment))
      throw IllegalStateException("Schema is missing type definitions")
    return typeMappers[fragment.typeName]?.init?.invoke(values) as? T
  }

  class Builder {
    private val map = mutableMapOf<String, TypeMapping<*>>()

    fun define(typeMapper: TypeMapping<*>) =
        apply { map[typeMapper.typeName] = typeMapper }

    fun build() = Schema(map)
  }
}


class TypeMapping<out T>(
    val typeName: String,
    val init: (Map<String, Any?>) -> T)
