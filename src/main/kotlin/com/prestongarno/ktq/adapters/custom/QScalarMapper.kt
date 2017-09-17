package com.prestongarno.ktq.adapters.custom

import com.prestongarno.ktq.QSchemaType
import java.io.InputStream

sealed class QScalarMapper<out A> : QSchemaType {
  internal abstract val value: A
}

class InputStreamScalarMapper<out A>(adapter: (InputStream) -> A): QScalarMapper<A>() {
  override val value: A by lazy { adapter.invoke(rawResponse) }

  internal lateinit var rawResponse: InputStream
}


class StringScalarMapper<out A>(adapter: (String) -> A): QScalarMapper<A>() {
  override val value: A by lazy { adapter.invoke(rawResponse) }

  internal lateinit var rawResponse: String
}

sealed class QScalarListMapper<out A> : QScalarMapper<List<A>>() {
  abstract override val value: List<A>
}

class InputStreamScalarListMapper<out A>(adapter: (InputStream) -> A): QScalarListMapper<A>() {

  override val value: List<A> by lazy { rawResponse.map { adapter.invoke(it) } }

  internal lateinit var rawResponse: List<InputStream>
}


class StringScalarListMapper<out A>(adapter: (String) -> A): QScalarListMapper<A>() {

  override val value: List<A> by lazy { rawResponse.map { adapter.invoke(it) } }

  internal lateinit var rawResponse: List<String>
}
