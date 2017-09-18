package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.CustomScalarAdapter
import com.prestongarno.ktq.adapters.CustomScalarListAdapter
import com.prestongarno.ktq.adapters.ScalarListAdapter
import com.prestongarno.ktq.adapters.ScalarStubAdapter
import com.prestongarno.ktq.adapters.TypeListAdapter
import com.prestongarno.ktq.adapters.TypeStubAdapter
import com.prestongarno.ktq.adapters.custom.QScalarListMapper
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import com.prestongarno.ktq.internal.Grub

/**
 * The root type of all generated schema objects. Nested objects
 * provide handles for field declarations
 * @author prestongarno */
interface QSchemaType {

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates on
   * fields which are regular Scalars (Int, Float, Boolean, String):
   * {@link com.prestongarno.ktq.QSchemaType.QScalar#stub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QScalar#configStub(arginit)}.*/
  object QScalar {
    /**
     * Method which provides a delegate for fields
     * @param T The type argument for the stub, one of: {@link kotlin.Int}, {@link kotlin.String},
     *     {@link kotlin.Float}, {@link kotlin.Boolean}
     * @return Grub<Stub<T>> the delegate which lazily provides a Stub<T> */
    fun <T> stub(): Grub<Stub<T>> = Grub { ScalarStubAdapter<T, ArgBuilder>(it, { it }) }

    /**
     * Method which provides a delegate for fields
     * @param arginit an initializer for the stub field. <b>Important for auto-generated schema definitions</b>
     * @param T type argument for the stub, one of: {@link kotlin.Int}, {@link kotlin.String},
     *     {@link kotlin.Float}, {@link kotlin.Boolean}
     * @param A type argument for the argument builder class for that given schema field definition
     * @return Grub<QConfigStub<T, A>> the delegate which lazily provides a Stub<T> */
    fun <T : Any, A : ArgBuilder> configStub(arginit: (ArgBuilder) -> A): Grub<QConfigStub<T, A>>
        = Grub { ScalarStubAdapter<T, A>(it, arginit) }
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates on
   * fields which are types from any Schema-defined scalars:
   * {@link com.prestongarno.ktq.QSchemaType.QCustomScalar#stub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QCustomScalar#configStub(arginit)}.*/
  object QCustomScalar {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} fields
     * on schema-defined scalar types
     * @param T The type argument for the stub, from a schema-defined CustomScalar type
     * @return Grub<CustomScalarInitStub<T>> the delegate which lazily provides a CustomScalarInitStub<T> */
    fun <T : CustomScalar> stub(): Grub<CustomScalarInitStub<T>>
        = Grub { CustomScalarAdapter<T, QScalarMapper<T>, T, CustomScalarArgBuilder>(it, { it }) }

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} fields
     * on schema-defined scalar types which take input arguments
     * @param arginit an initializer for the stub field. <b>Important for auto-generated schema definitions</b>
     * @param T type of the custom scalar
     * @param A type argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarConfigStub<T, A>> the delegate which lazily provides a CustomScalarConfigStub<T, A> */
    fun <T : CustomScalar, A : CustomScalarArgBuilder> configStub(
        arginit: (CustomScalarArgBuilder) -> A
    ): Grub<CustomScalarConfigStub<T, A>>
        = Grub { CustomScalarAdapter<T, QScalarMapper<T>, T, A>(it, arginit) }
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates on
   * fields which are of any type defined in the schema:
   * {@link com.prestongarno.ktq.QSchemaType.QType#stub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QType#configStub(arginit)}.*/
  object QType {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QType} fields
     * @param T The type of the stub which the field is backing
     * @return Grub<InitStub<T>> the delegate which lazily provides a InitStub<T> */
    fun <T : QSchemaType> stub(): Grub<InitStub<T>> = Grub { TypeStubAdapter<T, QModel<T>, TypeArgBuilder>(it, { it }) }

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QType} fields
     * on schema-defined types which take input arguments
     * @param arginit an initializer for the stub field
     * @param T type of the field (schema-defined type)
     * @param A type argument for the argument builder class for that given schema field definition
     * @return Grub<QTypeConfigStub<T, A>> the delegate which lazily provides a QTypeConfigStub<T, A> */
    fun <T : QSchemaType, A : TypeArgBuilder> configStub(
        arginit: (TypeArgBuilder) -> A
    ): Grub<QTypeConfigStub<T, A>>
        = Grub { TypeStubAdapter<T, QModel<T>, A>(it, arginit) }
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates on
   * fields which are lists of any built-in scalar (Int, Float, Boolean, String):
   * {@link com.prestongarno.ktq.QSchemaType.QScalarList#stub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QScalarList#configStub(arginit)}.*/
  object QScalarList {
    /**
     * Method which provides a delegate for lists of built-in scalar types
     * @param T The type argument for the list stub, one of: {@link kotlin.Int}, {@link kotlin.String},
     *     {@link kotlin.Float}, {@link kotlin.Boolean}
     * @return Grub<ListStub<T>> the delegate which lazily provides a ListStub<T> */
    fun <T> stub(): Grub<ListStub<T>> = Grub { ScalarListAdapter<T, ListArgBuilder>(it, { it }) }

    /**
     * Method which provides a delegate for lists of built-in scalar types which accept input arguments
     * @param arginit an initializer for the list stub fields
     * @param T type argument for the stub, one of: {@link kotlin.Int}, {@link kotlin.String},
     *     {@link kotlin.Float}, {@link kotlin.Boolean}
     * @param A type argument for the argument builder class for the given schema field definition
     * @return Grub<ListConfig<T, A>> the delegate which lazily provides a ListConfig<T, A> */
    fun <T : Any, A : ListArgBuilder> configStub(arginit: (ListArgBuilder) -> A): Grub<ListConfig<T, A>>
        = Grub { ScalarListAdapter<T, A>(it, arginit) }
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates on
   * fields which represent lists of any type defined in the schema:
   * {@link com.prestongarno.ktq.QSchemaType.QTypeList#stub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QTypeList#configStub(arginit)}.*/
  object QTypeList {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QTypeList} fields
     * @param T The type of the stub
     * @return Grub<ListInitStub<T>> the delegate which lazily provides a ListInitStub<T> */
    fun <T : QSchemaType> stub(): Grub<ListInitStub<T>>
        = Grub { TypeListAdapter<T, QModel<T>, TypeListArgBuilder>(it, { it }) }

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.QTypeList} fields
     * on schema-defined types which take input arguments
     * @param arginit an initializer for the stub field
     * @param T type of the field (schema-defined type)
     * @param A type argument for the argument builder class for that given schema field definition
     * @return Grub<ListConfigType<T, A>> the delegate which lazily provides a ListConfigType<T, A> */
    fun <T : QSchemaType, A : TypeListArgBuilder> configStub(arginit: (TypeListArgBuilder) -> A): Grub<ListConfigType<T, A>>
        = Grub { TypeListAdapter<T, QModel<T>, A>(it, arginit) }
  }

  /**
   * Object which provides 2 convenience methods for generated schemas to create delegates on
   * fields which represent lists of any custom scalar type defined in the schema:
   * {@link com.prestongarno.ktq.QSchemaType.QCustomScalarList#stub()} and
   * {@link com.prestongarno.ktq.QSchemaType.QCustomScalarList#configStub(arginit)}.*/
  object QCustomScalarList {
    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} collection
     * fields on schema-defined scalar types
     * @param T The type argument for the stub, from a schema-defined CustomScalar type
     * @return Grub<CustomScalarListInitStub<T>> the delegate which lazily provides a CustomScalarListInitStub<T> */
    fun <T> stub(): Grub<CustomScalarListInitStub<T>> where T : CustomScalar
        = Grub { CustomScalarListAdapter<T, QScalarListMapper<T>, T, CustomScalarListArgBuilder>(it, { it }) }

    /**
     * Method which provides a delegate for {@link com.prestongarno.ktq.CustomScalar} collection
     * fields on schema-defined scalar types which take input arguments
     * @param arginit an initializer for the stub field.
     * @param T type of the custom scalar
     * @param A type argument for the argument builder class for that given schema field definition
     * @return Grub<CustomScalarListArgBuilder<T, A>> the delegate which lazily provides a CustomScalarListArgBuilder<T, A> */
    fun <T : CustomScalar, A : CustomScalarListArgBuilder> configStub(
        arginit: (CustomScalarListArgBuilder) -> A
    ): Grub<CustomScalarListConfigStub<T, A>> =
        Grub { CustomScalarListAdapter<T, QScalarListMapper<T>, T, A>(it, arginit) }
  }
}

/**
 * Interface for all Custom Scalar types on a schema.
 * Supports custom deserialization or mapping to another object by
 * using {@link com.prestongarno.ktq.adapters.custom.QScalarMapper} */
interface CustomScalar : QSchemaType
