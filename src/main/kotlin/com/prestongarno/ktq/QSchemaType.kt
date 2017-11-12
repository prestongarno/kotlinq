package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.BooleanArrayDelegate
import com.prestongarno.ktq.adapters.BooleanDelegate
import com.prestongarno.ktq.adapters.BooleanDelegateConfig
import com.prestongarno.ktq.adapters.EnumAdapterImpl
import com.prestongarno.ktq.adapters.FloatArrayDelegate
import com.prestongarno.ktq.adapters.FloatDelegate
import com.prestongarno.ktq.adapters.FloatDelegateConfig
import com.prestongarno.ktq.adapters.IntegerArrayDelegate
import com.prestongarno.ktq.adapters.IntegerDelegate
import com.prestongarno.ktq.adapters.IntegerDelegateConfig
import com.prestongarno.ktq.adapters.StringArrayDelegate
import com.prestongarno.ktq.adapters.StringDelegate
import com.prestongarno.ktq.adapters.StringDelegateConfig
import com.prestongarno.ktq.adapters.UnionConfigAdapter
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.InitStub
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.hooks.TypeConfig
import com.prestongarno.ktq.hooks.providers.EnumProvider.createEnumStub
import com.prestongarno.ktq.hooks.providers.InterfaceProvider.createCollectionConfigStub
import com.prestongarno.ktq.hooks.providers.InterfaceProvider.createCollectionStub
import com.prestongarno.ktq.hooks.providers.InterfaceProvider.createInterfaceConfigStub
import com.prestongarno.ktq.hooks.providers.InterfaceProvider.createInterfaceStub
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createCustomScalarConfig
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createCustomScalarListConfig
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createCustomScalarListStub
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createCustomScalarStub
import com.prestongarno.ktq.hooks.providers.TypeProvider.createTypeConfigStub
import com.prestongarno.ktq.hooks.providers.TypeProvider.createTypeListConfigStub
import com.prestongarno.ktq.hooks.providers.TypeProvider.createTypeListStub
import com.prestongarno.ktq.hooks.providers.TypeProvider.createTypeStub
import com.prestongarno.ktq.hooks.providers.UnionProvider.createUnionListStub
import com.prestongarno.ktq.hooks.providers.UnionProvider.createUnionStub
import com.prestongarno.ktq.stubs.CollectionConfigFragment
import com.prestongarno.ktq.stubs.CollectionFragment
import com.prestongarno.ktq.stubs.CustomScalarConfigStub
import com.prestongarno.ktq.stubs.CustomScalarInitStub
import com.prestongarno.ktq.stubs.CustomScalarListConfigStub
import com.prestongarno.ktq.stubs.CustomScalarListInitStub
import com.prestongarno.ktq.stubs.InterfaceConfigFragment
import com.prestongarno.ktq.stubs.InterfaceFragment
import com.prestongarno.ktq.stubs.ListConfigType
import com.prestongarno.ktq.stubs.ListInitStub
import com.prestongarno.ktq.stubs.UnionConfigStub
import com.prestongarno.ktq.stubs.UnionFragment
import com.prestongarno.ktq.stubs.UnionListInitStub

/**
 * The root createTypeStub of all generated schema objects. Nested objects
 * provide handles for field declarations
 * @author prestongarno */
interface QSchemaType {

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are regular Scalars (Int, Float, Boolean, String):
   * QScalar#createStub()} and
   * QScalar#createConfigStub(arginit)}.*/
  object QScalar {
    /**
     * Method which provides a delegate for fields of type [kotlin.String]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<StringDelegate<ArgBuilder>>] */
    fun stringStub(): StubProvider<StringDelegate<ArgBuilder>> =
        Grub("String") { StringDelegate<ArgBuilder>(it) }


    /**
     * Method which provides a delegate for fields of type [kotlin.Int]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<IntDelegate<ArgBuilder>>] */
    fun intStub() : StubProvider<IntegerDelegate<ArgBuilder>>
        = Grub("Int") { IntegerDelegate<ArgBuilder>(it) }


    /**
     * Method which provides a delegate for fields of type [kotlin.Float]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<FloatDelegate<ArgBuilder>>] */
    fun floatStub() : StubProvider<FloatDelegate<ArgBuilder>> =
        Grub("Float") { FloatDelegate<ArgBuilder>(it) }


    /**
     * Method which provides a delegate for fields of type [kotlin.Boolean]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<BooleanDelegate<ArgBuilder>>] */
    fun booleanStub() : StubProvider<BooleanDelegate<ArgBuilder>> =
        Grub("Boolean") { BooleanDelegate<ArgBuilder>(it) }


    /**
     * Method which provides a delegate for fields of type [kotlin.String]
     * @param A the type of argument (graphql field argBuilder) builder type this field requires
     * @return [com.prestongarno.ktq.hooks.StubProvider]<StringDelegate<ArgBuilder>>] */
    fun <A : ArgBuilder> stringConfigStub(): StubProvider<StringDelegateConfig<A>> =
        Grub("String") { StringDelegateConfig<A>(it) }


    /**
     * Method which provides a delegate for fields of type [kotlin.Int]
     * @param A the type of argument (graphql field argBuilder) builder type this field requires
     * @param arginit the initializer for the argBuilder. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<IntDelegate<A>>] */
    fun <A : ArgBuilder> intConfigStub() : StubProvider<IntegerDelegateConfig<A>> =
        Grub("Int") { IntegerDelegateConfig<A>(it) }


    /**
     * Method which provides a delegate for fields of type [kotlin.Float]
     * @param A the type of argument (graphql field argBuilder) builder type this field requires
     * @param arginit the initializer for the argBuilder. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<FloatDelegate<A>>] */
    fun <A : ArgBuilder> floatConfigStub() : StubProvider<FloatDelegateConfig<A>> =
        Grub("Float") { FloatDelegateConfig<A>(it) }

    /**
     * Method which provides a delegate for fields of type [kotlin.Boolean]
     * @param A the type of argument (graphql field argBuilder) builder type this field requires
     * @param arginit the initializer for the argBuilder. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<BooleanDelegate<A>>] */
    fun <A : ArgBuilder> booleanStub(arginit: (ArgBuilder) -> A) : StubProvider<BooleanDelegateConfig<A>> =
        Grub("Boolean") { BooleanDelegateConfig<A>(it) }
  }

  object QScalarArray {
    /**
     * Method which provides a delegate for fields of type [kotlin.String]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<StringArrayDelegate<ArgBuilder>>] */
    fun stringArrayStub() : StubProvider<StringArrayDelegate<ArgBuilder>> =
        Grub("String", true) { StringArrayDelegate<ArgBuilder>(it) }

    /**
     * Method which provides a delegate for fields of type [kotlin.Int]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<IntArrayDelegate<ArgBuilder>>] */
    fun intArrayStub() : StubProvider<IntegerArrayDelegate<ArgBuilder>> =
        Grub("Int", true) { IntegerArrayDelegate<ArgBuilder>(it) }

    /**
     * Method which provides a delegate for fields of type [kotlin.Float]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<FloatArrayDelegate<ArgBuilder>>] */
    fun floatArrayStub() : StubProvider<FloatArrayDelegate<ArgBuilder>> =
        Grub("Float", true) { FloatArrayDelegate<ArgBuilder>(it) }

    /**
     * Method which provides a delegate for fields of type [kotlin.Boolean]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<BooleanArrayDelegate<ArgBuilder>>] */
    fun booleanArrayStub() : StubProvider<BooleanArrayDelegate<ArgBuilder>> =
        Grub("Boolean", true) { BooleanArrayDelegate<ArgBuilder>(it) }

  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are types typedValueFrom any Schema-defined scalars:
   * QCustomScalar#createStub()} and
   * QCustomScalar#createConfigStub(arginit)}.*/
  object QCustomScalar {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} fields
     * fragment schema-defined scalar types
     * @param T The createTypeStub argument for the createStub, typedValueFrom a schema-defined CustomScalar createTypeStub
     * @return Grub<CustomScalarInitStub<T>> the delegate which lazily provides a CustomScalarInitStub<T> */
    inline fun <reified T : CustomScalar> stub(): StubProvider<CustomScalarInitStub<T>>
        = createCustomScalarStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} fields
     * fragment schema-defined scalar types which take input argBuilder
     * @param arginit an initializer for the createStub field. <b>Important for auto-generated schema definitions</b>
     * @param T createTypeStub of the custom scalar
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarConfigStub<T, A>> the delegate which lazily provides a CustomScalarConfigStub<T, A> */
    inline fun <reified T : CustomScalar, A : ArgBuilder> configStub():
        StubProvider<CustomScalarConfigStub<T, A>> =
        createCustomScalarConfig(T::class.simpleName!!)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are of any createTypeStub defined in the schema
   */
  object QObject {
    /**
     * Method which provides a delegate for [QType] fields
     * @param T The createTypeStub of the createStub which the field is backing
     * @return Grub<[InitStub]<[T]>> the delegate which lazily provides a InitStub<T> */
    inline fun <reified T : QType> stub(): StubProvider<InitStub<out T, ArgBuilder>> = createTypeStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QType} fields
     * fragment schema-defined types which take input argBuilder
     * @param T createTypeStub of the field (schema-defined createTypeStub)
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<QTypeConfigStub<T, A>> the delegate which lazily provides a QTypeConfigStub<T, A> */
    inline fun <reified T : QType, A : ArgBuilder> configStub(): StubProvider<TypeConfig<out T, A>>
        = createTypeConfigStub(T::class.simpleName!!)
  }

  object QInterfaces {
    inline fun <reified T> stub(): StubProvider<InterfaceFragment<T>>
        where T : QType, T : QInterface =
        createInterfaceStub("${T::class.simpleName}")

    inline fun <reified T, A : ArgBuilder> configStub(
    ): StubProvider<InterfaceConfigFragment<T, A>>
        where T : QType, T : QInterface =
        createInterfaceConfigStub<T, A>("${T::class.simpleName}")
  }

  object QInterfaceLists {
    inline fun <reified T> stub(): StubProvider<CollectionFragment<T>>
        where T : QType, T : QInterface =
        createCollectionStub(name = "${T::class.simpleName}")

    inline fun <reified T, A : ArgBuilder> stub(
        noinline arginit: (ArgBuilder) -> A
    ): StubProvider<CollectionConfigFragment<T, A>>
        where T : QType, T : QInterface =
        createCollectionConfigStub("${T::class.simpleName}")
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which represent lists of any createTypeStub defined in the schema:
   * QTypeList#createStub()} and
   * QTypeList#createConfigStub(arginit)}.*/
  object QTypeList {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QTypeList} fields
     * @param T The createTypeStub of the createStub
     * @return Grub<ListInitStub<T>> the delegate which lazily provides a ListInitStub<T> */
    inline fun <reified T : QType> stub(): StubProvider<ListInitStub<T, ArgBuilder>>
        = createTypeListStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QTypeList} fields
     * fragment schema-defined types which take input argBuilder
     * @param arginit an initializer for the createStub field
     * @param T createTypeStub of the field (schema-defined createTypeStub)
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<ListConfigType<T, A>> the delegate which lazily provides a ListConfigType<T, A> */
    inline fun <reified T : QType, A : ArgBuilder> configStub(): StubProvider<ListConfigType<T, A>> =
        createTypeListConfigStub(T::class.simpleName!!)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which represent lists stub defined in the schema by: [QCustomScalarList.stub]
   */
  object QCustomScalarList {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} collection
     * fields fragment schema-defined scalar types
     * @param T The createTypeStub argument for the createStub, typedValueFrom a schema-defined CustomScalar createTypeStub
     * @return Grub<CustomScalarListInitStub<T>> the delegate which lazily provides a CustomScalarListInitStub<T> */
    inline fun <reified T> stub(): StubProvider<CustomScalarListInitStub<T>> where T : CustomScalar
        = createCustomScalarListStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} collection
     * fields fragment schema-defined scalar types which take input argBuilder
     * @param arginit an initializer for the createStub field.
     * @param T createTypeStub of the custom scalar
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarListArgBuilder<T, A>> the delegate which lazily provides a CustomScalarListArgBuilder<T, A> */
    inline fun <reified T : CustomScalar, A : ArgBuilder> configStub(): StubProvider<CustomScalarListConfigStub<T, A>> =
        createCustomScalarListConfig(T::class.simpleName!!)
  }

  object QUnion {

    inline fun <reified T : QUnionType> stub(
        objectModel: T
    ): StubProvider<UnionFragment<T>> = createUnionStub(objectModel, "${T::class.simpleName}")

    inline fun <reified T : QUnionType, A : ArgBuilder> configStub(
        objectModel: T
    ): StubProvider<UnionConfigStub<T, A>> = Grub("${T::class.simpleName}") {
      UnionConfigAdapter.create<T, ArgBuilder>(it, objectModel)
    }

  }

  object QUnionList {
    //TODO quality control
    inline fun <reified T : QUnionType> stub(): StubProvider<UnionListInitStub<T>> =
        createUnionListStub(T::class.objectInstance!!)
  }

  object QEnum {
    inline fun <reified T> stub(): StubProvider<EnumStub<T>> where T : Enum<*>, T : QEnumType
        = createEnumStub(T::class.simpleName.toString(), T::class)

    inline fun <reified T, A : ArgBuilder> configStub(
    ): StubProvider<EnumStub<T>> where T : Enum<*>, T : QEnumType
        = Grub("${T::class.simpleName}", true) { EnumAdapterImpl<T, A>(it, T::class) }
  }

}

