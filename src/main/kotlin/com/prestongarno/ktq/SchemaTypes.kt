package com.prestongarno.ktq

import com.prestongarno.ktq.properties.FragmentProvider

/**
 * Interface representing a *concrete* type on a graphql schema.
 */
interface QType : QSchemaType

interface QInterface : QSchemaType

/**
 * Supertype of a GraphQL Enum definition
 *
 * This isn't forced to be an actual native enum. However,
 * the API entrypoints require multiple bounds, one of which being [kotlin.Enum] */
interface QEnumType : QSchemaType

/**
 * Interface for all Custom Scalar types fragment a schema.
 * Supports custom deserialization or mapping to another object by
 * using {@link com.prestongarno.ktq.adapters.custom.QScalarMapper}
 */
interface CustomScalar : QSchemaType

interface QUnionType : QType {

  val queue: FragmentProvider

  fun on(init: () -> QModel<QType>)

  fun <A : ArgBuilder> config(scope: A.() -> Unit)

  companion object {
    fun <U : QUnionType, A : ArgBuilder> create(objectModel: U): QUnionType = TODO()
  }
}
