/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.prestongarno.kotlinq.core.internal.resolve

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.kotlinq.core.QModel
import java.io.InputStream

/**
 * Marshall input stream and map to this model's fields
 *
 * @return true if resolved correctly, false if not
 */
fun QModel<*>.resolve(response: InputStream): Boolean {

  resolved = Parser().parse(response)
      ?.let { it as? JsonObject }
      ?.run { accept(this["data"] as JsonObject) }
      ?: false // switches the model to "unresolved" if failing even if it was before

  return resolved

}