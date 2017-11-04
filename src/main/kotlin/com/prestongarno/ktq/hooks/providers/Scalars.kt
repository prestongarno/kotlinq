package com.prestongarno.ktq.hooks.providers

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.CustomScalarArgBuilder
import com.prestongarno.ktq.stubs.CustomScalarConfigStub
import com.prestongarno.ktq.stubs.CustomScalarInitStub
import com.prestongarno.ktq.CustomScalarListArgBuilder
import com.prestongarno.ktq.stubs.CustomScalarListConfigStub
import com.prestongarno.ktq.stubs.CustomScalarListInitStub
import com.prestongarno.ktq.adapters.BooleanArrayDelegate
import com.prestongarno.ktq.adapters.BooleanDelegate
import com.prestongarno.ktq.adapters.CustomScalarAdapter
import com.prestongarno.ktq.adapters.CustomScalarListAdapter
import com.prestongarno.ktq.adapters.FloatArrayDelegate
import com.prestongarno.ktq.adapters.FloatDelegate
import com.prestongarno.ktq.adapters.IntegerArrayDelegate
import com.prestongarno.ktq.adapters.IntegerDelegate
import com.prestongarno.ktq.adapters.StringArrayDelegate
import com.prestongarno.ktq.adapters.StringDelegate
import com.prestongarno.ktq.adapters.custom.QScalarListMapper
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.Grub.Companion.standardGenerator
import com.prestongarno.ktq.hooks.StubProvider

object PrimitiveProvider {

  @JvmStatic fun createStringDelegate(): StubProvider<StringDelegate<ArgBuilder>> =
      Grub("String") { StringDelegate(it, standardGenerator) }

  @JvmStatic fun createIntDelegate(): StubProvider<IntegerDelegate<ArgBuilder>> =
      Grub("Int") { IntegerDelegate(it, standardGenerator) }

  @JvmStatic fun createFloatDelegate(): StubProvider<FloatDelegate<ArgBuilder>> =
      Grub("Float") { FloatDelegate(it, standardGenerator) }

  @JvmStatic fun createBooleanDelegate(): StubProvider<BooleanDelegate<ArgBuilder>> =
      Grub("Boolean") { BooleanDelegate(it, standardGenerator) }

  @JvmStatic fun <A : ArgBuilder> createStringDelegate(arginit: (ArgBuilder) -> A) =
      Grub("String") { StringDelegate(it, arginit) }

  @JvmStatic fun <A : ArgBuilder> createIntDelegate(arginit: (ArgBuilder) -> A) =
      Grub("Int") { IntegerDelegate(it, arginit) }

  @JvmStatic fun <A : ArgBuilder> createFloatDelegate(arginit: (ArgBuilder) -> A)
      = Grub("Float") { FloatDelegate(it, arginit) }

  @JvmStatic fun <A : ArgBuilder> createBooleanDelegate(arginit: (ArgBuilder) -> A)
      = Grub("Boolean") { BooleanDelegate(it, arginit) }


  @JvmStatic fun createStringArrayDelegate() = Grub("String") { StringArrayDelegate(it, standardGenerator) }

  @JvmStatic fun createIntArrayDelegate() = Grub("Int") { IntegerArrayDelegate(it, standardGenerator) }

  @JvmStatic fun createFloatArrayDelegate() = Grub("Float") { FloatArrayDelegate(it, standardGenerator) }

  @JvmStatic fun createBooleanArrayDelegate() = Grub("Boolean") { BooleanArrayDelegate(it, standardGenerator) }

  @JvmStatic fun <A : ArgBuilder> createStringArrayDelegate(arginit: (ArgBuilder) -> A) =
      Grub("String") { StringArrayDelegate(it, arginit) }

  @JvmStatic fun <A : ArgBuilder> createIntArrayDelegate(arginit: (ArgBuilder) -> A) =
      Grub("Int") { IntegerArrayDelegate(it, arginit) }

  @JvmStatic fun <A : ArgBuilder> createFloatArrayDelegate(arginit: (ArgBuilder) -> A) =
      Grub("Float") { FloatArrayDelegate(it, arginit) }

  @JvmStatic fun <A : ArgBuilder> createBooleanArrayDelegate(arginit: (ArgBuilder) -> A) =
      Grub("Boolean") { BooleanArrayDelegate(it, arginit) }

  @JvmStatic fun <T : CustomScalar> createCustomScalarStub(typeName: String): StubProvider<CustomScalarInitStub<T>> =
      Grub(typeName) { CustomScalarAdapter<T, QScalarMapper<T>, T, CustomScalarArgBuilder>(it, { it }) }

  @JvmStatic fun <T : CustomScalar, A : CustomScalarArgBuilder> createCustomScalarConfig(
      typeName: String,
      arginit: (CustomScalarArgBuilder) -> A
  ): StubProvider<CustomScalarConfigStub<T, A>> =
      Grub(typeName) { CustomScalarAdapter<T, QScalarMapper<T>, T, A>(it, arginit) }

  @JvmStatic fun <T : CustomScalar> createCustomScalarListStub(typeName: String): StubProvider<CustomScalarListInitStub<T>>
      = Grub(typeName, true) { CustomScalarListAdapter<T, QScalarListMapper<T>, T, CustomScalarListArgBuilder>(it, { it }) }

  @JvmStatic fun <T : CustomScalar, A : CustomScalarListArgBuilder> createCustomScalarListConfig(
      typeName: String,
      arginit: (CustomScalarListArgBuilder) -> A
  ): StubProvider<CustomScalarListConfigStub<T, A>> =
      Grub(typeName, true) { CustomScalarListAdapter<T, QScalarListMapper<T>, T, A>(it, arginit) }
}
