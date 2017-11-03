package com.prestongarno.ktq.hooks.providers

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.EnumStub
import com.prestongarno.ktq.QSchemaEnum
import com.prestongarno.ktq.adapters.EnumAdapter
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.Grub.Companion.standardGenerator
import com.prestongarno.ktq.hooks.StubProvider
import kotlin.reflect.KClass

object EnumProvider {

  @JvmStatic fun <T> createEnumStub(
      typeName: String,
      clazz: KClass<T>
  ): StubProvider<EnumStub<T>>
      where T : QSchemaEnum,
            T : Enum<*> =
      Grub(typeName) { EnumAdapter(it, clazz, standardGenerator) }

  @JvmStatic fun <T, A : ArgBuilder> createEnumConfigStub(
      typeName: String,
      clazz: KClass<T>,
      arginit: (ArgBuilder) -> A
  ): StubProvider<EnumStub<T>>
      where T : QSchemaEnum,
            T : Enum<*> =
      Grub(typeName, true) { EnumAdapter(it, clazz, arginit) }
}

