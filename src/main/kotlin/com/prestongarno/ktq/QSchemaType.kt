package com.prestongarno.ktq

import com.prestongarno.ktq.hooks.ConfigurableQuery
import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.NoArgConfig
import com.prestongarno.ktq.hooks.OptionalConfigQuery
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.stubs.ListConfigType
import com.prestongarno.ktq.stubs.ListInitStub
import com.prestongarno.ktq.stubs.TypeStub

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
  }

  object QScalarArray {

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
    //inline fun <reified T : CustomScalar> stub(): StubProvider<CustomScalarInitStub<T>> = createCustomScalarStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} fields
     * fragment schema-defined scalar types which take input argBuilder
     * @param arginit an initializer for the createStub field. <b>Important for auto-generated schema definitions</b>
     * @param T createTypeStub of the custom scalar
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarConfigStub<T, A>> the delegate which lazily provides a CustomScalarConfigStub<T, A> */
    //inline fun <reified T : CustomScalar, A : ArgBuilder> configStub(): StubProvider<CustomScalarConfigStub<T, A>> = createCustomScalarConfig(T::class.simpleName!!)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are of any createTypeStub defined in the schema
   */
  object QTypes {

    inline fun <reified T : QType> stub(): StubProvider<TypeStub.Query<T>> =
        Grub("${T::class.simpleName}") { TypeStub.noArgStub<T>(it) }

    inline fun <reified T : QType, A : ArgBuilder> optionalConfigStub(): StubProvider<TypeStub.OptionalConfigQuery<T, A>> =
        Grub("${T::class.simpleName}") { TypeStub.optionalArgStub<T, A>(it) }

    inline fun <reified T : QType, A : ArgBuilder> configStub(): StubProvider<TypeStub.ConfigurableQuery<T, A>> =
        Grub("${T::class.simpleName}") { TypeStub.argStub<T, A>(it) }
  }

  object QInterfaces {
    inline fun <reified T> stub(): StubProvider<InterfaceStub.Query<T>>
        where T : QType, T : QInterface =
        Grub("${T::class.simpleName}") { InterfaceStub.noArgStub<T>(it) }

    inline fun <reified T, A> optionalConfigStub(): StubProvider<InterfaceStub.OptionalConfigQueryQuery<T, A>>
        where T : QType, T : QInterface, A : ArgBuilder =
        Grub("${T::class.simpleName}") { InterfaceStub.optionalArgStub<T, A>(it) }

    inline fun <reified T, A> configStub(): StubProvider<InterfaceStub.ConfigurableQueryQuery<T, A>>
        where T : QType, T : QInterface, A : ArgBuilder =
        Grub("${T::class.simpleName}") { InterfaceStub.argStub<T, A>(it) }

  }

  object QInterfaceLists {
    // redacted/refactored :/
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
        = TODO()

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QTypeList} fields
     * fragment schema-defined types which take input argBuilder
     * @param arginit an initializer for the createStub field
     * @param T createTypeStub of the field (schema-defined createTypeStub)
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<ListConfigType<T, A>> the delegate which lazily provides a ListConfigType<T, A> */
    inline fun <reified T : QType, A : ArgBuilder> configStub(): StubProvider<ListConfigType<T, A>> =
        TODO()
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
    //inline fun <reified T> stub(): StubProvider<CustomScalarListInitStub<T>> where T : CustomScalar = createCustomScalarListStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} collection
     * fields fragment schema-defined scalar types which take input argBuilder
     * @param arginit an initializer for the createStub field.
     * @param T createTypeStub of the custom scalar
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
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
    ): StubProvider<OptionalConfigQuery<EnumStub<T, A>, T, A>> where T : Enum<*>, T : QEnumType =
        Grub("${T::class.simpleName}") {
          EnumStub.optionalArgStub<T, A>(it, T::class)
        }

    inline fun <reified T, A : ArgBuilder> configStub(): StubProvider<ConfigurableQuery<EnumStub<T, A>, A>> where T : Enum<*>, T : QEnumType
        = Grub("${T::class.simpleName}") { EnumStub.argStub<T, A>(it, T::class) }
  }

}
