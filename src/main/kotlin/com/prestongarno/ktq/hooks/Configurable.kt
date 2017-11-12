package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.TypeStub
import com.prestongarno.ktq.adapters.QField
import kotlin.reflect.KProperty

/**
 * Represents an intermediate object that enforces passing of an [ArgBuilder]
 * instance to the object. This is for GraphQL fields with one or more non-null
 * arguments specified in the schema
 * @param T : The type of SchemaStub which will provide a delegate type <T>
 * @param A : The type of ArgBuilder which configures on this field
 */
interface Configurable<out T : DelegateProvider<*>, in A: ArgBuilder> : SchemaStub {
  operator fun invoke(arguments: A, scope: (T.() -> Unit)? = null): T
}

/**
 * Represents an intermediate object that optionally allows invoking by passing
 * an [ArgBuilder] instance to the object. This is for GraphQL fields with
 * exactly zero non-null arguments specified for this field, but with one or
 * more arguments/parameters specified
 * @param D : The type of [DelegateProvider] which this field supplies
 * @param A : The type of ArgBuilder which configures on this field
 */
interface OptionalConfig<out D : DelegateProvider<T>, out T : Any?, in A: ArgBuilder> : SchemaStub {
  operator fun invoke(arguments: A? = null, scope: (D.() -> Unit)? = null): D

  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
      invoke().provideDelegate(inst, property)
}

/**
 * Represents an intermediate object that optionally allows invoking by passing
 * an [ArgBuilder] instance to the object. This is for GraphQL fields with
 * exactly zero non-null arguments specified for this field, and zero arguments/parameters specified.
 * This interface exists to support adding arguments arbitrarily to GraphQL queries/mutations
 * @param D : The type of [DelegateProvider] which this field supplies
 */
interface NoArgDelegate<out D : DelegateProvider<T>, out T : Any?> : SchemaStub {
  operator fun invoke(arguments: ArgBuilder? = ArgBuilder(), scope: (D.() -> Unit)? = null): D

  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
      invoke().provideDelegate(inst, property)
}

/**
 * Represents an intermediate object that enforces
 * a 'scope { //argBuilder }' on a delegate initialization
 * @param T : The type of QSchemaType (object) which provides a delegate type <T>
 * @param A : The type of ArgBuilder which configures on this field */
interface TypeConfig<T: QType, A: ArgBuilder> : SchemaStub {
  operator fun invoke(arguments: A, scope: (A.() -> Unit)? = null): InitStub<T, A>
}

/**
 * A terminal delegate type that enforces a '() -> U' initializer for creating the object type
 *
 * @param T : The type of QSchemaType (object) which provides a delegate type <T> */
interface InitStub<T : QType, A : ArgBuilder> : SchemaStub {

  operator fun invoke(arguments: A, scope: (A.() -> Unit)? = null): InitStub<T, A>
  /**
   * Function to pass a function which returns the immutable delegate object for this field
   * @param init : a function which returns the model instance for the GraphQL query or mutation
   * @return an immutable [com.prestongarno.ktq.TypeStub]<U, T> which provides an instance of U when resolved
   * */
  fun <U : QModel<T>> querying(init: () -> U): TypeStub<U, T>
}
