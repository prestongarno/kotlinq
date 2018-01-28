package org.kotlinq.api

import org.kotlinq.Model
import org.kotlinq.adapters.Adapter
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType



internal
interface GraphQlProperty<out T> : ReadOnlyProperty<Model<*>, T> {

  val propertyName: String

  val type: KType


  companion object {

    @Suppress("UNCHECKED_CAST")
    internal fun <T> graphQlProperty(
        name: String,
        property: KProperty<*>,
        default: T? = null,
        adapter: Adapter
    ): GraphQlProperty<T> =
        GraphQlPropertyImpl<T>(name, property.returnType, adapter)
            as GraphQlProperty<T>

  }
}

internal
class GraphQlPropertyImpl<out T>(
    override val propertyName: String,
    override val type: KType,
    val adapter: Adapter
) : GraphQlProperty<T?> {

  override fun getValue(thisRef: Model<*>, property: KProperty<*>): T? {
    require(property.returnType == type)
    return adapter.getValue() as? T
  }

}
