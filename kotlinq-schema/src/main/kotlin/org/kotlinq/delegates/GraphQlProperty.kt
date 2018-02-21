package org.kotlinq.delegates

import org.kotlinq.Model
import org.kotlinq.api.Adapter
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


interface GraphQlProperty<out T> : ReadOnlyProperty<Model<*>, T>

private
class GraphQlPropertyImpl<out T>(
    val name: String
) : GraphQlProperty<T?> {

  override fun getValue(thisRef: Model<*>, property: KProperty<*>): T? {
    return thisRef.fragment.graphQlInstance.properties[name]?.let {
      @Suppress("UNCHECKED_CAST")
      it.getValue() as? T
    }
  }

}

@Suppress("UNCHECKED_CAST")
internal
fun <T> Adapter.bind(inst: Model<*>): ReadOnlyProperty<Model<*>, T> {
  inst.instanceBuilder?.register(this)
  TODO()
}
