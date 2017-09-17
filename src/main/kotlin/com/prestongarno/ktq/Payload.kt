package com.prestongarno.ktq

interface Payload {
  fun addArg(name: String, value: Any) : Payload
}

interface ArgBuilder : Payload {
  fun <T> build(): Stub<T>
}
interface ListArgBuilder : Payload {
  fun <T> build(): ListStub<T>
}
interface TypeArgBuilder : Payload {
  fun <U, T> build(init: () -> U): TypeStub<U, T>
      where U : QModel<T>,
            T : QSchemaType
}
interface TypeListArgBuilder : Payload {
  fun <U, T> build(init: () -> U): TypeListStub<U, T>
      where U : QModel<T>,
            T : QSchemaType
}
