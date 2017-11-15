package com.prestongarno.ktq

import com.prestongarno.ktq.hooks.ConfiguredQuery
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.NoArgConfig
import com.prestongarno.ktq.hooks.OptionalConfiguration
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.stubs.BooleanDelegate
import com.prestongarno.ktq.stubs.EnumStub
import com.prestongarno.ktq.stubs.FloatDelegate
import com.prestongarno.ktq.stubs.IntDelegate
import com.prestongarno.ktq.stubs.IntStub
import com.prestongarno.ktq.stubs.InterfaceListStub
import com.prestongarno.ktq.stubs.InterfaceStub
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
  }

  object QCustomScalar {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} fields
     * fragment schema-defined scalar types
     * @param T The type argument for the createStub, typedValueFrom a schema-defined CustomScalar type
     * @return Grub<CustomScalarInitStub<T>> the delegate which lazily provides a CustomScalarInitStub<T> */
    //inline fun <reified T : CustomScalar> stub(): StubProvider<CustomScalarInitStub<T>> = createCustomScalarStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} fields
     * fragment schema-defined scalar types which take input argBuilder
     * @param arginit an initializer for the createStub field. <b>Important for auto-generated schema definitions</b>
     * @param T type of the custom scalar
     * @param A type argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarConfigStub<T, A>> the delegate which lazily provides a CustomScalarConfigStub<T, A> */
    //inline fun <reified T : CustomScalar, A : ArgBuilder> configStub(): StubProvider<CustomScalarConfigStub<T, A>> = createCustomScalarConfig(T::class.simpleName!!)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are of any type defined in the schema
   */
  object QTypes {

    inline fun <reified T : QType> stub(): StubProvider<TypeStub.Query<T>> =
        Grub("${T::class.simpleName}") { TypeStub.noArgStub<T>(it) }

    inline fun <reified T : QType, A : ArgBuilder> optionalConfigStub(): StubProvider<TypeStub.OptionalConfigQuery<T, A>> =
        Grub("${T::class.simpleName}") { TypeStub.optionalArgStub<T, A>(it) }

    inline fun <reified T : QType, A : ArgBuilder> configStub(): StubProvider<TypeStub.ConfigurableQuery<T, A>> =
        Grub(T::class.meaningfulName()) { TypeStub.argStub<T, A>(it) }
  }

  object QInterfaces {

    inline fun <reified T> stub(): StubProvider<InterfaceStub.Query<T>>
        where T : QType, T : QInterface =
        Grub("${T::class.simpleName}") { InterfaceStub.noArgStub<T>(it) }

    inline fun <reified T, A> optionalConfigStub(): StubProvider<InterfaceStub.OptionalConfigQuery<T, A>>
        where T : QType, T : QInterface, A : ArgBuilder =
        Grub("${T::class.simpleName}") { InterfaceStub.optionalArgStub<T, A>(it) }

    inline fun <reified T, A> configStub(): StubProvider<InterfaceStub.ConfigurableQuery<T, A>>
        where T : QType, T : QInterface, A : ArgBuilder =
        Grub("${T::class.simpleName}") { InterfaceStub.argStub<T, A>(it) }

  }

  object QInterfaceLists {

    inline fun <reified T> stub(): StubProvider<InterfaceListStub.Query<T>>
        where T : QType, T : QInterface =
        Grub("${T::class.simpleName}") { InterfaceListStub.noArgStub<T>(it) }

    inline fun <reified T, A> optionalConfigStub(): StubProvider<InterfaceListStub.OptionalConfigQuery<T, A>>
        where T : QType, T : QInterface, A : ArgBuilder =
        Grub("${T::class.simpleName}") { InterfaceListStub.optionalArgStub<T, A>(it) }

    inline fun <reified T, A> configStub(): StubProvider<InterfaceListStub.ConfigurableQuery<T, A>>
        where T : QType, T : QInterface, A : ArgBuilder =
        Grub("${T::class.simpleName}") { InterfaceListStub.argStub<T, A>(it) }
  }

  object QTypeList {

    inline fun <reified T : QType> stub()
        : StubProvider<TypeListStub.Query<T>> =
        Grub("${T::class.simpleName}") { TypeListStub.noArgStub<T>(it) }

    inline fun <reified T : QType, A : ArgBuilder> optionalConfigStub()
        : StubProvider<TypeListStub.OptionalConfigQuery<T, A>> =
        Grub("${T::class.simpleName}") { TypeListStub.optionalArgStub<T, A>(it) }

    inline fun <reified T : QType, A : ArgBuilder> configStub()
        : StubProvider<TypeListStub.ConfigurableQuery<T, A>> =
        Grub("${T::class.simpleName}") { TypeListStub.argStub<T, A>(it) }
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which represent lists stub defined in the schema by: [QCustomScalarList.stub]
   */
  object QCustomScalarList {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} collection
     * fields fragment schema-defined scalar types
     * @param T The type argument for the createStub, typedValueFrom a schema-defined CustomScalar type
     * @return Grub<CustomScalarListInitStub<T>> the delegate which lazily provides a CustomScalarListInitStub<T> */
    //inline fun <reified T> stub(): StubProvider<CustomScalarListInitStub<T>> where T : CustomScalar = createCustomScalarListStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} collection
     * fields fragment schema-defined scalar types which take input argBuilder
     * @param arginit an initializer for the createStub field.
     * @param T type of the custom scalar
     * @param A type argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarListArgBuilder<T, A>> the delegate which lazily provides a CustomScalarListArgBuilder<T, A> */
    //inline fun <reified T : CustomScalar, A : ArgBuilder> configStub(): StubProvider<CustomScalarListConfigStub<T, A>> = createCustomScalarListConfig(T::class.simpleName!!)

    object QUnion {

      //inline fun <reified T : QUnionType> stub( objectModel: T ): StubProvider<UnionFragment<T>> = createUnionStub(objectModel, "${T::class.simpleName}")

      //inline fun <reified T : QUnionType, A : ArgBuilder> configStub( objectModel: T ): StubProvider<UnionConfigStub<T, A>> = Grub("${T::class.simpleName}") { UnionConfigAdapter.create<T, ArgBuilder>(it, objectModel)
    }

  }

  object QUnionList {
    ////TODO quality control inline fun <reified T : QUnionType> stub(): StubProvider<UnionListInitStub<T>> = createUnionListStub(T::class.objectInstance!!)
  }

  object QEnum {
    inline fun <reified T> stub(): StubProvider<NoArgConfig<EnumStub<T, ArgBuilder>, T>> where T : Enum<*>, T : QEnumType
        = Grub("${T::class.simpleName}") { EnumStub.noArgStub(it, T::class) }

    inline fun <reified T, A : ArgBuilder> optionalConfigStub(
    ): StubProvider<OptionalConfiguration<EnumStub<T, A>, T, A>> where T : Enum<*>, T : QEnumType =
        Grub("${T::class.simpleName}") {
          EnumStub.optionalArgStub<T, A>(it, T::class)
        }

    inline fun <reified T, A : ArgBuilder> configStub(): StubProvider<ConfiguredQuery<EnumStub<T, A>, A>> where T : Enum<*>, T : QEnumType
        = Grub("${T::class.simpleName}") { EnumStub.argStub<T, A>(it, T::class) }
  }

}

/**
 * TODO -> if anonymous class, crawl up the type hierarchy to find one, shouldn't really need it though */
@Suppress("NOTHING_TO_INLINE") @PublishedApi internal inline fun <T: Any> KClass<T>.meaningfulName() = "${this.simpleName}"
