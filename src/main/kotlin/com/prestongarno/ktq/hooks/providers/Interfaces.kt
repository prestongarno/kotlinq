package com.prestongarno.ktq.hooks.providers

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.adapters.InterfaceListStub
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.adapters.InterfaceStubImpl
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.stubs.CollectionFragment
import com.prestongarno.ktq.stubs.InterfaceFragment

object InterfaceProvider {

  @JvmStatic fun <T> createInterfaceStub(name: String): StubProvider<InterfaceFragment<T, ArgBuilder>>
      where T : QType, T : QInterface =
      Grub(name) { InterfaceStubImpl<T, ArgBuilder>(it, Grub.standardGenerator) }

  @JvmStatic fun <T, A : ArgBuilder> createInterfaceStub(
      name: String,
      arginit: (ArgBuilder) -> A
  ): StubProvider<InterfaceFragment<T, A>> where T : QType, T : QInterface =
      Grub(name) { InterfaceStubImpl<T, A>(it, arginit) }

  @JvmStatic fun <T> createCollectionStub(name: String): StubProvider<CollectionFragment<T, ArgBuilder>>
      where T : QType, T : QInterface =
      Grub(name, true) { InterfaceListStub<T, ArgBuilder>(it, Grub.standardGenerator) }

  @JvmStatic fun <T, A : ArgBuilder> createCollectionStub(name: String, arginit: (ArgBuilder) -> A)
      : StubProvider<CollectionFragment<T, A>> where T : QType, T : QInterface =
      Grub(name, true) { InterfaceListStub<T, A>(it, arginit) }
}
