package org.kotlinq

import org.kotlinq.api.GraphQlProperty


open class Model<out T>(val model: T) {

  /**
   * There needs to be an object here which
   * implements a simple interface in the presentation layer
   */

  internal
  val properties = mutableMapOf<String, GraphQlProperty<*>>()

  internal
  fun <T> bind(property: GraphQlProperty<T>): GraphQlProperty<T> {
    properties[property.propertyName] = property
    return property
  }

}