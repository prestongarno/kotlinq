package org.kotlinq.dsl.extensions

import org.kotlinq.api.Fragment
import org.kotlinq.dsl.ScalarSymbol
import org.kotlinq.dsl.fields.FreeProperty


interface StringExtensionScope {

  /** kotlin.String type hint */
  val string: ScalarSymbol get() = ScalarSymbol.StringSymbol
  /** kotlin.Int type hint */
  val integer: ScalarSymbol get() = ScalarSymbol.IntSymbol
  /** kotlin.Boolean type hint */
  val boolean: ScalarSymbol get() = ScalarSymbol.BooleanSymbol
  /** kotlin.Float type hint */
  val float: ScalarSymbol get() = ScalarSymbol.FloatSymbol

  /**
   * Example:
   *
   * ```
   *   fragment("Person") {
   *     "name"(string, mapOf("charset" to Charsets.UTF8))
   *   }
   * ```
   */
  operator fun String.invoke(
      typeSymbol: ScalarSymbol,
      arguments: Map<String, Any> = emptyMap()
  ) = invoke(typeSymbol to false, arguments)

  /**
   * Example:
   *
   * ```
   *   fragment("Person") {
   *     "name"(string, "charset" to Charsets.UTF8)
   *   }
   * ```
   */
  operator fun String.invoke(
      typeSymbol: ScalarSymbol,
      vararg arguments: Pair<String, Any>) =
      invoke(typeSymbol to false, *arguments)

  /**
   * Example:
   *
   * ```
   *   fragment("Person") {
   *     "name"(!string, mapOf("charset" to Charsets.UTF8))
   *   }
   * ```
   */
  operator fun String.invoke(
      typeSymbol: Pair<ScalarSymbol, Boolean>,
      arguments: Map<String, Any> = emptyMap())

  /**
   * Example:
   *
   * ```
   *   fragment("Person") {
   *     "name"(!string, "charset" to Charsets.UTF8)
   *   }
   * ```
   */
  operator fun String.invoke(
      typeSymbol: Pair<ScalarSymbol, Boolean>,
      vararg arguments: Pair<String, Any>) =
      invoke(typeSymbol, arguments.toMap())

  /**
   * For arguments on fields returning objects
   *
   *
   * Example:
   *
   *
   * ```
   *   query {
   *     "nodes"(mapOf("first" to 10)) on fragmentDefinition()
   *   }
   * ```
   */
  operator fun String.invoke(
      arguments: Map<String, Any> = emptyMap()
  ): FreeProperty = FreeProperty(this, arguments)

  /**
   * For arguments on fields returning objects
   *
   *
   * Example:
   *
   *
   * ```
   *   query {
   *     "nodes"("first" to 10) on fragmentDefinition()
   *   }
   * ```
   */
  operator fun String.invoke(vararg arguments: Pair<String, Any>): FreeProperty =
      FreeProperty(this, arguments.toMap())

  /**
   * For arguments on fields returning objects
   *
   *
   * Example:
   *
   *
   * ```
   *   query {
   *     "nodes"(mapOf("first" to 10)) on fragmentDefinition()
   *   }
   * ```
   */
  infix fun String.on(definition: Fragment)
}