package org.kotlinq.api

import com.github.salomonbrys.kodein.instance


interface JsonParser {

  fun parseToObject(string: String): Map<String, Any?>

  fun parseToArray(string: String): Map<String, Any?>

  companion object : JsonParser by Configuration.kodein.instance()
}
