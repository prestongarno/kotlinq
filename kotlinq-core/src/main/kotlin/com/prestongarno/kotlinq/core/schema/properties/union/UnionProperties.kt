package com.prestongarno.kotlinq.core.schema.properties.union

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.properties.delegates.ConfiguredBlock
import com.prestongarno.kotlinq.core.properties.delegates.NoArgBlock
import com.prestongarno.kotlinq.core.schema.stubs.UnionStub

typealias UnionProperty<T> = NoArgBlock<UnionStub<T, ArgBuilder>, QModel<*>?>
typealias OptionallyConfiguredUnionProperty<T, A> = UnionStub.OptionallyConfigured<T, A>
typealias ConfiguredUnionProperty<T, A> = ConfiguredBlock<UnionStub<T, A>, A, T>