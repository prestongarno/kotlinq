/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.prestongarno.kotlinq.core

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.kotlinq.core.adapters.Adapter
import com.prestongarno.kotlinq.core.internal.pretty
import java.io.InputStream

/**
 * The base class for creating a GraphQL type model
 *
 * @param model the generated schema object
 * @param T the upper bounds for the type (should be the same as the ^ usually)
 * @author prestongarno
 */
open class QModel<out T : QType>(val model: T) {

  internal
  val fields = mutableMapOf<String, Adapter>()

  internal
  var resolved = false

  internal
  val graphqlType by lazy { "${model::class.simpleName}" }

  fun isResolved(): Boolean = resolved

  @JvmOverloads
  fun toGraphql(pretty: Boolean = false): String =
      if (pretty) pretty() else fields.entries.joinToString(",", "{", "}") {
        it.value.toRawPayload()
      }

  private
  fun onResponse(input: InputStream): Boolean =
      (Parser().parse(input) as? JsonObject)?.let { accept(it) } == true

  internal
  fun onResponse(input: String) = onResponse(input.byteInputStream())

  internal
  fun accept(input: JsonObject): Boolean {
    resolved = fields.filterNot {
      it.value.accept(input[it.value.qproperty.graphqlName])
    }.isEmpty()
    return resolved
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is QModel<*>) return false
    if (graphqlType != other.graphqlType) return false
    if (fields.size != other.fields.size) return false

    return fields.entries.find {
      other.fields[it.key] != it.value
    } == null
  }

  override fun hashCode(): Int =
      fields.entries.fold(initial = graphqlType.hashCode()) { acc, entry ->
        acc * 31 * entry.value.hashCode()
      }

  override fun toString() = "${this::class.simpleName}<${model::class.simpleName}>" +
      fields.entries.joinToString(",", "[", "]") { it.value.qproperty.toString() }

  internal
  fun getFields(): Sequence<Adapter> =
      fields.entries.map { it.value }.asSequence()

  /**
   * Add the field to the instance of this model
   * @param field the Adapter to bind
   * @return the field
   */
  internal
  fun <T : Adapter> register(field: T): T {
    fields[field.qproperty.graphqlName] = field
    return field
  }

}

