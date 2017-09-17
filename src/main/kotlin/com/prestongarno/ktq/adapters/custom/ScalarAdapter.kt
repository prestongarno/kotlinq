package com.prestongarno.ktq.adapters.custom

import com.prestongarno.ktq.QSchemaType
import java.io.InputStream

sealed class ScalarAdapter<out A> : QSchemaType {
  abstract val value: A
}

class InputStreamScalar<out A>(adapter: (InputStream) -> A): ScalarAdapter<A>() {
  override val value: A by lazy { adapter.invoke(rawResponse) }

  internal lateinit var rawResponse: InputStream
}


class StringScalar<out A>(adapter: (String) -> A): ScalarAdapter<A>() {
  override val value: A by lazy { adapter.invoke(rawResponse) }

  internal lateinit var rawResponse: String
}
