package org.kotlinq.context

import org.kotlinq.adapters.ModelAdapter
import org.kotlinq.api.Adapter
import java.lang.ref.WeakReference

internal
interface ContextAware : Adapter {

  // TODO is this a problem?
  val context: WeakReference<ModelAdapter>

  /**
   * Implementations should check if they are null, then request the
   * surrounding context for the value. Goal: idk, maybe achieve some neat way to streamline
   * the parsing process and slow down GC thrashing through repeated malloc when creating the graph
   */
  override fun getValue(): Any?
}