package com.prestongarno.kotlinq.core.schema.properties.enums

import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.schema.properties.ConfiguredProperty


typealias EnumProperty<T> = GraphqlDslBuilder.NoArgContext<T>
typealias OptionallyConfiguredProperty<T, A> = GraphqlDslBuilder.OptionallyConfiguredContext<T, A>
typealias ConfiguredEnumProperty<T, A> = ConfiguredProperty<T, A>

