package com.prestongarno.ktq.hooks.providers

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.stubs.ListConfigType
import com.prestongarno.ktq.stubs.ListInitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.adapters.TypeConfigStub
import com.prestongarno.ktq.adapters.TypeListAdapter
import com.prestongarno.ktq.adapters.TypeStubAdapter
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.Grub.Companion.standardGenerator
import com.prestongarno.ktq.hooks.InitStub
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.hooks.TypeConfig

object TypeProvider {

  @JvmStatic fun <T : QType> createTypeStub(name: String): StubProvider<InitStub<T, ArgBuilder>>
      = Grub(name) { TypeStubAdapter<T, QModel<T>, ArgBuilder>(it) }

  @JvmStatic fun <T : QType, A : ArgBuilder> createTypeConfigStub(name: String
  ): StubProvider<TypeConfig<T, A>> = Grub(name) {
    TypeConfigStub<T, A>(it)
  }

  @JvmStatic fun <T : QType> createTypeListStub(typeName: String): StubProvider<ListInitStub<T, ArgBuilder>> =
      Grub(typeName, true) { TypeListAdapter<T, QModel<T>, ArgBuilder>(it) }

  @JvmStatic fun <A : ArgBuilder, T : QType> createTypeListConfigStub(
      typeName: String
  ): StubProvider<ListConfigType<T, A>> =
      Grub(typeName, true) { TypeListAdapter<T, QModel<T>, A>(it) }
}

