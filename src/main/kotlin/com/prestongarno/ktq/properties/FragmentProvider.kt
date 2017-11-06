package com.prestongarno.ktq.properties

import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.hooks.Fragment

/**
 * TODO -> Use [java.util.concurrent.atomic.AtomicReference] instead
 *
 * This is simply a hook into the generated API interface to
 * be able to call a 'SomeUnionObjectType.() -> Unit' on the correct object
 *
 * I still have no idea how this works */
class FragmentProvider {

  private var collector = mutableListOf<Fragment>()

  @Synchronized inline operator fun <I: QSchemaUnion, T : Any> invoke(
      target: I,
      dispatch: I.() -> Unit,
      callback: FragmentProvider.() -> QField<T>
  ): QField<T> {
      dispatch(target)
      return callback(this)
  }

  internal fun reset() = collector.toList().also { collector.clear() }

  /**
   * MUST only be called from a context of this.invoke! (indirectly, ofc...) */
  internal fun addFragment(init: Fragment) { collector.add(init) }
}