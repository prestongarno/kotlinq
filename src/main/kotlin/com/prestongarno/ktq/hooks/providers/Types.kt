package com.prestongarno.ktq.hooks.providers

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.stubs.ListConfigType
import com.prestongarno.ktq.stubs.ListInitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.adapters.TypeListAdapter
import com.prestongarno.ktq.adapters.TypeStubAdapter
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.Grub.Companion.standardGenerator
import com.prestongarno.ktq.hooks.InitStub
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.hooks.TypeConfig

object TypeProvider {

  @JvmStatic fun <T : QSchemaType> createTypeStub(name: String): StubProvider<InitStub<T>>
      = Grub(name) { TypeStubAdapter<T, QModel<T>, ArgBuilder>(it, standardGenerator) }

  @JvmStatic fun <T : QSchemaType, A : ArgBuilder> createTypeStub(
      name: String, arginit: (ArgBuilder) -> A
  ): StubProvider<TypeConfig<T, A>> = Grub(name) {
    TypeStubAdapter<T, QModel<T>, A>(it, arginit)
  }

  @JvmStatic fun <A : ArgBuilder, T : QSchemaType> createTypeConfigStub(
      typeName: String,
      arginit: (ArgBuilder) -> A
  ): StubProvider<TypeConfig<T, A>>
      = Grub(typeName) { TypeStubAdapter<T, QModel<T>, A>(it, arginit) }

  @JvmStatic fun <T : QSchemaType> createTypeListStub(typeName: String): StubProvider<ListInitStub<T>> =
      Grub(typeName, true) { TypeListAdapter<T, QModel<T>, ArgBuilder>(it, standardGenerator) }

  @JvmStatic fun <A : ArgBuilder, T : QSchemaType> createTypeListConfigStub(
      typeName: String,
      arginit: (ArgBuilder) -> A
  ): StubProvider<ListConfigType<T, A>> =
      Grub(typeName, true) { TypeListAdapter<T, QModel<T>, A>(it, arginit) }
}
