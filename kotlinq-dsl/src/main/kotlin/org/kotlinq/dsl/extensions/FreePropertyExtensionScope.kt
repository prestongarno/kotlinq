package org.kotlinq.dsl.extensions

import org.kotlinq.api.Fragment
import org.kotlinq.dsl.FragmentSelection
import org.kotlinq.dsl.SelectionSet
import org.kotlinq.dsl.fields.FreeProperty


interface FreePropertyExtensionScope {

  /**
   * Convenience method for inline type definitions
   *
   * Example:
   *
   * ```
   *   query {
   *     "search"("text" to "hello") on def("SearchResultConnection") {
   *       "resultCount"(integer)
   *     }
   *   }
   * ```
   *
   */
  fun def(typeName: String, block: SelectionSet): Fragment


  /**
   * Call on a [FreeProperty] declaration to define
   * with fragment spread operations on named types
   *
   * Example:
   *
   * ```
   *   query {
   *     nodes("first" to 10) .. {
   *       on("Human") {
   *         "name"(string)
   *       }
   *       on("Robot") {
   *         "modelNumber"(integer)
   *       }
   *     }
   *   }
   * ```
   *
   */
  operator fun FreeProperty.rangeTo(block: FragmentSelection)

  /**
   * Call on a [FreeProperty] declaration to define
   * with fragment spread operations on named types
   *
   * Example:
   *
   * ```
   *   query {
   *     "nodes" .. {
   *       on("Human") {
   *         "name"(string)
   *       }
   *       on("Robot") {
   *         "modelNumber"(integer)
   *       }
   *     }
   *   }
   * ```
   *
   */
  operator fun String.rangeTo(block: FragmentSelection) =
      FreeProperty(this).rangeTo(block)

  /**
   * Use this when the GraphQl field is a *concrete* type
   *
   * Example:
   *
   * ```
   *   query {
   *     "search"("text" to "hello") on searchResult()
   *   }
   *
   *
   * fun searchResult() =
   *   fragment("SearchResultConnection") {
   *     "totalCount"(integer)
   *   }
   * ```
   *
   */
  infix fun FreeProperty.on(context: Fragment)
}
