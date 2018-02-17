package org.kotlinq.dsl

import org.kotlinq.api.Adapter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.Kotlinq
import org.kotlinq.dsl.Scalar.FloatScalar
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.KProperty2

/**
 * In order to be able to query like:
 *
 * ```
 *     query {
 *       "name"::string
 *       "age"::int
 *
 *       "friends"(first to 10) {
 *         "name"::string
 *       }
 *     }
 *
 * ```
 *
 * *unfortunately* is not possible, but something like this is:
 *
 * ```
 *   query {
 *     -"name"::string
 *     "friends"(first to 100) {
 *       -"age"::int
 *     }
 *   }
 * ```
 *
 *
 * To do so, need to:
 *
 *   1. Locally-scoped String extension **properties** "string", "int", "float", and "boolean"
 *   2. String.unaryMinus() extension *operator* function
 *        - In the implementation, set a private variable String on call
 *
 */
interface PrimitiveScope : GraphQlInstance {

  private val service get() = Kotlinq.adapterService.scalarAdapters

  val float get() = PrimitiveScope::floatOp

  val string get() = PrimitiveScope::stringOp

  val integer get() = PrimitiveScope::intOp

  val boolean get() = PrimitiveScope::boolOp

  operator fun String.invoke(typeKind: KProperty1<PrimitiveScope, Scalar>) {
  }

}

private
val PrimitiveScope.stringOp get() = Scalar.StringScalar
private
val PrimitiveScope.intOp get() = Scalar.IntScalar
private
val PrimitiveScope.boolOp get() = Scalar.BooleanScalar
private
val PrimitiveScope.floatOp get() = Scalar.FloatScalar
