package com.prestongarno.ktq

import com.prestongarno.ktq.internal.Grub

/**
 * The root type of all generated schema objects. Nested objects
 * provide handles for field declarations
 * @author prestongarno */
interface QSchemaType {

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are regular Scalars (Int, Float, Boolean, String):
   * {@link com.prestongarno.ktq.QSchemaType.QScalar#stub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QScalar#configStub(arginit)}.*/
  object QScalar {
    /**
     * Method which provides a delegate for fields
     * @param T The type argument for the stub, one of: {@link kotlin.Int}, {@link kotlin.String},
     *     {@link kotlin.Float}, {@link kotlin.Boolean}
     * @return Grub<Stub<T>> the delegate which lazily provides a Stub<T> */
    inline fun <reified T> stub(): Grub<Stub<T>> = Grub.stub(T::class.simpleName!!)

    /**
     * Method which provides a delegate for fields
     * @param arginit an initializer for the stub field. <b>Important for auto-generated schema definitions</b>
     * @param T type argument for the stub, one of: {@link kotlin.Int}, {@link kotlin.String},
     *     {@link kotlin.Float}, {@link kotlin.Boolean}
     * @param A type argument for the argument builder class for that given schema field definition
     * @return Grub<QConfigStub<T, A>> the delegate which lazily provides a Stub<T> */
    inline fun <reified T : Any, reified A : ArgBuilder> configStub(noinline arginit: (ArgBuilder) -> A): Grub<QConfigStub<T, A>>
        = Grub.configStub(T::class.simpleName!!, arginit)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are types typedValueFrom any Schema-defined scalars:
   * {@link com.prestongarno.ktq.QSchemaType.QCustomScalar#stub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QCustomScalar#configStub(arginit)}.*/
  object QCustomScalar {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} fields
     * fragment schema-defined scalar types
     * @param T The type argument for the stub, typedValueFrom a schema-defined CustomScalar type
     * @return Grub<CustomScalarInitStub<T>> the delegate which lazily provides a CustomScalarInitStub<T> */
    inline fun <reified T : CustomScalar> stub(): Grub<CustomScalarInitStub<T>>
        = Grub.customScalar(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} fields
     * fragment schema-defined scalar types which take input arguments
     * @param arginit an initializer for the stub field. <b>Important for auto-generated schema definitions</b>
     * @param T type of the custom scalar
     * @param A type argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarConfigStub<T, A>> the delegate which lazily provides a CustomScalarConfigStub<T, A> */
    inline fun <reified T : CustomScalar, reified A : CustomScalarArgBuilder> configStub(
        noinline arginit: (CustomScalarArgBuilder) -> A
    ): Grub<CustomScalarConfigStub<T, A>> = Grub.customScalarConfig(T::class.simpleName!!, arginit)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are of any type defined in the schema:
   * {@link com.prestongarno.ktq.QSchemaType.QType#stub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QType#configStub(arginit)}.*/
  object QType {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QType} fields
     * @param T The type of the stub which the field is backing
     * @return Grub<InitStub<T>> the delegate which lazily provides a InitStub<T> */
    inline fun <reified T : QSchemaType> stub(): Grub<InitStub<T>> = Grub.type(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QType} fields
     * fragment schema-defined types which take input arguments
     * @param arginit an initializer for the stub field
     * @param T type of the field (schema-defined type)
     * @param A type argument for the argument builder class for that given schema field definition
     * @return Grub<QTypeConfigStub<T, A>> the delegate which lazily provides a QTypeConfigStub<T, A> */
    inline fun <reified T : QSchemaType, reified A : TypeArgBuilder> configStub(
        noinline arginit: (TypeArgBuilder) -> A
    ): Grub<QTypeConfigStub<T, A>>
        = Grub.typeConfig(T::class.simpleName!!, arginit)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which are lists of any built-in scalar (Int, Float, Boolean, String):
   * {@link com.prestongarno.ktq.QSchemaType.QScalarList#stub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QScalarList#configStub(arginit)}.*/
  object QScalarList {
    /**
     * Method which provides a delegate for lists of built-in scalar types
     * @param T The type argument for the list stub, one of: {@link kotlin.Int}, {@link kotlin.String},
     *     {@link kotlin.Float}, {@link kotlin.Boolean}
     * @return Grub<ListStub<T>> the delegate which lazily provides a ListStub<T> */
    inline fun <reified T> stub(): Grub<ListStub<T>> = Grub.scalarList(T::class.simpleName!!)

    /**
     * Method which provides a delegate for lists of built-in scalar types which accept input arguments
     * @param arginit an initializer for the list stub fields
     * @param T type argument for the stub, one of: {@link kotlin.Int}, {@link kotlin.String},
     *     {@link kotlin.Float}, {@link kotlin.Boolean}
     * @param A type argument for the argument builder class for the given schema field definition
     * @return Grub<ListConfig<T, A>> the delegate which lazily provides a ListConfig<T, A> */
    inline fun <reified T : Any, reified A : ListArgBuilder> configStub(noinline arginit: (ListArgBuilder) -> A): Grub<ListConfig<T, A>>
        = Grub.scalarListConfig(T::class.simpleName!!, arginit)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which represent lists of any type defined in the schema:
   * {@link com.prestongarno.ktq.QSchemaType.QTypeList#stub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QTypeList#configStub(arginit)}.*/
  object QTypeList {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QTypeList} fields
     * @param T The type of the stub
     * @return Grub<ListInitStub<T>> the delegate which lazily provides a ListInitStub<T> */
    inline fun <reified T : QSchemaType> stub(): Grub<ListInitStub<T>>
        = Grub.typeList(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QTypeList} fields
     * fragment schema-defined types which take input arguments
     * @param arginit an initializer for the stub field
     * @param T type of the field (schema-defined type)
     * @param A type argument for the argument builder class for that given schema field definition
     * @return Grub<ListConfigType<T, A>> the delegate which lazily provides a ListConfigType<T, A> */
    inline fun <reified T : QSchemaType, reified A : TypeListArgBuilder> configStub(noinline arginit: (TypeListArgBuilder) -> A): Grub<ListConfigType<T, A>>
        = Grub.typeListConfig(T::class.simpleName!!, arginit)
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates fragment
   * fields which represent lists of any custom scalar type defined in the schema:
   * {@link com.prestongarno.ktq.QSchemaType.QCustomScalarList#stub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QCustomScalarList#configStub(arginit)}.*/
  object QCustomScalarList {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} collection
     * fields fragment schema-defined scalar types
     * @param T The type argument for the stub, typedValueFrom a schema-defined CustomScalar type
     * @return Grub<CustomScalarListInitStub<T>> the delegate which lazily provides a CustomScalarListInitStub<T> */
    inline fun <reified T> stub(): Grub<CustomScalarListInitStub<T>> where T : CustomScalar
        = Grub.customScalarList(T::class.simpleName!!)

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} collection
     * fields fragment schema-defined scalar types which take input arguments
     * @param arginit an initializer for the stub field.
     * @param T type of the custom scalar
     * @param A type argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarListArgBuilder<T, A>> the delegate which lazily provides a CustomScalarListArgBuilder<T, A> */
    inline fun <reified T : CustomScalar, reified A : CustomScalarListArgBuilder> configStub(
        noinline arginit: (CustomScalarListArgBuilder) -> A
    ): Grub<CustomScalarListConfigStub<T, A>> =
        Grub.customScalarListConfig(T::class.simpleName!!, arginit)
  }

  object QUnion {
    inline fun <reified T : QSchemaUnion> stub(objectModel: T): Grub<UnionInitStub<T>> =
        Grub.union(objectModel, T::class.simpleName!!)
  }
}

/**
 * Interface for all Custom Scalar types fragment a schema.
 * Supports custom deserialization or mapping to another object by
 * using {@link com.prestongarno.ktq.adapters.custom.QScalarMapper} */
interface CustomScalar : QSchemaType

interface QSchemaUnion : QSchemaType {

  fun on(init: () -> QModel<*>): Unit = TODO()

  val queue: DispatchQueue get() = TODO()

  companion object {
    fun create(objectModel: QSchemaUnion): QSchemaUnion = BaseUnionAdapter(objectModel)
  }
}

