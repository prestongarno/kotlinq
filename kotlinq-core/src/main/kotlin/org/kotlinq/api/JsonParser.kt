package org.kotlinq.api


interface JsonParser {

  fun parseToObject(string: String): Sequence<Pair<String, String>>

  fun parseToArray(string: String): Sequence<String>

  fun parseFragment(string: String): Pair<String, String>

  companion object : JsonParser by Configuration.jsonParser
}
