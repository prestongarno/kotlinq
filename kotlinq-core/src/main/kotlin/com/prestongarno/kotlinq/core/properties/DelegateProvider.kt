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

package com.prestongarno.kotlinq.core.properties

import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.QField
import kotlin.reflect.KProperty

interface GraphQLPropertyContext<out T : Any?> {
  // TODO overriders should be more restrictive of the type so DSL is still enabled
  fun asNullable(): GraphQLPropertyContext<T?>
}


interface DelegateProvider<out T> {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>
}

interface ConfigurableDelegateProvider<out U : GraphQLPropertyContext<T>, out T> : DelegateProvider<T> {
  fun invoke(block: U.() -> Unit): DelegateProvider<T>
}

/** Grand Unified bootloader... but this time for instance delegation rather than static schema types */
internal
fun <T> delegateProvider(init: (QModel<*>) -> QField<T>) = object : DelegateProvider<T> {
  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) = init(inst)
}

internal
fun <U, T> configurableDelegate(
    context: U,
    init: (QModel<*>) -> QField<T>
) where U : GraphQLPropertyContext<T> =

    object : ConfigurableDelegateProvider<U, T> {
      override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> = init(inst)
      override fun invoke(block: U.() -> Unit): DelegateProvider<T> = apply { context.apply(block) }
    }
