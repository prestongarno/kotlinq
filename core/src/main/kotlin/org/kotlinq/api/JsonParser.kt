package org.kotlinq.api

import org.kotlinq.api.services.Configuration


/**
 * Provides low-level parsing functionality for resolving queries
 *
 * @author prestongarno
 */
interface JsonParser {

  fun parseToObject(string: String, rootObjectName: String = "data"): Map<String, Any?>

  fun parseToArray(string: String): Iterable<Any>

  companion object : JsonParser by Configuration.instance()
}
