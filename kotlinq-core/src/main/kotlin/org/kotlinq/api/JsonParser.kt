package org.kotlinq.api


// TODO module
interface GraphQlJsonParser {

  fun parseToObject(string: String): Sequence<Pair<String, String>>

  fun parseToArray(string: String): List<Map<String, String>>

  companion object : GraphQlJsonParser by TODO() {

  }
}
