/**For organizing the utility objects/classes which provide stubs for schema types
 */
package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QInterfaceType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.QField
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal interface ModelProvider {
  val value: QModel<*>
}

interface DelegateProvider<out T> : SchemaStub {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>
}

/**
 * Scope of fragmenting on a GraphQL field
 * @param I : the interface type that this fragment represents*/
interface FragmentScope<in I : QInterfaceType, out A : ArgBuilder> {
  /**
   * Create a fragment on an field
   * @param T The concrete type. Bounded by [I] and [QType]*/
  infix fun <T : I> Any.on(initializer: () -> QModel<T>)
}

internal interface FragmentContext<in I : QInterfaceType, out A : ArgBuilder> : FragmentScope<I, A> {
  val fragments: Set<Fragment>
}

data class Fragment(val initializer: () -> QModel<*>) {
  internal val model by lazy(initializer)
}

internal fun <T> nullPointer() = object: ReadOnlyProperty<Any, T> {
  override operator fun getValue(thisRef: Any, property: KProperty<*>): T =
    throw NullPointerException("Null: ${property.name}")
}
