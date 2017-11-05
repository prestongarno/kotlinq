package com.prestongarno.ktq.properties

import com.prestongarno.ktq.QSchemaUnion

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

  @Synchronized internal fun put(value: QSchemaUnion) {
    this.value = value
  }

  @Synchronized internal fun pop() {
    value = null
  }

  fun get() = value
}