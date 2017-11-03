package com.prestongarno.ktq.properties

import com.prestongarno.ktq.hooks.nullPointer
import kotlin.reflect.KProperty

/**
 * The immutable object which ultimately ends up inside of
 * a field adapter class to denote reflective information about the GraphQL field
 *
 * Used for generating queries and resolving to graphql
 *
 * <b>** NOTE ** </b>: This property does NOT represent the field name & type on the property that
 * the adapter holding this object represents!!! It references the information defined in
 * the GraphQL Schema. The name of the property it returns to does not need to be the same as [graphqlName]! */
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

  /** TODO Get it out. */
  @Deprecated("leaky abstraction which literally makes this interface pointless")
  val kproperty: KProperty<*>

  companion object {

    /**
     * Factory method to create a property */
    fun from(
        property: KProperty<*>,
        graphqlType: String,
        isList: Boolean,
        graphqlName: String
    ): GraphQlProperty = PropertyImpl(graphqlType, property, isList, graphqlName)

    /**
     * TODO -> get rid of this thing. Although the union
     * types design kind of relies on a meaningless object. Is it better to
     * be explicit about any nullability even if it's done like this? */
    internal val ROOT = object : GraphQlProperty {
      override val kproperty = nullPointer<KProperty<*>>()()
      override val typeKind = nullPointer<PropertyType>()()
      override val graphqlType = nullPointer<String>()()
      override val graphqlName: String = nullPointer<String>()()
      override val isList: Boolean = nullPointer<Boolean>()()
    }
  }
}

private data class PropertyImpl @JvmOverloads constructor(
    override val graphqlType: String,
    override val kproperty: KProperty<*>,
    override val isList: Boolean,
    override val graphqlName: String = kproperty.name
) : GraphQlProperty {

  override val typeKind: PropertyType = PropertyType.from(graphqlType)

  override fun equals(other: Any?): Boolean {
    return (other as? GraphQlProperty)?.kproperty == kproperty
  }

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
}