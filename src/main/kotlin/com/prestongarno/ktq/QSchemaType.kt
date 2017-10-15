package com.prestongarno.ktq

import com.prestongarno.ktq.internal.Grub
import com.prestongarno.ktq.internal.StubProvider

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
     * Method which provides a delegate for fields
     * @param T The createTypeStub argument for the createStub, one of: {@link kotlin.Int}, {@link kotlin.String},
     *     {@link kotlin.Float}, {@link kotlin.Boolean}
     * @return Grub<Stub<T>> the delegate which lazily provides a Stub<T> */
    inline fun <reified T> stub(): StubProvider<Stub<T>> = Grub.createStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for fields
     * @param arginit an initializer for the createStub field. <b>Important for auto-generated schema definitions</b>
     * @param T createTypeStub argument for the createStub, one of: {@link kotlin.Int}, {@link kotlin.String},
     *     {@link kotlin.Float}, {@link kotlin.Boolean}
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<QConfigStub<T, A>> the delegate which lazily provides a Stub<T> */
    inline fun <reified T : Any, reified A : ArgBuilder> configStub(noinline arginit: (ArgBuilder) -> A): StubProvider<QConfigStub<T, A>>
        = Grub.createConfigStub(T::class.simpleName!!, arginit)
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
    inline fun <reified T : CustomScalar, reified A : CustomScalarArgBuilder> configStub(
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
    inline fun <reified T : QSchemaType, reified A : TypeArgBuilder> configStub(
        noinline arginit: (TypeArgBuilder) -> A
    ): StubProvider<QTypeConfigStub<T, A>>
        = Grub.createTypeConfigStub(T::class.simpleName!!, arginit)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are lists of any built-in scalar (Int, Float, Boolean, String):
   * {@link com.prestongarno.ktq.QSchemaType.QScalarList#createStub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QScalarList#createConfigStub(arginit)}.*/
  object QScalarList {
    /**
     * Method which provides a delegate for lists of built-in scalar types
     * @param T The createTypeStub argument for the list createStub, one of: {@link kotlin.Int}, {@link kotlin.String},
     *     {@link kotlin.Float}, {@link kotlin.Boolean}
     * @return Grub<ListStub<T>> the delegate which lazily provides a ListStub<T> */
    inline fun <reified T> stub(): StubProvider<ListStub<T>> = Grub.createScalarListStub(T::class.simpleName?: throw NullPointerException())

    /**
     * Method which provides a delegate for lists of built-in scalar types which accept input arguments
     * @param arginit an initializer for the list createStub fields
     * @param T createTypeStub argument for the createStub, one of: {@link kotlin.Int}, {@link kotlin.String},
     *     {@link kotlin.Float}, {@link kotlin.Boolean}
     * @param A createTypeStub argument for the argument builder class for the given schema field definition
     * @return Grub<ListConfig<T, A>> the delegate which lazily provides a ListConfig<T, A> */
    inline fun <reified T : Any, reified A : ListArgBuilder> configStub(
        noinline arginit: (ListArgBuilder) -> A
    ): StubProvider<ListConfig<T, A>> = Grub.createListConfigStub(T::class.simpleName!!, arginit)
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
    inline fun <reified T : QSchemaType, reified A : TypeListArgBuilder> configStub(
        noinline arginit: (TypeListArgBuilder) -> A
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
        = Grub.createCustimScalarStub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} collection
     * fields fragment schema-defined scalar types which take input arguments
     * @param arginit an initializer for the createStub field.
     * @param T createTypeStub of the custom scalar
     * @param A createTypeStub argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarListArgBuilder<T, A>> the delegate which lazily provides a CustomScalarListArgBuilder<T, A> */
    inline fun <reified T : CustomScalar, reified A : CustomScalarListArgBuilder> configStub(
        noinline arginit: (CustomScalarListArgBuilder) -> A
    ): StubProvider<CustomScalarListConfigStub<T, A>> =
        Grub.createCustomScalarListConfig(T::class.simpleName!!, arginit)
  }

  object QUnion {
    inline fun <reified T : QSchemaUnion> stub(objectModel: T): StubProvider<UnionInitStub<T>> =
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


