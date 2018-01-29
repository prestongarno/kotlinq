package org.kotlinq

import org.kotlinq.api.GraphQlProperty


open class Model<out T>(val model: T) {

  internal
  val properties = mutableMapOf<String, GraphQlProperty<*>>()

  internal
  fun <T> bind(property: GraphQlProperty<T>): GraphQlProperty<T> {
    properties[property.propertyName] = property
    return property
  }

}