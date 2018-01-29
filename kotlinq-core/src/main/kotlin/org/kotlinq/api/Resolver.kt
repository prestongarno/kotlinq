package org.kotlinq.api


// TODO module
interface GraphQlJsonParser {
  fun parseToObject(string: String): Map<String, Any?>
  fun parseToArray(string: String): List<Map<String, Any?>>
}
