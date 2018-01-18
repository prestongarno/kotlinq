package com.prestongarno.kotlinq.core.schema.properties.scalar

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.schema.stubs.BooleanDelegate
import com.prestongarno.kotlinq.core.schema.stubs.BooleanStub
import com.prestongarno.kotlinq.core.schema.stubs.ScalarDelegate

typealias BooleanProperty = ScalarDelegate.NoArg<BooleanDelegate<ArgBuilder>, BooleanStub>
typealias OptionallyConfiguredBooleanProperty<A> = BooleanDelegate.OptionallyConfigured<A>
typealias ConfiguredBooleanProperty<A> = ScalarDelegate.Configured<BooleanDelegate<A>, BooleanStub, A>