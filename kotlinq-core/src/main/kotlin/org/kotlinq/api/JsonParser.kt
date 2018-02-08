package org.kotlinq.api


interface JsonParser {

  fun parseToObject(string: String): Map<String, Any?>

  fun parseToArray(string: String): Map<String, Any?>

  companion object : JsonParser by Configuration.jsonParser
}
