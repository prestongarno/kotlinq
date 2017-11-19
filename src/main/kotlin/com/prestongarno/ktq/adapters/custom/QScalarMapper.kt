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

package com.prestongarno.ktq.adapters.custom

import com.prestongarno.ktq.QSchemaType
import java.io.InputStream

/**
 * Root class for all adapter types which support custom deserialization/mapping typedValueFrom GraphQl values.
 * Currently supports raw byte input stream deserialization and also simple string values */
sealed class QScalarMapper<out A> : QSchemaType {
  /**
   * The converted/mapped value. Lazily evaluated. */
  internal abstract val value: A
}

/**
 * Adapter/Mapper class for creating user-defined objects
 * typedValueFrom a raw byte InputStream typedValueFrom the value of a field */
class InputStreamScalarMapper<out A>(adapter: (InputStream) -> A) : QScalarMapper<A>() {
  override val value: A by lazy { adapter(rawValue) }

  internal lateinit var rawValue: InputStream
}


/**
 * Adapter/Mapper class for creating user-defined objects
 * typedValueFrom the string value of a field */
class StringScalarMapper<out A>(adapter: (String) -> A) : QScalarMapper<A>() {
  override val value: A by lazy { adapter(rawValue) }

  internal lateinit var rawValue: String
}

/**
 * Root class for all 'list' adapter types supporting custom
 * deserialization/mapping typedValueFrom GraphQl field values */
sealed class QScalarListMapper<out A> : QScalarMapper<List<A>>() {
  abstract override val value: List<A>
}

/**
 * Adapter/Mapper class for creating a list of user-defined objects
 * typedValueFrom a raw byte InputStream typedValueFrom the values of a field */
class InputStreamScalarListMapper<out A>(adapter: (InputStream) -> A) : QScalarListMapper<A>() {

  override val value: List<A> by lazy { rawValue.map { adapter(it) } }

  internal lateinit var rawValue: List<InputStream>
}


/**
 * Adapter/Mapper class for creating a list of user-defined
 * objects typedValueFrom the string values of a field */
class StringScalarListMapper<out A>(adapter: (String) -> A) : QScalarListMapper<A>() {

  override val value: List<A> by lazy { rawValue.map { adapter(it) } }

  internal lateinit var rawValue: List<String>
}
