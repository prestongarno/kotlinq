package org.kotlinq.dsl

import TypeDefinition
import org.kotlinq.api.GraphQlInstance
import kotlin.reflect.KProperty0
import kotlin.reflect.KProperty1

typealias ScalarOp = KProperty0<KProperty1<DslExtensionScope, Scalar>>

/**
 * Ideally it would be nice to able to query like:
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
 *     "name"(::string)
 *     "friends"(first to 100) {
 *       "age"(::int)
 *     }
 *   }
 * ```
 *
 *
 * To do so, need to:
 *
 *   1. Locally-scoped String extension **properties** "string", "int", "float", and "boolean"
 *   2. String extension *operator* function invoke
 *        - In the implementation, set a private variable String on call
 *
 */
interface DslExtensionScope : GraphQlInstance {

  val float: KProperty1<DslExtensionScope, Scalar>
    get() = DslExtensionScope::floatOp

  val string: KProperty1<DslExtensionScope, Scalar>
    get() = DslExtensionScope::stringOp

  val boolean: KProperty1<DslExtensionScope, Scalar>
    get() = DslExtensionScope::boolOp

  val integer: KProperty1<DslExtensionScope, Scalar>
    get() = DslExtensionScope::intOp

  operator fun String.invoke(typeKind: KProperty0<KProperty1<DslExtensionScope, Scalar>>) =
      bindProperty(typeKind.get().invoke(this@DslExtensionScope).bindToName(this))

  operator fun String.invoke(
      arguments: Map<String, Any> = emptyMap(),
      typeName: String? = null,
      block: TypeBuilder.() -> Unit)

  operator fun String.invoke(
      vararg arguments: Pair<String, Any>,
      typeName: String? = null,
      block: TypeBuilder.() -> Unit
  ) = invoke(arguments.toMap(), typeName, block)

  operator fun String.invoke(
      arguments: Map<String, Any> = emptyMap(),
      typeName: String? = null,
      definition: TypeDefinition)

  infix fun String.spread(block: FragmentScopeBuilder.() -> Unit)
}

private
val DslExtensionScope.stringOp get() = Scalar.StringScalar
private
val DslExtensionScope.intOp get() = Scalar.IntScalar
private
val DslExtensionScope.boolOp get() = Scalar.BooleanScalar
private
val DslExtensionScope.floatOp get() = Scalar.FloatScalar
