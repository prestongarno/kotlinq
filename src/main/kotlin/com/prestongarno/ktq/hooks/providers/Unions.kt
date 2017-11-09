package com.prestongarno.ktq.hooks.providers

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QUnionType
import com.prestongarno.ktq.stubs.UnionConfigStub
import com.prestongarno.ktq.stubs.UnionInitStub
import com.prestongarno.ktq.adapters.UnionConfigAdapter
import com.prestongarno.ktq.adapters.UnionListConfigAdapter
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.stubs.UnionListInitStub

object UnionProvider {

  @JvmStatic fun <T : QUnionType> createUnionStub(
      objectModel: T,
      typeName: String
  ): StubProvider<UnionInitStub<T>>
      = Grub(typeName) { UnionConfigAdapter.create(it, objectModel) }

  @JvmStatic fun <T : QUnionType, A : ArgBuilder> createUnionStub(
      objectModel: T,
      typeName: String,
      arginit: (ArgBuilder) -> A
  ): StubProvider<UnionConfigStub<T, A>>
      = Grub(typeName) { UnionConfigAdapter.create(it, objectModel, arginit) }

  @JvmStatic fun <T : QUnionType> createUnionListStub(
      objectModel: T
  ): StubProvider<UnionListInitStub<T>> = Grub("${objectModel::class.simpleName}", true) {
    UnionListConfigAdapter.create(it, objectModel)
  }
}

