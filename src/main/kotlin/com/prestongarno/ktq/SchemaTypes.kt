package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.BaseUnionAdapter
import com.prestongarno.ktq.hooks.DelegateProvider
import com.prestongarno.ktq.properties.DispatchQueue

/**
 * Simply a marker interface to group together the different
 * stubbable types for the StubMapper delegate to restrict delegation to */
interface SchemaStub

interface NullableStub<T> : DelegateProvider<T?>

interface TypeStub<T, U> : DelegateProvider<T> where  T : QModel<U>, U : QSchemaType

interface UnionStub : DelegateProvider<QModel<*>?>

interface EnumStub<T> : DelegateProvider<T> where T: QSchemaEnum, T: Enum<*>
/**
 * Interface for all Custom Scalar types fragment a schema.
 * Supports custom deserialization or mapping to another object by
 * using {@link com.prestongarno.ktq.adapters.custom.QScalarMapper} */
interface CustomScalar : QSchemaType

interface QSchemaUnion : QSchemaType {

  fun on(init: () -> QModel<*>)

  val queue: DispatchQueue

  companion object {
    fun create(objectModel: QSchemaUnion): QSchemaUnion = BaseUnionAdapter(objectModel)
  }
}

/**
 * This isn't forced to be an actual native enum. However,
 * the API entrypoints require multiple bounds, one of which being [kotlin.Enum] */
interface QSchemaEnum : QSchemaType