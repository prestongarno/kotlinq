/*
 * Copyright (C) 2018 Preston Garno
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

package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.adapters.BooleanArrayStub
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.adapters.toMap
import com.prestongarno.kotlinq.core.api.ConfiguredProvider
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder.DefaultBuilder.Companion.configuredContext
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder.DefaultBuilder.Companion.noArgContext
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder.DefaultBuilder.Companion.optionallyConfiguredContext
import com.prestongarno.kotlinq.core.api.Grub
import com.prestongarno.kotlinq.core.api.NoArgProvider
import com.prestongarno.kotlinq.core.api.OptionallyConfiguredProvider
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.GraphQlPropertyContext.Companion.Builder

internal
object BooleanArrayDelegates {

}

private
fun notNullNoArg(property: GraphQlProperty): GraphqlDslBuilder.NoArgContext<BooleanArray> =
    noArgContext(BooleanArray(0), newCtor(property))

private
fun nullableNoArg(property: GraphQlProperty) =
    noArgContext(BooleanArray(0), newNullableCtor(property))

private
fun <A : ArgumentSpec> optionallyNotNullConfigured(property: GraphQlProperty) =
    optionallyConfiguredContext<BooleanArray, A>(BooleanArray(0), newCtor(property))

private
fun <A : ArgumentSpec> optionallyNullableConfigured(property: GraphQlProperty) =
    optionallyConfiguredContext<BooleanArray?, A>(BooleanArray(0), newNullableCtor(property))

private
fun <A : ArgumentSpec> configured(property: GraphQlProperty) =
    configuredContext<BooleanArray, A>(BooleanArray(0), newCtor(property))

private fun <A : ArgumentSpec> newCtor(property: GraphQlProperty):
    (Pair<A?, GraphqlDslBuilder.DefaultBuilder<BooleanArray, A>>) -> GraphqlPropertyDelegate<BooleanArray> =
    { (args, builder) -> BooleanArrayStub(property, builder.default, args.toMap()) }

private fun <A : ArgumentSpec> newNullableCtor(property: GraphQlProperty):
    (Pair<A?, GraphqlDslBuilder.DefaultBuilder<BooleanArray?, A>>) -> GraphqlPropertyDelegate<BooleanArray?> =
    { (args, builder) -> BooleanArrayStub(property, builder.default, args.toMap()).asNullable() }

