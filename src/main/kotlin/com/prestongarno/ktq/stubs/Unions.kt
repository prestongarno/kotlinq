package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QUnionType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.UnionStub

interface UnionInitStub<out T : QUnionType> : SchemaStub {
  fun fragment(what: T.() -> Unit): UnionStub
}

interface UnionConfigStub<out T : QUnionType, out A : ArgBuilder> : SchemaStub {
  fun config(on: A.() -> Unit): UnionInitStub<T>
}

interface UnionListInitStub<out T : QUnionType> : SchemaStub {
  fun fragment(what: T.() -> Unit): UnionListStub
}
