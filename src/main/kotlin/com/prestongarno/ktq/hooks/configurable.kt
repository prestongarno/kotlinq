package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.SchemaStub

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
