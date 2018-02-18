package org.kotlinq.dsl

import TypeDefinition
import org.kotlinq.api.GraphQlInstance
import kotlin.reflect.KProperty0
import kotlin.reflect.KProperty1
import kotlin.reflect.KFunction0
import org.kotlinq.dsl.fields.FreeProperty

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
 * ***acshually*** it's possible to write
 *
 * ```
 *   query {
 *     !"name"::string
 *     !"friends"first to 100 {
 *       "age"::int
 *     }
 *   }
 * ```
 *
 * by overloading operators on higher-order types.
 *
 *
 * Details:
 *
 *   1. Local scoped extension functions "String.\[integer, boolean, string, etc...\]: (GraphQlInstance) -> FreeProperty"
 *   2. [KFunction0]<([GraphQlInstance]) -> [FreeProperty]> is the type when expressing "stringLiteral::string" etc.
 *     - ^ is a DSL expression for a *dynamic name* with *scalar type*
 *   3. Local operator overload operator function [KFunction0]<([GraphQlInstance]) -> [FreeProperty]>.not()
 *     - Nested higher-order type *extension* function definitions is the key to this
 *     - the [KFunction0] type guarantees direct invocation, and the returned lambda type is invoked with the current instance's context passed ad the argument, which returns the name + type information.
 *     - This is done from our controlled [GraphBuilder] scope on calling the operator functions ***not*** and ***unaryMinus***.
 *   4. ***not*** operator overload represents *non-nullability*
 *   5. ***unaryMinus*** operator overload represents field *nullability*
 *   6. Both operators are required because provide an instance-bound frame of reference to create the resulting [org.kotlinq.api.Adapter] property from property meta information
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
