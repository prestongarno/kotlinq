package org.kotlinq.api


/**
 * @author prestongarno
 */
interface GraphQlInstance {

  val properties: Map<String, Adapter>

  fun isResolved(): Boolean

  fun accept(visitor: GraphVisitor) {
    properties.forEach { it.value.accept(visitor) }
  }
}