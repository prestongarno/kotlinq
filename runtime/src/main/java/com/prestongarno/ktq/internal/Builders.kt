package com.prestongarno.ktq.internal

import com.prestongarno.ktq.QSchemaType

internal interface BuilderInit<in F> {
  fun <T, A: F> create(init: () -> A) : A
}
internal interface TypeBuilderInit<in F> {
  fun <T: QSchemaType, A: F> create(init: () -> A) : A
}

public interface Payload {
  fun addArg(name: String, value: Any) : Payload
}

