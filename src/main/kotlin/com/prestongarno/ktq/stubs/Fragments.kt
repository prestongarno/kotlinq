package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType

/**
 * To support no arguments on optional query and restricting use of config { } block
 * TODO add this as supertype for single field interface stub also
 */
interface FragmentStub<in I> where I : QType, I : QInterface {
  /**
   * Create a fragment on an field
   * @param T The concrete type. Bounded by [I] and [QType] */
  fun <T : I> on(initializer: () -> QModel<T>)
}