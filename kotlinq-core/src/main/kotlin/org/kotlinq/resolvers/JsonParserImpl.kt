package org.kotlinq.resolvers

import com.beust.klaxon.Klaxon
import org.kotlinq.api.JsonParser
import java.io.StringReader


internal
class JsonParserImpl : JsonParser {

  override fun parseToObject(string: String, rootObjectName: String): Map<String, Any?> {
    return Klaxon().parseJsonObject(reader = StringReader(string)).obj(rootObjectName) ?: emptyMap()
  }

  override fun parseToArray(string: String): Iterable<Any> {
    return Klaxon().parseArray(string) ?: emptyList()
  }

}