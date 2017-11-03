package com.prestongarno.ktq.hooks.providers

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.stubs.UnionConfigStub
import com.prestongarno.ktq.stubs.UnionInitStub
import com.prestongarno.ktq.adapters.UnionConfigAdapter
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.StubProvider

object UnionProvider {

  @JvmStatic fun <T : QSchemaUnion> createUnionStub(
      objectModel: T,
      typeName: String
  ): StubProvider<UnionInitStub<T>>
      = Grub(typeName) { UnionConfigAdapter.create(it, objectModel) }

  @JvmStatic fun <T : QSchemaUnion, A : ArgBuilder> createUnionStub(
      objectModel: T,
      typeName: String,
      arginit: (ArgBuilder) -> A
  ): StubProvider<UnionConfigStub<T, A>>
      = Grub(typeName) { UnionConfigAdapter.create(it, objectModel, arginit) }
}

