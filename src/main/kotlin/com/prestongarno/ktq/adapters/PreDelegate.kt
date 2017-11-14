package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.properties.GraphQlProperty

/**
 * Base class for all objects who produce immutable field delegates
 */
internal abstract class PreDelegate(val qproperty: GraphQlProperty)

internal fun <T> T.bind(inst: QModel<*>): T where T : Adapter = apply { inst.register(this) }

internal fun <T : Any> T.applyNotNull(scope: (T.() -> Unit)?): T {
  return scope?.let { this.apply(it) } ?: this
}

internal fun <A : ArgBuilder?> A.toMap(): Map<String, Any> = this?.arguments?.invoke() ?: emptyMap()

