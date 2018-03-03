package org.kotlinq.dsl.extensions

import org.kotlinq.api.Fragment
import org.kotlinq.dsl.FragmentSelection
import org.kotlinq.dsl.ScalarSymbol
import org.kotlinq.dsl.fields.FreeProperty


interface ListDeclarationExtensionScope {

  /**
   * Declare a field returning a list of scalars
   *
   * ```
   *   query {
   *     "field" listOf string
   *   }
   * ```
   */
  infix fun String.listOf(typeSymbol: ScalarSymbol) = this listOf (typeSymbol to false)

  /**
   * Declare a field returning a list of objects
   *
   * ```
   *   query {
   *     "field" listOf !string
   *   }
   * ```
   */
  infix fun String.listOf(nullTypeSymbol: Pair<ScalarSymbol, Boolean>)

  /**
   * Declare a field returning a list of objects
   *
   * ```
   *   query {
   *     "node"("id" to 1234) listOf humanFragment()
   *   }
   * ```
   */
  infix fun FreeProperty.listOf(definition: Fragment)

  /**
   * Declare a field returning a list of multiple fragment types (i.e. union or interface fields)
   *
   * ```
   *   query {
   *     "node"("id" to 1234) listOf {
   *       on..humanFragment()
   *       on..robotFragment()
   *     }
   *   }
   * ```
   */
  fun listOf(definitions: FragmentSelection): FragmentSelection = {
    this.apply(definitions)
    this.flagField(isCollection = true)
  }
}