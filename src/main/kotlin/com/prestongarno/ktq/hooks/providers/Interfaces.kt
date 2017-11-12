package com.prestongarno.ktq.hooks.providers

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.adapters.InterfaceConfigStubImpl
import com.prestongarno.ktq.adapters.InterfaceListConfigStub
import com.prestongarno.ktq.adapters.InterfaceListStub
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.adapters.InterfaceStubImpl
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.stubs.CollectionConfigFragment
import com.prestongarno.ktq.stubs.CollectionFragment
import com.prestongarno.ktq.stubs.InterfaceConfigFragment
import com.prestongarno.ktq.stubs.InterfaceFragment

object InterfaceProvider {

  @JvmStatic fun <T> createInterfaceStub(name: String): StubProvider<InterfaceFragment<T>>
      where T : QType, T : QInterface =
      Grub(name) { InterfaceStubImpl<T>(it) }

  @JvmStatic fun <T, A : ArgBuilder> createInterfaceConfigStub(
      name: String
  ): StubProvider<InterfaceConfigFragment<T, A>> where T : QType, T : QInterface =
      Grub(name) { InterfaceConfigStubImpl<T, A>(it) }

  @JvmStatic fun <T> createCollectionStub(name: String): StubProvider<CollectionFragment<T>>
      where T : QType, T : QInterface =
      Grub(name, true) { InterfaceListStub<T>(it) }

  @JvmStatic fun <T, A : ArgBuilder> createCollectionConfigStub(name: String)
      : StubProvider<CollectionConfigFragment<T, A>> where T : QType, T : QInterface =
      Grub(name, true) { InterfaceListConfigStub<T, A>(it) }
}
