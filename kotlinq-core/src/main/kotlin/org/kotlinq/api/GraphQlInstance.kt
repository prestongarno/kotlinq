package org.kotlinq.api


/**
 * This is used for a query class for property delegates
 * to bind themselves to during object construction
 *
 * @author prestongarno
 */
interface GraphQlInstance {

  val properties: Map<String, Adapter>

  val edges: Sequence<Fragment>

  val nodes: Sequence<Adapter>

  fun isResolved(): Boolean

  /**
   * Prints the GraphQL request
   *
   * @param pretty if true, the returned string will be formatted with line breaks and nested indents by 2 spaces
   * @param inlineFragments if false, the returned string will be formatted with all fragments extracted to the end of the request.
   * Fragment definition naming scheme is frag<TypeName\><index\>, where the index is ordered by order encountered in a BFS from this root context
   */
  fun toGraphQl(pretty: Boolean = false, inlineFragments: Boolean = true): String

  fun accept(visitor: GraphVisitor) = properties.forEach { (name, prop) ->
    prop.accept(visitor)
  }
}