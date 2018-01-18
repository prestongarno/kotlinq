package com.prestongarno.kotlinq.core.schema.properties.scalar

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.schema.stubs.IntDelegate
import com.prestongarno.kotlinq.core.schema.stubs.IntStub
import com.prestongarno.kotlinq.core.schema.stubs.ScalarDelegate

typealias IntProperty = ScalarDelegate.NoArg<IntDelegate<ArgBuilder>, IntStub>
typealias OptionallyConfiguredIntProperty<A> = IntDelegate.OptionallyConfigured<A>
typealias ConfiguredIntProperty<A> = ScalarDelegate.Configured<IntDelegate<A>, IntStub, A>

