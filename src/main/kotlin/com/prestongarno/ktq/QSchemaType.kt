package com.prestongarno.ktq

import com.prestongarno.ktq.hooks.InitStub
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.hooks.TypeConfig
import com.prestongarno.ktq.hooks.providers.EnumProvider.createEnumConfigStub
import com.prestongarno.ktq.hooks.providers.EnumProvider.createEnumStub
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createBooleanArrayDelegate
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createBooleanDelegate
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createCustomScalarConfig
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createCustomScalarListConfig
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createCustomScalarListStub
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createCustomScalarStub
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createFloatArrayDelegate
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createFloatDelegate
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createIntArrayDelegate
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createIntDelegate
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createStringArrayDelegate
import com.prestongarno.ktq.hooks.providers.PrimitiveProvider.createStringDelegate
import com.prestongarno.ktq.hooks.providers.TypeProvider.createTypeConfigStub
import com.prestongarno.ktq.hooks.providers.TypeProvider.createTypeListConfigStub
import com.prestongarno.ktq.hooks.providers.TypeProvider.createTypeListStub
import com.prestongarno.ktq.hooks.providers.TypeProvider.createTypeStub
import com.prestongarno.ktq.hooks.providers.UnionProvider.createUnionStub
import com.prestongarno.ktq.stubs.CustomScalarConfigStub
import com.prestongarno.ktq.stubs.CustomScalarInitStub
import com.prestongarno.ktq.stubs.CustomScalarListConfigStub
import com.prestongarno.ktq.stubs.CustomScalarListInitStub
import com.prestongarno.ktq.stubs.ListConfigType
import com.prestongarno.ktq.stubs.ListInitStub
import com.prestongarno.ktq.stubs.UnionConfigStub
import com.prestongarno.ktq.stubs.UnionInitStub

/**
 * The root createTypeStub of all generated schema objects. Nested objects
 * provide handles for field declarations
 * @author prestongarno */
interface QSchemaType {

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are regular Scalars (Int, Float, Boolean, String):
   * {@link com.prestongarno.ktq.QSchemaType.QScalar#createStub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QScalar#createConfigStub(arginit)}.*/
  object QScalar {
    /**
     * Method which provides a delegate for fields of type [kotlin.String]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<StringDelegate<ArgBuilder>>] */
    fun stringStub() = createStringDelegate()

    /**
     * Method which provides a delegate for fields of type [kotlin.Int]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<IntDelegate<ArgBuilder>>] */
    fun intStub() = createIntDelegate()

    /**
     * Method which provides a delegate for fields of type [kotlin.Float]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<FloatDelegate<ArgBuilder>>] */
    fun floatStub() = createFloatDelegate()

    /**
     * Method which provides a delegate for fields of type [kotlin.Boolean]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<BooleanDelegate<ArgBuilder>>] */
    fun booleanStub() = createBooleanDelegate()


    /**
     * Method which provides a delegate for fields of type [kotlin.String]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<StringDelegate<ArgBuilder>>] */
    fun <A : ArgBuilder> stringStub(arginit: (ArgBuilder) -> A) = createStringDelegate(arginit)

    /**
     * Method which provides a delegate for fields of type [kotlin.Int]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<IntDelegate<A>>] */
    fun <A : ArgBuilder> intStub(arginit: (ArgBuilder) -> A) = createIntDelegate(arginit)

    /**
     * Method which provides a delegate for fields of type [kotlin.Float]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<FloatDelegate<A>>] */
    fun <A : ArgBuilder> floatStub(arginit: (ArgBuilder) -> A) = createFloatDelegate(arginit)

    /**
     * Method which provides a delegate for fields of type [kotlin.Boolean]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<BooleanDelegate<A>>] */
    fun <A : ArgBuilder> booleanStub(arginit: (ArgBuilder) -> A) = createBooleanDelegate(arginit)
  }

  object QScalarArray {
    /**
     * Method which provides a delegate for fields of type [kotlin.String]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<StringArrayDelegate<ArgBuilder>>] */
    fun stringArrayStub() = createStringArrayDelegate()

    /**
     * Method which provides a delegate for fields of type [kotlin.Int]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<IntArrayDelegate<ArgBuilder>>] */
    fun intArrayStub() = createIntArrayDelegate()

    /**
     * Method which provides a delegate for fields of type [kotlin.Float]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<FloatArrayDelegate<ArgBuilder>>] */
    fun floatArrayStub() = createFloatArrayDelegate()

    /**
     * Method which provides a delegate for fields of type [kotlin.Boolean]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<BooleanArrayDelegate<ArgBuilder>>] */
    fun booleanArrayStub() = createBooleanArrayDelegate()


    /**
     * Method which provides a delegate for fields of type [kotlin.String] (as an Array)
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<StringArrayDelegate<ArgBuilder>>] */
    fun <A : ArgBuilder> stringArrayStub(arginit: (ArgBuilder) -> A) = createStringArrayDelegate(arginit)

    /**
     * Method which provides a delegate for fields of type [kotlin.IntArray]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<IntArrayDelegate<A>>] */
    fun <A : ArgBuilder> intArrayStub(arginit: (ArgBuilder) -> A) = createIntArrayDelegate(arginit)

    /**
     * Method which provides a delegate for fields of type [kotlin.FloatArray]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<FloatArrayDelegate<A>>] */
    fun <A : ArgBuilder> floatArrayStub(arginit: (ArgBuilder) -> A) = createFloatArrayDelegate(arginit)

    /**
     * Method which provides a delegate for fields of type [kotlin.BooleanArray]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<BooleanArrayDelegate<A>>] */
    fun <A : ArgBuilder> booleanArrayStub(arginit: (ArgBuilder) -> A) = createBooleanArrayDelegate(arginit)

  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are types typedValueFrom any Schema-defined scalars:
   * {@link com.prestongarno.ktq.QSchemaType.QCustomScalar#createStub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QCustomScalar#createConfigStub(arginit)}.*/
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
     * fragment schema-defined scalar types which take input arguments
     * @param arginit an initializer for the createStub field. <b>Important for auto-generated schema definitions</b>
     * @param T createTypeStub of the custom scalar
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarConfigStub<T, A>> the delegate which lazily provides a CustomScalarConfigStub<T, A> */
    inline fun <reified T : CustomScalar, A : CustomScalarArgBuilder> stub(
        noinline arginit: (CustomScalarArgBuilder) -> A
    ): StubProvider<CustomScalarConfigStub<T, A>> = createCustomScalarConfig(T::class.simpleName!!, arginit)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are of any createTypeStub defined in the schema:
   * {@link com.prestongarno.ktq.QSchemaType.QType#createStub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QType#createConfigStub(arginit)}.*/
  object QType {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QType} fields
     * @param T The createTypeStub of the createStub which the field is backing
     * @return Grub<InitStub<T>> the delegate which lazily provides a InitStub<T> */
    inline fun <reified T : QSchemaType> stub(): StubProvider<InitStub<T>> = createTypeStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QType} fields
     * fragment schema-defined types which take input arguments
     * @param arginit an initializer for the createStub field
     * @param T createTypeStub of the field (schema-defined createTypeStub)
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<QTypeConfigStub<T, A>> the delegate which lazily provides a QTypeConfigStub<T, A> */
    inline fun <reified T : QSchemaType, A : ArgBuilder> stub(
        noinline arginit: (ArgBuilder) -> A
    ): StubProvider<TypeConfig<T, A>>
        = createTypeConfigStub(T::class.simpleName!!, arginit)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which represent lists of any createTypeStub defined in the schema:
   * {@link com.prestongarno.ktq.QSchemaType.QTypeList#createStub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QTypeList#createConfigStub(arginit)}.*/
  object QTypeList {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QTypeList} fields
     * @param T The createTypeStub of the createStub
     * @return Grub<ListInitStub<T>> the delegate which lazily provides a ListInitStub<T> */
    inline fun <reified T : QSchemaType> stub(): StubProvider<ListInitStub<T>>
        = createTypeListStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QTypeList} fields
     * fragment schema-defined types which take input arguments
     * @param arginit an initializer for the createStub field
     * @param T createTypeStub of the field (schema-defined createTypeStub)
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<ListConfigType<T, A>> the delegate which lazily provides a ListConfigType<T, A> */
    inline fun <reified T : QSchemaType, A : ArgBuilder> stub(
        noinline arginit: (ArgBuilder) -> A
    ): StubProvider<ListConfigType<T, A>> = createTypeListConfigStub(T::class.simpleName!!, arginit)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which represent lists of any custom scalar createTypeStub defined in the schema:
   * {@link com.prestongarno.ktq.QSchemaType.QCustomScalarList#createStub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QCustomScalarList#createConfigStub(arginit)}.*/
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
     * fields fragment schema-defined scalar types which take input arguments
     * @param arginit an initializer for the createStub field.
     * @param T createTypeStub of the custom scalar
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarListArgBuilder<T, A>> the delegate which lazily provides a CustomScalarListArgBuilder<T, A> */
    inline fun <reified T : CustomScalar, A : CustomScalarListArgBuilder> stub(
        noinline arginit: (CustomScalarListArgBuilder) -> A
    ): StubProvider<CustomScalarListConfigStub<T, A>> =
        createCustomScalarListConfig(T::class.simpleName!!, arginit)
  }

  object QUnion {

    inline fun <reified T : QSchemaUnion> stub(objectModel: T): StubProvider<UnionInitStub<T>> =
        createUnionStub(objectModel, T::class.simpleName!!)

    inline fun <reified T : QSchemaUnion, A : ArgBuilder> stub(
        objectModel: T,
        noinline arginit: (ArgBuilder) -> A
    ): StubProvider<UnionConfigStub<T, A>> = createUnionStub(objectModel, T::class.simpleName!!, arginit)

  }

  object QUnionList {
    inline fun <reified T : QSchemaUnion> stub(objectModel: T): StubProvider<UnionInitStub<T>>
        = TODO()
  }

  object QEnum {
    inline fun <reified T> stub(): StubProvider<EnumStub<T>> where T : Enum<*>, T : QSchemaEnum
        = createEnumStub(T::class.simpleName.toString(), T::class)

    inline fun <reified T, A : ArgBuilder> stub(
        noinline arginit: (ArgBuilder) -> A
    ): StubProvider<EnumStub<T>> where T : Enum<*>, T : QSchemaEnum
        = createEnumConfigStub(T::class.simpleName.toString(), T::class, arginit)
  }

}

