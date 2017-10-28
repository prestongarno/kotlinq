/**For organizing the utility objects/classes which provide stubs for schema types
 */
package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.QField
import kotlin.reflect.KProperty

internal interface ModelProvider {
  val value: QModel<*>
}

interface FragmentProvider {
  val fragments: Set<FragmentGenerator>
}

data class FragmentGenerator(val initializer: () -> QModel<*>) {
  internal val model by lazy(initializer)
}

fun <T> nullPointer(): () -> T = { throw NullPointerException() }
interface DelegateProvider<out T> : SchemaStub {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>
}