package org.kotlinq.api


// TODO module
interface GraphQlJsonParser {

  fun parseToObject(string: String): Sequence<Pair<String, String>>

  fun parseToArray(string: String): List<String>


  companion object : GraphQlJsonParser by TODO()
}
