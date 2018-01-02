package com.prestongarno.kotlinq.core.properties

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.Adapter
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate.Companion.wrapAsNullable
import kotlin.reflect.KProperty


/**
 * Class to support json arrays of arbitrarily large dimensions
 */
internal
class ListDelegate<out T : Any?>(val adapter: GraphqlPropertyDelegate<T>)
  : GraphqlPropertyDelegate<List<T>>,
    Adapter by adapter {

  private var value: List<T>? = null

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<T> =
      value ?: emptyList()

  override fun asNullable(): GraphqlPropertyDelegate<List<T>?> =
      wrapAsNullable(this, this::value)

  override fun acceptAndReturn(obj: Any?): List<T>? =
      (obj as? JsonObject)?.values?.filterNotNull()?.mapNotNull<Any, T> { adapter.acceptAndReturn(it) } ?: null

  override fun accept(result: Any?): Boolean {
    value = acceptAndReturn(result)
    return true
  }

}

object WhatTheFuckK {
  var foo : List<String?>? = null

  init {
    val fooBar : List<String>? = foo?.filterNotNull()
  }
}
