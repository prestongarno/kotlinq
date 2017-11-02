package com.prestongarno.ktq

interface UnionInitStub<out T : QSchemaUnion> : SchemaStub {
  fun fragment(what: T.() -> Unit): UnionStub
}

interface UnionConfigStub<out T : QSchemaUnion, out A : ArgBuilder> {
  fun config(on: A.() -> Unit): UnionInitStub<T>
}

interface UnionListInitStub<out T : QSchemaUnion> : SchemaStub {
  fun fragment(what: T.() -> Unit): UnionListStub
}
