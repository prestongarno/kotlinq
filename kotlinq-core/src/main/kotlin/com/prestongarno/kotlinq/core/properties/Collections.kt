package com.prestongarno.kotlinq.core.properties

import com.beust.klaxon.JsonObject
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.Adapter
import com.prestongarno.kotlinq.core.adapters.GraphQlField
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate.Companion.wrapAsNullable
import com.prestongarno.kotlinq.core.properties.GraphQlProperty.Companion.from
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import kotlin.reflect.KProperty


/**
 * support lists/arrays of arbitrarily nested dimensions
 */
internal
class ListDelegate<out T : Any>(val adapter: GraphqlPropertyDelegate<T>)
  : GraphqlPropertyDelegate<List<T>>,
    Adapter by adapter {

  private var value: List<T>? = null

  override val qproperty: GraphQlProperty = adapter.qproperty.toList()

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<T> =
      value ?: emptyList()

  override fun asNullable(): GraphqlPropertyDelegate<List<T>?> =
      wrapAsNullable(this, this::value)

  override fun asList(): GraphqlPropertyDelegate<List<List<T>>> = ListDelegate(this)

  override fun transform(obj: Any?): List<T>? =
      (obj as? JsonObject)
          ?.values
          ?.filterNotNull()
          ?.mapNotNull(adapter::transform)

  override fun accept(result: Any?): Boolean {
    value = transform(result)
    return true
  }

}

internal
class NullableElementListDelegate<out T : Any?>(val adapter: GraphqlPropertyDelegate<T>)
  : GraphqlPropertyDelegate<List<T?>>,
    Adapter by adapter {

  private var value: List<T?>? = null

  override val qproperty: GraphQlProperty = adapter.qproperty.toList()

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<T?> =
      value ?: emptyList()

  override fun asNullable(): GraphqlPropertyDelegate<List<T?>?> =
      wrapAsNullable(this, this::value)

  override fun asList(): GraphqlPropertyDelegate<List<List<T?>>> =
      ListDelegate(this)

  override fun transform(obj: Any?): List<T?>? =
      (obj as? JsonObject)
          ?.entries
          ?.map(MutableMap.MutableEntry<String, Any?>::value)
          ?.map(adapter::transform)
}

internal
fun <T> collectionDelegate(
    constructor: (QModel<*>, KProperty<*>) -> GraphqlPropertyDelegate<T>
) = object : DelegateProvider<List<T>> {

      override fun provideDelegate(inst: QModel<*>, property: KProperty<*>)
          : GraphQlField<List<T>> =
          constructor(inst, property).asList()
    }


internal
fun GraphQlProperty.toList() = if (!isList) from(
    graphqlType = graphqlType,
    graphqlName = graphqlName,
    isList = true
) else
  this

