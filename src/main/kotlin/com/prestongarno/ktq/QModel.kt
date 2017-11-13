package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.ktq.adapters.Adapter
import java.io.InputStream

open class QModel<out T : QType>(val model: T) {

  // TODO(preston) make this a map, because gql symbols
  // by name need to be constantly looked up
  internal val fields = mutableMapOf<String, Adapter>()

  internal var resolved = false

  internal val graphqlType by lazy { "${model::class.simpleName}" }

  fun isResolved(): Boolean = resolved

  fun toGraphql(pretty: Boolean = true): String {
    return if (pretty) prettyPrinted(0) else fields.entries.joinToString(",", "{", "}") {
      it.value.toRawPayload()
    }
  }

  private fun onResponse(input: InputStream): Boolean =
      (Parser().parse(input) as? JsonObject)?.let { accept(it) } == true

  internal fun onResponse(input: String) = onResponse(input.byteInputStream())

  internal open fun accept(input: JsonObject): Boolean {
    resolved = fields.filterNot {
      it.value.accept(input[it.value.qproperty.graphqlName])
    }.isEmpty()
    return resolved
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as QModel<*>

    if (model != other.model) return false
    if (resolved != other.resolved) return false
    if (!fields.entries.containsAll(other.fields.entries)) return false

    return true
  }

  override fun hashCode(): Int {
    var result = model.hashCode()
    result = 31 * result + resolved.hashCode()
    return result
  }

  override fun toString() = "${this::class.simpleName}<${model::class.simpleName}>" +
      fields.entries.joinToString(",", "[", "]") { it.value.qproperty.toString() }

  internal fun getFields(): Sequence<Adapter> =
      fields.entries.asSequence().map { it.value }

  /**
   * Add the field to the instance of this model
   * @param field the Adapter to bind
   * @return the field
   */
  internal fun <T : Adapter> register(field: T): T {
    fields[field.qproperty.graphqlName] = field
    return field
  }

}

inline fun <reified T : QType> graphQl(model: T, scope: QModel<T>.() -> Unit) =
    (object : QModel<T>(model) { }).apply(scope)

