package com.prestongarno.kotlinq.core.schema.properties.scalar

import com.prestongarno.kotlinq.core.schema.stubs.CustomScalarListStub
import com.prestongarno.kotlinq.core.schema.stubs.CustomScalarStub


typealias CustomScalarProperty<T> = CustomScalarStub.NoArg<T>
typealias OptionallyConfiguredCustomScalarProperty<T, A> = CustomScalarStub.OptionallyConfigured<T, A>
typealias ConfiguredCustomScalarProperty<T, A> = CustomScalarStub.Configured<T, A>

typealias CustomScalarListProperty<T> = CustomScalarListStub.NoArg<T>
typealias OptionallyConfiguredCustomScalarListProperty<T, A> = CustomScalarListStub.OptionallyConfigured<T, A>
typealias ConfiguredCustomScalarListProperty<T, A> = CustomScalarListStub.Configured<T, A>
