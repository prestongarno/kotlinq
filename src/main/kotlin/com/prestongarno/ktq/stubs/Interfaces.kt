package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.InterfaceStub
import com.prestongarno.ktq.QInterfaceType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.hooks.FragmentScope

interface InterfaceFragment<T : QInterfaceType, out A : ArgBuilder> : SchemaStub {
  operator fun invoke(context: FragmentScope<T, A>.() -> Unit): InterfaceStub<T>
}

interface  InterfaceFragmentConfig<T : QInterfaceType, out A : ArgBuilder> : SchemaStub {
  fun config(on: A.() -> Unit): InterfaceFragment<T>
}
