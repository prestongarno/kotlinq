package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.TypeStub

/**
 * Represents an intermediate object that enforces
 * a 'config { //arguments }' on a delegate initialization */
interface Config<out T, out A: ArgBuilder> : SchemaStub {
  fun config(provider: A.() -> Unit): DelegateProvider<T>
}

/**
 * Represents an intermediate delegate object which
 * optionally takes arguments as a 'config { }' block*/
interface OptionalConfig<out T> : Config<T, ArgBuilder>, DelegateProvider<T>

interface TypeConfig<T: QSchemaType, out A: ArgBuilder> : SchemaStub {
  fun config(provider: A.() -> Unit): InitStub<T>
}

interface InitStub<T : QSchemaType> : SchemaStub {
  fun <U : QModel<T>> querying(init: () -> U): TypeStub<U, T>
}
