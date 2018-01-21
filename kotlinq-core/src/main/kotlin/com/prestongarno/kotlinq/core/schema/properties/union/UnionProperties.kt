package com.prestongarno.kotlinq.core.schema.properties.union

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.Model
import com.prestongarno.kotlinq.core.properties.delegates.ConfiguredBlock
import com.prestongarno.kotlinq.core.properties.delegates.NoArgBlock
import com.prestongarno.kotlinq.core.schema.stubs.UnionListStub
import com.prestongarno.kotlinq.core.schema.stubs.UnionStub

typealias UnionProperty<T> = NoArgBlock<UnionStub<T, ArgBuilder>, Model<*>?>
typealias OptionallyConfiguredUnionProperty<T, A> = UnionStub.OptionallyConfigured<T, A>
typealias ConfiguredUnionProperty<T, A> = ConfiguredBlock<UnionStub<T, A>, A, Model<*>?>

typealias UnionListProperty<T> = UnionListStub.NoArg.NotNull<T>
typealias OptionallyConfiguredUnionListProperty<T, A> = UnionListStub.OptionallyConfigured.NotNull<T, A>
typealias ConfiguredUnionListProperty<T, A> = UnionListStub.Configured.NotNull<T, A>
