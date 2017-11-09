package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.ktq.adapters.Adapter
import java.io.InputStream

@kotlin.Suppress("AddVarianceModifier")
open class QModel<T : QInterfaceType>(val model: T) {

  // TODO(preston) make this a map, because gql symbols
  // by name need to be constantly looked up
  internal val fields by lazy { mutableListOf<Adapter>() }

  internal var resolved = false

  internal val graphqlType by lazy { "${model::class.simpleName}" }

  fun isResolved(): Boolean = resolved

  fun toGraphql(pretty: Boolean = true): String {
    return if (pretty) prettyPrinted(0) else fields.joinToString(",", "{", "}") {
      it.toRawPayload()
    }
  }

  private fun onResponse(input: InputStream): Boolean =
      (Parser().parse(input) as? JsonObject)?.let { accept(it) } == true

  internal fun onResponse(input: String) = onResponse(input.byteInputStream())

  internal open fun accept(input: JsonObject): Boolean {
    resolved = fields.filterNot {
      it.accept(input[it.qproperty.graphqlName])
    }.isEmpty()
    return resolved
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as QModel<*>

    if (model != other.model) return false
    if (resolved != other.resolved) return false
    if (!fields.containsAll(other.fields)) return false

    return true
  }

  override fun hashCode(): Int {
    var result = model.hashCode()
    result = 31 * result + resolved.hashCode()
    return result
  }

  override fun toString() = "${this::class.simpleName}<${model::class.simpleName}>" +
      fields.joinToString(",", "[", "]") { it.toRawPayload() }

}

