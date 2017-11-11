package com.prestongarno.ktq.hooks.providers

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.adapters.InterfaceFragmentAdapter
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.stubs.InterfaceFragment

//@JvmStatic fun <T : QType> createTypeStub(name: String): StubProvider<InitStub<T>>
// = Grub(name) { TypeStubAdapter<T, QModel<T>, ArgBuilder>(it, standardGenerator) }
object InterfaceProvider {

  @JvmStatic fun <T : QType> createInterfaceStub(name: String)
      : StubProvider<InterfaceFragment<T, ArgBuilder>> = Grub(name) {
    InterfaceFragmentAdapter<T, ArgBuilder>(it, Grub.standardGenerator)
  }

  @JvmStatic fun <T : QType, A : ArgBuilder> createInterfaceStub(
      name: String,
      arginit: (ArgBuilder) -> A
  ): StubProvider<InterfaceFragment<T, A>> = Grub(name) {
    InterfaceFragmentAdapter<T, A>(it, arginit)
  }
}
