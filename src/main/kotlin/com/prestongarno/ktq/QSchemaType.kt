package com.prestongarno.ktq

import com.prestongarno.ktq.hooks.Grub
import com.prestongarno.ktq.hooks.StubProvider

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
    fun stringStub() = Grub.createStringDelegate()

    /**
     * Method which provides a delegate for fields of type [kotlin.Int]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<IntDelegate<ArgBuilder>>] */
    fun intStub() = Grub.createIntDelegate()

    /**
     * Method which provides a delegate for fields of type [kotlin.Float]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<FloatDelegate<ArgBuilder>>] */
    fun floatStub() = Grub.createFloatDelegate()

    /**
     * Method which provides a delegate for fields of type [kotlin.Boolean]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<BooleanDelegate<ArgBuilder>>] */
    fun booleanStub() = Grub.createBooleanDelegate()


    /**
     * Method which provides a delegate for fields of type [kotlin.String]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<StringDelegate<ArgBuilder>>] */
    fun <A: ArgBuilder> stringStub(arginit: (ArgBuilder) -> A) = Grub.createStringDelegate(arginit)

    /**
     * Method which provides a delegate for fields of type [kotlin.Int]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<IntDelegate<A>>] */
    fun <A: ArgBuilder> intStub(arginit: (ArgBuilder) -> A) = Grub.createIntDelegate(arginit)

    /**
     * Method which provides a delegate for fields of type [kotlin.Float]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<FloatDelegate<A>>] */
    fun <A: ArgBuilder> floatStub(arginit: (ArgBuilder) -> A) = Grub.createFloatDelegate(arginit)

    /**
     * Method which provides a delegate for fields of type [kotlin.Boolean]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<BooleanDelegate<A>>] */
    fun <A: ArgBuilder> booleanStub(arginit: (ArgBuilder) -> A) = Grub.createBooleanDelegate(arginit)
  }

  object QScalarArray {
    /**
     * Method which provides a delegate for fields of type [kotlin.String]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<StringArrayDelegate<ArgBuilder>>] */
    fun stringArrayStub() = Grub.createStringArrayDelegate()

    /**
     * Method which provides a delegate for fields of type [kotlin.Int]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<IntArrayDelegate<ArgBuilder>>] */
    fun intArrayStub() = Grub.createIntArrayDelegate()

    /**
     * Method which provides a delegate for fields of type [kotlin.Float]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<FloatArrayDelegate<ArgBuilder>>] */
    fun floatArrayStub() = Grub.createFloatArrayDelegate()

    /**
     * Method which provides a delegate for fields of type [kotlin.Boolean]
     * @return [com.prestongarno.ktq.hooks.StubProvider]<BooleanArrayDelegate<ArgBuilder>>] */
    fun booleanArrayStub() = Grub.createBooleanArrayDelegate()


    /**
     * Method which provides a delegate for fields of type [kotlin.String] (as an Array)
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<StringArrayDelegate<ArgBuilder>>] */
    fun <A: ArgBuilder> stringArrayStub(arginit: (ArgBuilder) -> A) = Grub.createStringDelegate(arginit)

    /**
     * Method which provides a delegate for fields of type [kotlin.IntArray]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<IntArrayDelegate<A>>] */
    fun <A: ArgBuilder> intArrayStub(arginit: (ArgBuilder) -> A) = Grub.createIntDelegate(arginit)

    /**
     * Method which provides a delegate for fields of type [kotlin.FloatArray]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<FloatArrayDelegate<A>>] */
    fun <A: ArgBuilder> floatArrayStub(arginit: (ArgBuilder) -> A) = Grub.createFloatDelegate(arginit)

    /**
     * Method which provides a delegate for fields of type [kotlin.BooleanArray]
     * @param A the type of argument (graphql field arguments) builder type this field requires
     * @param arginit the initializer for the arguments. Generally this is auto-generated so don't worry about it
     * @return [com.prestongarno.ktq.hooks.StubProvider]<BooleanArrayDelegate<A>>] */
    fun <A: ArgBuilder> booleanArrayStub(arginit: (ArgBuilder) -> A) = Grub.createBooleanDelegate(arginit)

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
        = Grub.createCustomScalarStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} fields
     * fragment schema-defined scalar types which take input arguments
     * @param arginit an initializer for the createStub field. <b>Important for auto-generated schema definitions</b>
     * @param T createTypeStub of the custom scalar
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarConfigStub<T, A>> the delegate which lazily provides a CustomScalarConfigStub<T, A> */
    inline fun <reified T : CustomScalar, A : CustomScalarArgBuilder> stub(
        noinline arginit: (CustomScalarArgBuilder) -> A
    ): StubProvider<CustomScalarConfigStub<T, A>> = Grub.createCustomScalarConfig(T::class.simpleName!!, arginit)
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
    inline fun <reified T : QSchemaType> stub(): StubProvider<InitStub<T>> = Grub.createTypeStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QType} fields
     * fragment schema-defined types which take input arguments
     * @param arginit an initializer for the createStub field
     * @param T createTypeStub of the field (schema-defined createTypeStub)
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<QTypeConfigStub<T, A>> the delegate which lazily provides a QTypeConfigStub<T, A> */
    inline fun <reified T : QSchemaType, A : ArgBuilder> stub(
        noinline arginit: (ArgBuilder) -> A
    ): StubProvider<QTypeConfigStub<T, A>>
        = Grub.createTypeConfigStub(T::class.simpleName!!, arginit)
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
        = Grub.createTypeListStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QTypeList} fields
     * fragment schema-defined types which take input arguments
     * @param arginit an initializer for the createStub field
     * @param T createTypeStub of the field (schema-defined createTypeStub)
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<ListConfigType<T, A>> the delegate which lazily provides a ListConfigType<T, A> */
    inline fun <reified T : QSchemaType, A : ArgBuilder> stub(
        noinline arginit: (ArgBuilder) -> A
    ): StubProvider<ListConfigType<T, A>> = Grub.createTypeListConfigStub(T::class.simpleName!!, arginit)
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
        = Grub.createCustomScalarListStub(T::class.simpleName!!)

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
        Grub.createCustomScalarListConfig(T::class.simpleName!!, arginit)
  }

  object QUnion {
    inline fun <reified T : QSchemaUnion> stub(objectModel: T): StubProvider<UnionInitStub<T>> =
        Grub.createUnionStub(objectModel, T::class.simpleName!!)

    inline fun <reified T : QSchemaUnion, A : ArgBuilder> stub(objectModel: T, arginit: (ArgBuilder) -> A): StubProvider<UnionInitStub<T>> =
        Grub.createUnionStub(objectModel, T::class.simpleName!!)
  }
}

/**
 * Interface for all Custom Scalar types fragment a schema.
 * Supports custom deserialization or mapping to another object by
 * using {@link com.prestongarno.ktq.adapters.custom.QScalarMapper} */
interface CustomScalar : QSchemaType

interface QSchemaUnion : QSchemaType {

  fun on(init: () -> QModel<*>)

  val queue: DispatchQueue

  companion object {
    fun create(objectModel: QSchemaUnion): QSchemaUnion = BaseUnionAdapter(objectModel)
  }
}


