package com.prestongarno.ktq.hooks.providers

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.EnumStub
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.adapters.EnumAdapterImpl
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.StubProvider
import kotlin.reflect.KClass

@PublishedApi internal object EnumProvider {

  @JvmStatic fun <T> createEnumStub(
      typeName: String,
      clazz: KClass<T>
  ): StubProvider<EnumStub<T>>
      where T : QEnumType,
            T : Enum<*> =
      Grub(typeName) { EnumAdapterImpl<T, ArgBuilder>(it, clazz) }

  @JvmStatic fun <T, A : ArgBuilder> createEnumConfigStub(
      typeName: String,
      clazz: KClass<T>
  ): StubProvider<EnumStub<T>>
      where T : QEnumType,
            T : Enum<*> =
      Grub(typeName, true) { EnumAdapterImpl<T, A>(it, clazz) }
}

