package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.InterfaceStub
import com.prestongarno.ktq.QInterfaceType
import com.prestongarno.ktq.SchemaStub

interface InterfaceFragment<out T : QInterfaceType> : SchemaStub {
  fun fragment(on: T.() -> Unit): InterfaceStub<T>
}

interface  InterfaceFragmentConfig<out T : QInterfaceType, out A : ArgBuilder> : SchemaStub {
  fun config(on: A.() -> Unit): InterfaceFragment<T>
}
