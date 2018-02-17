package org.kotlinq.dsl

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.Kotlinq
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
 *   2. ...
 */
interface PrimitiveScope : GraphQlInstance {

  private val service get() = Kotlinq.adapterService.scalarAdapters

  val String.string: Unit
    get() = this@PrimitiveScope.bindProperty(service.stringAdapter(
        info(this, GraphQlPropertyInfo.STRING)))

  val String.integer: Unit
    get() = this@PrimitiveScope.bindProperty(service.stringAdapter(
        info(this, GraphQlPropertyInfo.INT)))

  val String.boolean: Unit
    get() = this@PrimitiveScope.bindProperty(service.stringAdapter(
        info(this, GraphQlPropertyInfo.BOOL)))

  val String.float: Unit
    get() = this@PrimitiveScope.bindProperty(service.stringAdapter(
        info(this, GraphQlPropertyInfo.FLOAT)))

  operator fun KProperty2<PrimitiveScope, String, Scalar>.unaryMinus() {
    this.invoke(this@PrimitiveScope)
  }

  infix operator fun String.invoke(call: KProperty1<String, Scalar>)

}

