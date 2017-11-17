package com.prestongarno.ktq

import com.prestongarno.ktq.hooks.ConfiguredQuery
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.NoArgConfig
import com.prestongarno.ktq.hooks.OptionalConfiguration
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.stubs.BooleanArrayDelegate
import com.prestongarno.ktq.stubs.BooleanDelegate
import com.prestongarno.ktq.stubs.CustomScalarListStub
import com.prestongarno.ktq.stubs.CustomScalarStub
import com.prestongarno.ktq.stubs.EnumListStub
import com.prestongarno.ktq.stubs.EnumStub
import com.prestongarno.ktq.stubs.FloatArrayDelegate
import com.prestongarno.ktq.stubs.FloatDelegate
import com.prestongarno.ktq.stubs.IntArrayDelegate
import com.prestongarno.ktq.stubs.IntDelegate
import com.prestongarno.ktq.stubs.InterfaceListStub
import com.prestongarno.ktq.stubs.InterfaceStub
import com.prestongarno.ktq.stubs.StringArrayDelegate
import com.prestongarno.ktq.stubs.StringDelegate
import com.prestongarno.ktq.stubs.TypeListStub
import com.prestongarno.ktq.stubs.TypeStub
import kotlin.reflect.KClass

/**
 * The root type of all generated schema objects. Nested objects
 * provide handles for field declarations
 * @author prestongarno */
interface QSchemaType {

  object QScalar {
    object String {

      fun stub(): StubProvider<StringDelegate.Query> = Grub("String") { StringDelegate.noArgStub(it) }

      fun <A : ArgBuilder> optionalConfigStub(): StubProvider<StringDelegate.OptionalConfigQuery<A>> =
          Grub("String") { StringDelegate.optionalArgStub<A>(it) }

      fun <A : ArgBuilder> configStub(): StubProvider<StringDelegate.ConfigurableQuery<A>> =
          Grub("String") { StringDelegate.argStub<A>(it) }

    }

    object Int {

      fun stub(): StubProvider<IntDelegate.Query> = Grub("Int") { IntDelegate.noArgStub(it) }

      fun <A : ArgBuilder> optionalConfigStub(): StubProvider<IntDelegate.OptionalConfigQuery<A>> =
          Grub("Int") { IntDelegate.optionalArgStub<A>(it) }

      fun <A : ArgBuilder> configStub(): StubProvider<IntDelegate.ConfigurableQuery<A>> =
          Grub("Int") { IntDelegate.argStub<A>(it) }

    }

    object Float {

      fun stub(): StubProvider<FloatDelegate.Query> = Grub("Float") { FloatDelegate.noArgStub(it) }

      fun <A : ArgBuilder> optionalConfigStub(): StubProvider<FloatDelegate.OptionalConfigQuery<A>> =
          Grub("Float") { FloatDelegate.optionalArgStub<A>(it) }

      fun <A : ArgBuilder> configStub(): StubProvider<FloatDelegate.ConfigurableQuery<A>> =
          Grub("Float") { FloatDelegate.argStub<A>(it) }

    }

    object Boolean {

      fun stub(): StubProvider<BooleanDelegate.Query> = Grub("Boolean") { BooleanDelegate.noArgStub(it) }

      fun <A : ArgBuilder> optionalConfigStub(): StubProvider<BooleanDelegate.OptionalConfigQuery<A>> =
          Grub("Boolean") { BooleanDelegate.optionalArgStub<A>(it) }

      fun <A : ArgBuilder> configStub(): StubProvider<BooleanDelegate.ConfigurableQuery<A>> =
          Grub("Boolean") { BooleanDelegate.argStub<A>(it) }

    }
  }

  object QScalarArray {
    object String {

      fun stub(): StubProvider<StringArrayDelegate.Query> = Grub("String", true) { StringArrayDelegate.noArgStub(it) }

      fun <A : ArgBuilder> optionalConfigStub(): StubProvider<StringArrayDelegate.OptionalConfigQuery<A>> =
          Grub("String") { StringArrayDelegate.optionalArgStub<A>(it) }

      fun <A : ArgBuilder> configStub(): StubProvider<StringArrayDelegate.ConfigurableQuery<A>> =
          Grub("String") { StringArrayDelegate.argStub<A>(it) }

    }

    object Int {

      fun stub(): StubProvider<IntArrayDelegate.Query> = Grub("Int", true) { IntArrayDelegate.noArgStub(it) }

      fun <A : ArgBuilder> optionalConfigStub(): StubProvider<IntArrayDelegate.OptionalConfigQuery<A>> =
          Grub("Int") { IntArrayDelegate.optionalArgStub<A>(it) }

      fun <A : ArgBuilder> configStub(): StubProvider<IntArrayDelegate.ConfigurableQuery<A>> =
          Grub("Int", true) { IntArrayDelegate.argStub<A>(it) }

    }

    object Float {

      fun stub(): StubProvider<FloatArrayDelegate.Query> = Grub("Float", true) { FloatArrayDelegate.noArgStub(it) }

      fun <A : ArgBuilder> optionalConfigStub(): StubProvider<FloatArrayDelegate.OptionalConfigQuery<A>> =
          Grub("Float", true) { FloatArrayDelegate.optionalArgStub<A>(it) }

      fun <A : ArgBuilder> configStub(): StubProvider<FloatArrayDelegate.ConfigurableQuery<A>> =
          Grub("Float", true) { FloatArrayDelegate.argStub<A>(it) }

    }

    object Boolean {

      fun stub(): StubProvider<BooleanArrayDelegate.Query> = Grub("Boolean", true) { BooleanArrayDelegate.noArgStub(it) }

      fun <A : ArgBuilder> optionalConfigStub(): StubProvider<BooleanArrayDelegate.OptionalConfigQuery<A>> =
          Grub("Boolean", true) { BooleanArrayDelegate.optionalArgStub<A>(it) }

      fun <A : ArgBuilder> configStub(): StubProvider<BooleanArrayDelegate.ConfigurableQuery<A>> =
          Grub("Boolean", true) { BooleanArrayDelegate.argStub<A>(it) }

    }
  }

  object QTypes {

    inline fun <reified T : QType> stub(): StubProvider<TypeStub.Query<T>> =
        Grub(T::class.graphQlName()) { TypeStub.noArgStub<T>(it) }

    inline fun <reified T : QType, A : ArgBuilder> optionalConfigStub(): StubProvider<TypeStub.OptionalConfigQuery<T, A>> =
        Grub(T::class.graphQlName()) { TypeStub.optionalArgStub<T, A>(it) }

    inline fun <reified T : QType, A : ArgBuilder> configStub(): StubProvider<TypeStub.ConfigurableQuery<T, A>> =
        Grub(T::class.graphQlName()) { TypeStub.argStub<T, A>(it) }

  }

  object QTypeList {

    inline fun <reified T : QType> stub()
        : StubProvider<TypeListStub.Query<T>> =
        Grub(T::class.graphQlName(), true) { TypeListStub.noArgStub<T>(it) }

    inline fun <reified T : QType, A : ArgBuilder> optionalConfigStub()
        : StubProvider<TypeListStub.OptionalConfigQuery<T, A>> =
        Grub(T::class.graphQlName(), true) { TypeListStub.optionalArgStub<T, A>(it) }

    inline fun <reified T : QType, A : ArgBuilder> configStub()
        : StubProvider<TypeListStub.ConfigurableQuery<T, A>> =
        Grub(T::class.graphQlName(), true) { TypeListStub.argStub<T, A>(it) }

  }

  object QInterfaces {

    inline fun <reified T> stub(): StubProvider<InterfaceStub.Query<T>>
        where T : QType, T : QInterface =
        Grub(T::class.graphQlName()) { InterfaceStub.noArgStub<T>(it) }

    inline fun <reified T, A> optionalConfigStub(): StubProvider<InterfaceStub.OptionalConfigQuery<T, A>>
        where T : QType, T : QInterface, A : ArgBuilder =
        Grub(T::class.graphQlName()) { InterfaceStub.optionalArgStub<T, A>(it) }

    inline fun <reified T, A> configStub(): StubProvider<InterfaceStub.ConfigurableQuery<T, A>>
        where T : QType, T : QInterface, A : ArgBuilder =
        Grub(T::class.graphQlName()) { InterfaceStub.argStub<T, A>(it) }

  }

  object QInterfaceLists {

    inline fun <reified T> stub(): StubProvider<InterfaceListStub.Query<T>>
        where T : QType, T : QInterface =
        Grub(T::class.graphQlName(), true) { InterfaceListStub.noArgStub<T>(it) }

    inline fun <reified T, A> optionalConfigStub(): StubProvider<InterfaceListStub.OptionalConfigQuery<T, A>>
        where T : QType, T : QInterface, A : ArgBuilder =
        Grub(T::class.graphQlName(), true) { InterfaceListStub.optionalArgStub<T, A>(it) }

    inline fun <reified T, A> configStub(): StubProvider<InterfaceListStub.ConfigurableQuery<T, A>>
        where T : QType, T : QInterface, A : ArgBuilder =
        Grub(T::class.graphQlName(), true) { InterfaceListStub.argStub<T, A>(it) }

  }

  object QEnum {
    inline fun <reified T> stub(): StubProvider<NoArgConfig<EnumStub<T, ArgBuilder>, T>> where T : Enum<*>, T : QEnumType
        = Grub(T::class.graphQlName()) { EnumStub.noArgStub(it, T::class) }

    inline fun <reified T, A : ArgBuilder> optionalConfigStub(
    ): StubProvider<OptionalConfiguration<EnumStub<T, A>, T, A>> where T : Enum<*>, T : QEnumType =
        Grub(T::class.graphQlName()) {
          EnumStub.optionalArgStub<T, A>(it, T::class)
        }

    inline fun <reified T, A : ArgBuilder> configStub(): StubProvider<ConfiguredQuery<EnumStub<T, A>, A>> where T : Enum<*>, T : QEnumType
        = Grub(T::class.graphQlName()) { EnumStub.argStub<T, A>(it, T::class) }

  }

  object QEnumLists {

    inline fun <reified T> stub() : StubProvider<EnumListStub.Query<T>>
        where T : Enum<*>, T : QEnumType =
        Grub(T::class.graphQlName(), true) { EnumListStub.noArgStub(it, T::class) }

    inline fun <reified T, A> optionalConfigStub() : StubProvider<EnumListStub.OptionalConfigQuery<T, A>>
        where T : Enum<*>, T : QEnumType, A : ArgBuilder =
        Grub(T::class.graphQlName(), true) { EnumListStub.optionalArgStub<T, A>(it, T::class) }

    inline fun <reified T, A> configStub() : StubProvider<EnumListStub.ConfigurableQuery<T, A>>
        where T : Enum<*>, T : QEnumType, A : ArgBuilder =
        Grub(T::class.graphQlName(), true) { EnumListStub.argStub<T, A>(it, T::class) }

  }

  object QCustomScalar {

    inline fun <reified T : CustomScalar> stub(): StubProvider<CustomScalarStub.Query<T>> =
        Grub(T::class.graphQlName()) { CustomScalarStub.noArgStub<T>(it) }

    inline fun <reified T : CustomScalar, A : ArgBuilder> optionalConfigStub()
        : StubProvider<CustomScalarStub.OptionalConfigQuery<T, A>> =
        Grub(T::class.graphQlName()) { CustomScalarStub.optionalArgStub<T, A>(it) }

    inline fun <reified T : CustomScalar, A : ArgBuilder> configStub():
        StubProvider<CustomScalarStub.ConfigurableQuery<T, A>> =
        Grub(T::class.graphQlName()) { CustomScalarStub.argStub<T, A>(it) }

  }

  object QCustomScalarList {

    inline fun <reified T : CustomScalar> stub(): StubProvider<CustomScalarListStub.Query<T>> =
        Grub(T::class.graphQlName()) { CustomScalarListStub.noArgStub<T>(it) }

    inline fun <reified T : CustomScalar, A : ArgBuilder> optionalConfigStub()
        : StubProvider<CustomScalarListStub.OptionalConfigQuery<T, A>> =
        Grub(T::class.graphQlName()) { CustomScalarListStub.optionalArgStub<T, A>(it) }

    inline fun <reified T : CustomScalar, A : ArgBuilder> configStub():
        StubProvider<CustomScalarListStub.ConfigurableQuery<T, A>> =
        Grub(T::class.graphQlName()) { CustomScalarListStub.argStub<T, A>(it) }

  }

  object QUnion

  object QUnionList

}

@Suppress("NOTHING_TO_INLINE") @PublishedApi internal inline fun <T : Any> KClass<T>.graphQlName() = "${this.simpleName}"
