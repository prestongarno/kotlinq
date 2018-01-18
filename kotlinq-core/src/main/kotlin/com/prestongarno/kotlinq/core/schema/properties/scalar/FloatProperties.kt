package com.prestongarno.kotlinq.core.schema.properties.scalar

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.schema.stubs.FloatDelegate
import com.prestongarno.kotlinq.core.schema.stubs.FloatStub
import com.prestongarno.kotlinq.core.schema.stubs.ScalarDelegate

typealias FloatProperty = ScalarDelegate.NoArg<FloatDelegate<ArgBuilder>, FloatStub>
typealias OptionallyConfiguredFloatProperty<A> = FloatDelegate.OptionallyConfigured<A>
typealias ConfiguredFloatProperty<A> = ScalarDelegate.Configured<FloatDelegate<A>, FloatStub, A>
