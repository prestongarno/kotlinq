package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.UnionConfigAdapter
import com.prestongarno.ktq.hooks.DelegateProvider
import com.prestongarno.ktq.properties.FragmentProvider

/**
 * Simply a marker interface to group together the different
 * stubbable types for the StubMapper delegate to restrict delegation to */
interface SchemaStub

interface NullableStub<T> : DelegateProvider<T?>

interface TypeStub<T, U> : DelegateProvider<T> where  T : QModel<U>, U : QSchemaType

interface UnionStub : DelegateProvider<QModel<*>?>

interface InterfaceStub<out T : QInterfaceType> : DelegateProvider<QModel<T>?>

interface EnumStub<T> : DelegateProvider<T> where T: QEnumType, T: Enum<*>
/**
 * Interface for all Custom Scalar types fragment a schema.
 * Supports custom deserialization or mapping to another object by
 * using {@link com.prestongarno.ktq.adapters.custom.QScalarMapper} */
interface CustomScalar : QSchemaType

/**
 * Supertype of an object representing
 * a GraphQL Interface definitions */
interface QInterfaceType : QSchemaType

/**
 * Supertype of a GraphQL Enum definition
 *
 * This isn't forced to be an actual native enum. However,
 * the API entrypoints require multiple bounds, one of which being [kotlin.Enum] */
interface QEnumType : QSchemaType

interface QSchemaUnion : QSchemaType {

  val queue: FragmentProvider

  fun on(init: () -> QModel<*>)

  companion object {
    fun create(objectModel: QSchemaUnion): QSchemaUnion =
        UnionConfigAdapter.baseObject(objectModel)
  }
}
