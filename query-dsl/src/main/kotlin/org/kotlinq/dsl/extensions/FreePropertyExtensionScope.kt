package org.kotlinq.dsl.extensions

import org.kotlinq.api.Fragment
import org.kotlinq.dsl.FragmentContextBuilder
import org.kotlinq.dsl.TypeBuilder
import org.kotlinq.dsl.fields.FreeProperty


interface FreePropertyExtensionScope {

  /**
   * 'Terminal' operation (i.e. binds property after this).
   * Call on a receiver property declaration to define
   * a single inline type selection set
   *
   * Example:
   *
   * ```
   *   query {
   *     "search"("text" to "hello").define("SearchResultConnection") {
   *       "resultCount"(integer)
   *     }
   *   }
   * ```
   *
   */
  fun FreeProperty.define(typeName: String, block: TypeBuilder.() -> Unit)


  /**
   * 'Terminal' operation for a collection of objects declared in the
   * [FragmentContextBuilder] block.
   *
   * Call on a [FreeProperty] declaration to define
   * with fragment spread operations on named types
   *
   * Example:
   *
   * ```
   *   query {
   *     "search"("text" to "hello").define("SearchResultConnection") {
   *       nodes .. {
   *         on("Human") {
   *           "name"(string)
   *         }
   *         on("Robot") {
   *           "modelNumber"(integer)
   *         }
   *       }
   *     }
   *   }
   * ```
   *
   */
  operator fun FreeProperty.rangeTo(block: FragmentContextBuilder.() -> Unit)

  operator fun String.rangeTo(block: FragmentContextBuilder.() -> Unit) =
      FreeProperty(this).rangeTo(block)

  /**
   * Example:
   *
   * ```
   *   query {
   *     "search"("text" to "hello") on searchResultConnection(...)
   *   }
   *```
   * Where:
   *
   * ```
   * fun searchResultConnection(humanDef: TypeDefinition, robodDef: TypeDefinition): TypeDefinition =
   *   define("SearchResultConnection") {
   *       "nodes" ..{
   *         on(humanDef)
   *         on(robotDef)
   *       }
   *     }
   *   }
   * ```
   *
   */
  infix fun FreeProperty.on(context: Fragment)
}
