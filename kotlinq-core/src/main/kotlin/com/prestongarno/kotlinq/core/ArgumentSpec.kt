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

@file:Suppress("unused")

package com.prestongarno.kotlinq.core

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface ArgumentSpec {
  val arguments: PropertyMapper
}

/**
 * The supertype of all classes which are used for arguments on GraphQL types.
 * Constructor parameters are arguments which are required by the GraphQL schema,
 * while properties are GraphQL optional arguments
 */
open class ArgBuilder : ArgumentSpec {

  override val arguments = PropertyMapper()

  infix fun String.with(value: Any) {
    arguments.put(this, value)
  }

  fun addArgument(key: String, value: Any) = apply { arguments.put(key, value) }

  internal
  fun getArguments() = arguments
}

class PropertyMapper {

  private
  val values = mutableMapOf<String, Any?>()

  private
  val notNullDelegate = NotNull<Any>()

  @Suppress("UNCHECKED_CAST")
  operator
  fun <T> getValue(inst: Any, property: KProperty<*>): T? =
      values[property.name] as? T
          ?: throw NullPointerException("Property '${property.name} was 'null'")

  operator
  fun <T> setValue(inst: Any, property: KProperty<*>, value: T) {
    values[property.name] = value
  }

  internal
  operator
  fun invoke(): Map<String, Any> = values.mapNotNull { (key, value) ->
    value?.let { key to it }
  }.toMap()

  internal
  fun put(key: String, value: Any) {
    values[key] = value
  }

  @Suppress("UNCHECKED_CAST")
  fun <T: Any> notNull(key: String, value: T): ReadOnlyProperty<Any, T> {
    put(key, value)
    return notNullDelegate as ReadOnlyProperty<Any, T>
  }


  // hack
  private inner class NotNull<out T: Any> : ReadOnlyProperty<Any, T> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any, property: KProperty<*>): T = values[property.name] as T
  }
}

