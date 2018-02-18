package org.kotlinq.api.services.wrappers

import org.kotlinq.api.JsonParser
import org.kotlinq.api.services.Wrapper

internal
class JsonParsingWrapper(default: JsonParser)
  : Wrapper<JsonParser>(default, JsonParser::class),
    JsonParser {

  override fun parseToObject(string: String, rootObjectName: String) =
      instance().parseToObject(string, rootObjectName)

  override fun parseToArray(string: String) =
      instance().parseToArray(string)
}