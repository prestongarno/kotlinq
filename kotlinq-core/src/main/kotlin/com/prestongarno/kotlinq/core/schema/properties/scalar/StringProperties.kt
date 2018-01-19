package com.prestongarno.kotlinq.core.schema.properties.scalar

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.schema.properties.ConfiguredProperty
import com.prestongarno.kotlinq.core.schema.properties.OptionallyConfiguredProperty
import com.prestongarno.kotlinq.core.schema.properties.Property
import com.prestongarno.kotlinq.core.schema.stubs.ScalarDelegate
import com.prestongarno.kotlinq.core.schema.stubs.StringDelegate
import com.prestongarno.kotlinq.core.schema.stubs.StringStub

typealias StringProperty = ScalarDelegate.NoArg<StringDelegate<ArgBuilder>, StringStub>
typealias OptionallyConfiguredStringProperty<A> = StringDelegate.OptionallyConfigured<A>
typealias ConfiguredStringProperty<A> = ScalarDelegate.Configured<StringDelegate<A>, StringStub, A>

