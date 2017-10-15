package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.internal.FragmentProvider
import com.prestongarno.ktq.internal.ModelProvider
import java.io.InputStream

open class QModel<out T : QSchemaType>(val model: T) {
  internal val fields by lazy { mutableListOf<Adapter>() }

  internal var resolved = false

  internal val graphqlType by lazy { "${model::class.simpleName}" }

  fun isResolved(): Boolean = resolved

  fun toGraphql(pretty: Boolean = true): String {
    return if (pretty) prettyPrinted(0) else fields.joinToString(",", "{", "}") { it.toRawPayload() }
  }

  override fun toString() = "${this::class.simpleName}<${model::class.simpleName}>" +
      fields.joinToString(",", "[", "]") { it.toRawPayload() }

  internal fun onResponse(input: InputStream): Boolean =
      (Parser().parse(input) as? JsonObject)?.let { accept(it) } == true

  internal fun onResponse(input: String) = onResponse(input.byteInputStream())

  internal open fun accept(input: JsonObject): Boolean {
    resolved = fields.filterNot {
      it.accept(input[it.graphqlProperty.graphqlName])
    }.isEmpty()
    return resolved
  }

  internal fun edges() = fields.map {
    when (it) {
      is FragmentProvider -> it.fragments.map { it.model }
      is ModelProvider -> listOf(it.value)
      else -> emptyList()
    }
  }.flatten().toSet()

  internal fun fragmentsToPayload() = fields.filterIsInstance<FragmentProvider>()
      .flatMap { it.fragments }
      .groupBy { it.model.graphqlType }
      .map { (type, allOf) ->
        allOf.mapIndexed { i, generator ->
          "fragment ${type.toLowerCase()}$i on $type ${generator.model.toGraphql(false)}"
        }.joinToString { it }
      }.joinToString { it }

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
}

