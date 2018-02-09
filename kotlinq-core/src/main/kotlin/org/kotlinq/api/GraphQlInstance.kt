package org.kotlinq.api


/**
 * Context object. non-scalar user GraphQL type/query definitions
 * will subclass a base class implementing this interface
 */
interface Context {
  val graphQlInstance: GraphQlInstance
}

/**
 * This is used for a query class for property delegates
 * to bind themselves to during object construction
 *
 * @author prestongarno
 */
interface GraphQlInstance {

  val graphQlTypeName: String

  val properties: Map<String, Adapter>

  fun isResolved(): Boolean

  /** TODO probably should not expose this */
  fun bindProperty(adapter: Adapter)

  /**
   * Prints the GraphQL request
   *
   * @param pretty if true, the returned string will be formatted with line breaks and nested indents by 2 spaces
   * @param extractFragments if true, the returned string will be formatted with all fragments extracted to the end of the request.
   * Fragment definition naming scheme is frag<TypeName\><index\>, where the index is ordered by order encountered in a BFS from this root context
   */
  fun toGraphQl(pretty: Boolean = false, extractFragments: Boolean = false): String
}