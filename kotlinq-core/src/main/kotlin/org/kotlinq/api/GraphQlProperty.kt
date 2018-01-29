package org.kotlinq.api

import org.kotlinq.Model
import org.kotlinq.adapters.Adapter
import org.kotlinq.adapters.AdapterService
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType



interface GraphQlProperty<out T> : ReadOnlyProperty<Model<*>, T> {

  val propertyName: String

  val type: KType


  companion object {

    // TODO module
    val adapterFactory: AdapterService = TODO()

    @Suppress("UNCHECKED_CAST")
    internal fun <T> graphQlProperty(
        name: String,
        property: KProperty<*>,
        default: T? = null
    ): GraphQlProperty<T> =
        GraphQlPropertyImpl<T>(name, property.returnType, TODO()) as GraphQlProperty<T>

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
