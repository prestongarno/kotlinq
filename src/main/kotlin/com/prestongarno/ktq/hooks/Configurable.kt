package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.TypeStub

/**
 * Represents an intermediate object that enforces
 * a 'scope { //argBuilder }' on a delegate initialization
 * @param T : The type of SchemaStub which will provide a delegate type <T>
 * @param A : The type of ArgBuilder which configures on this field */
interface Config<out T, A: ArgBuilder> : SchemaStub {
  operator fun invoke(arguments: A, scope: (A.() -> Unit)? = null): DelegateProvider<T>
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
