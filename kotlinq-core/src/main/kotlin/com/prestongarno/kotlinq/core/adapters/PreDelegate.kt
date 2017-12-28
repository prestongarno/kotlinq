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

package com.prestongarno.kotlinq.core.adapters

import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.properties.GraphQlProperty

/**
 * Base class for all objects who produce immutable field delegates
 *
 * TODO make this a sealed class
 */
internal abstract class PreDelegate<out T : GraphqlPropertyDelegate<V>, out V : Any?>(val qproperty: GraphQlProperty) {
  abstract fun toDelegate(): T

  abstract val flagNullable: (Boolean) -> Unit
}

internal
fun <T : GraphqlPropertyDelegate<*>> T.bind(inst: QModel<*>): T = this.let(inst::register)

internal
fun <T : Any> T.applyNotNull(scope: (T.() -> Unit)?): T {
  return scope?.let { this.apply(it) } ?: this
}

internal
fun <A : ArgumentSpec?> A?.toMap(): Map<String, Any> = this?.arguments?.invoke() ?: emptyMap()

