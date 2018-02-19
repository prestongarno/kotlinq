package org.kotlinq.dsl

import TypeBuilderBlock
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.dsl.extensions.FreePropertyExtensionScope
import org.kotlinq.dsl.extensions.NullabilityOperatorScope
import org.kotlinq.dsl.fields.FreeProperty
import kotlin.reflect.KFunction0
import kotlin.reflect.KProperty0
import kotlin.reflect.KProperty1

typealias ScalarOp = KProperty0<KProperty1<DslExtensionScope, ScalarSymbol>>

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
interface DslExtensionScope : NullabilityOperatorScope, FreePropertyExtensionScope {

  operator fun String.invoke(
      arguments: Map<String, Any> = emptyMap(),
      typeName: String? = null): FreeProperty = FreeProperty(this, arguments)

  operator fun String.invoke(vararg arguments: Pair<String, Any>): FreeProperty

  /**
   * TODO rigorously define string extension functions to
   * keep from "overload resolution ambiguity" from arity on HOF, etc.
   */
  operator fun String.invoke(block: TypeBuilderBlock): TypeDefinition
}

