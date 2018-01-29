package org.kotlinq.api

import kotlin.reflect.KType

interface Adapter {
  val name: String
  val type: KType
  fun getValue(): Any?
  /**
   * Callhack for giving this adapter a value
   */
  fun take(value: String): Boolean
}

