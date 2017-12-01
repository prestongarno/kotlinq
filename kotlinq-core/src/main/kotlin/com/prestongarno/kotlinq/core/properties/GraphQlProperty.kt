/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.prestongarno.kotlinq.core.properties

import kotlin.reflect.KProperty

/**
 * The immutable object which ultimately ends up inside of
 * a field adapter class to denote reflective information about the GraphQL field
 *
 * Used for generating queries and resolving to graphql
 *
 * <b>** NOTE ** </b>: This property does NOT represent the field name & type on the property that
 * the adapter holding this object represents!!! It references the information defined in
 * the GraphQL Schema. The name of the property it returns may not be the same as the property on the actual model! */
interface GraphQlProperty {

  /**
   * The [com.prestongarno.ktq.properties.PropertyType] that this field is */
  val typeKind: PropertyType

  /**
   * The name of the type that the GraphQL schema declares */
  val graphqlType: String

  /**
   * the name of the GraphQL property declared in the schema */
  val graphqlName: String

  /**
   * `[isList] == true` if the schema defines `[graphqlName]: [graphqlType]`
   *
   * Used for Boxing/generics on the JVM */
  val isList: Boolean

  companion object {

    /**
     * Factory method to create a property */
    fun from(
        property: KProperty<*>,
        graphqlType: String,
        isList: Boolean,
        graphqlName: String,
        typeKind: PropertyType = PropertyType.from(graphqlType)
    ): GraphQlProperty = PropertyImpl(graphqlType, property, isList, graphqlName, typeKind)

  }
}

private data class PropertyImpl @JvmOverloads constructor(
    override val graphqlType: String,
    private val kproperty: KProperty<*>,
    override val isList: Boolean,
    override val graphqlName: String = kproperty.name,
    override val typeKind: PropertyType = PropertyType.from(graphqlType)
) : GraphQlProperty {

  override fun hashCode(): Int {
    var result = kproperty.hashCode()
    result = 31 * result + graphqlType.hashCode()
    result = 31 * result + typeKind.hashCode()
    result = 31 * result + graphqlName.hashCode()
    result = 31 * result + isList.hashCode()
    return result
  }

  override fun toString(): String =
      "Property('$graphqlName:${if (isList) "[$graphqlType]" else graphqlType}' ($typeKind)"

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as PropertyImpl

    if (graphqlType != other.graphqlType) return false
    if (kproperty != other.kproperty) return false
    if (isList != other.isList) return false
    if (graphqlName != other.graphqlName) return false
    if (typeKind != other.typeKind) return false

    return true
  }
}