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
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.properties.GraphQlProperty

/**
 * Base class for all objects who produce immutable field delegates
 * @param T the type of object that the resulting [GraphqlPropertyDelegate] provides
 * @param A the arguments required for this field
 */
internal
abstract class PreDelegate<out T: Any?, out A : ArgumentSpec> : GraphqlDslBuilder<A> {

  abstract fun toDelegate(property: GraphQlProperty): GraphqlPropertyDelegate<T>

}

internal
fun <T : Any> T.applyNotNull(scope: (T.() -> Unit)?): T =
    scope?.let { this.apply(it) } ?: this

internal
fun <A : ArgumentSpec?> A?.toMap(): Map<String, Any> =
    this?.arguments
        ?.invoke()
        ?: emptyMap()

