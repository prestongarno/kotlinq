package com.prestongarno.kotlinq.core.schema.properties.type

import com.prestongarno.kotlinq.core.schema.stubs.TypeListStub
import com.prestongarno.kotlinq.core.schema.stubs.TypeStub

typealias TypeProperty<T> = TypeStub.NoArg.NotNull<T>
typealias OptionallyConfiguredTypeProperty<T, A> = TypeStub.OptionallyConfigured.NotNull<T, A>
typealias ConfiguredTypeProperty<T, A> = TypeStub.OptionallyConfigured.NotNull<T, A>

typealias NullableTypeProperty<T> = TypeStub.NoArg<T>
typealias NullableOptionallyConfiguredTypeProperty<T, A> = TypeStub.OptionallyConfigured<T, A>
typealias NullableConfiguredTypeProperty<T, A> = TypeStub.Configured<T, A>

typealias TypeListProperty<T> = TypeListStub.NoArg<T>
typealias OptionallyConfiguredTypeListProperty<T, A> = TypeListStub.OptionallyConfigured<T, A>
typealias ConfiguredTypeListProperty<T, A> = TypeListStub.Configured<T, A>
