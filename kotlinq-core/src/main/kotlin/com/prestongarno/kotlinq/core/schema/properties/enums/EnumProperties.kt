package com.prestongarno.kotlinq.core.schema.properties.enums

import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.schema.properties.ConfiguredProperty


typealias EnumProperty<T> = GraphqlDslBuilder.NoArgContext<T>
typealias OptionallyConfiguredProperty<T, A> = GraphqlDslBuilder.OptionallyConfiguredContext<T, A>
typealias ConfiguredEnumProperty<T, A> = ConfiguredProperty<T, A>

typealias NullableEnumProperty<T> = GraphqlDslBuilder.NoArgContext<T>
typealias NullableOptionallyConfiguredProperty<T, A> = GraphqlDslBuilder.OptionallyConfiguredContext<T, A>
typealias NullableConfiguredEnumProperty<T, A> = ConfiguredProperty<T, A>

typealias EnumListProperty<T> = GraphqlDslBuilder.NoArgContext<List<T>>
typealias OptionallyConfiguredListProperty<T, A> = GraphqlDslBuilder.OptionallyConfiguredContext<List<T>, A>
typealias ConfiguredEnumListProperty<T, A> = ConfiguredProperty<List<T>, A>
