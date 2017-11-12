package com.prestongarno.ktq.hooks.providers

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.adapters.BooleanArrayDelegate
import com.prestongarno.ktq.adapters.CustomScalarAdapter
import com.prestongarno.ktq.adapters.CustomScalarListAdapter
import com.prestongarno.ktq.adapters.FloatArrayDelegate
import com.prestongarno.ktq.adapters.IntegerArrayDelegate
import com.prestongarno.ktq.adapters.StringArrayDelegate
import com.prestongarno.ktq.adapters.custom.QScalarListMapper
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.stubs.CustomScalarConfigStub
import com.prestongarno.ktq.stubs.CustomScalarInitStub
import com.prestongarno.ktq.stubs.CustomScalarListConfigStub
import com.prestongarno.ktq.stubs.CustomScalarListInitStub

object PrimitiveProvider {

  @JvmStatic fun <T : CustomScalar> createCustomScalarStub(typeName: String): StubProvider<CustomScalarInitStub<T>> =
      Grub(typeName) { CustomScalarAdapter<T, QScalarMapper<T>, T, ArgBuilder>(it) }

  @JvmStatic fun <T : CustomScalar, A : ArgBuilder> createCustomScalarConfig(
      typeName: String
  ): StubProvider<CustomScalarConfigStub<T, A>> =
      Grub(typeName) { CustomScalarAdapter<T, QScalarMapper<T>, T, A>(it) }

  @JvmStatic fun <T : CustomScalar> createCustomScalarListStub(typeName: String): StubProvider<CustomScalarListInitStub<T>>
      = Grub(typeName, true) { CustomScalarListAdapter<T, QScalarListMapper<T>, T, ArgBuilder>(it) }

  @JvmStatic fun <T : CustomScalar, A : ArgBuilder> createCustomScalarListConfig(
      typeName: String
  ): StubProvider<CustomScalarListConfigStub<T, A>> =
      Grub(typeName, true) { CustomScalarListAdapter<T, QScalarListMapper<T>, T, A>(it) }
}
