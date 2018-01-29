package org.kotlinq.api


// TODO module
interface GraphQlJsonParser {
  fun parseToObject(string: String): Map<String, String>
  fun parseToArray(string: String): List<Map<String, String>>
}
