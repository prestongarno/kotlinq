package com.prestongarno.ktq.properties

import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.hooks.FragmentGenerator
import com.prestongarno.ktq.hooks.FragmentProvider

/**
 * TODO -> Use [java.util.concurrent.atomic.AtomicReference] instead
 *
 * This is simply a hook into the generated API interface to
 * be able to call a 'SomeUnionObjectType.() -> Unit' on the correct object
 *
 * I still have no idea how this works */
@Deprecated("Just my own version of an AtomicReference<QSchemaUnion>")
class DispatchQueue {

  @Volatile private var value: QSchemaUnion? = null
  private var collector = mutableListOf<FragmentGenerator>()

  operator fun <I: QSchemaUnion, T : Any> invoke(
      target: I,
      dispatch: I.() -> Unit,
      callback: DispatchQueue.() -> QField<T>): QField<T> {

    synchronized(this) {
      dispatch(target)
      return callback(this)
    }
  }

  internal fun reset() = collector.toList().also { collector.clear() }

  internal fun addFragment(init: FragmentGenerator) { collector.add(init) }

  @Synchronized internal fun put(value: QSchemaUnion) {
    this.value = value
  }

  @Synchronized internal fun pop() {
    value = null
  }

  fun get() = value
}