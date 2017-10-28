package com.prestongarno.ktq

import com.prestongarno.ktq.hooks.DelegateProvider

/**
 * Simply a marker interface to group together the different
 * stubbable types for the StubMapper delegate to restrict delegation to */
interface SchemaStub

interface NullableStub<T> : DelegateProvider<T?>

interface TypeStub<T, U> : DelegateProvider<T> where  T : QModel<U>, U : QSchemaType

interface UnionStub : DelegateProvider<QModel<*>?>

interface EnumStub<T> : DelegateProvider<T> where T: QSchemaEnum, T: Enum<*>
