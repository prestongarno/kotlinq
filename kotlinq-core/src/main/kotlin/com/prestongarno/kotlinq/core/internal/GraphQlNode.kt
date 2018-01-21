package com.prestongarno.kotlinq.core.internal

import com.prestongarno.kotlinq.core.adapters.Adapter
import com.prestongarno.kotlinq.core.api.FragmentContext
import com.prestongarno.kotlinq.core.api.ModelProvider
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.schema.stubs.PrimitiveStub

internal
interface GraphQlNode {


  val properties: List<Adapter>

  val scalars: Set<PrimitiveStub>

  val models: Set<ModelProvider>

  // TODO make [FragmentContext] sub-type QField
  val fragments: Set<FragmentContext>

  val propertyMap: Map<GraphQlProperty, Adapter>

  class Constructor {

    private val adapters: MutableSet<Adapter> = mutableSetOf()

    fun bindToContext(graphqlField: Adapter) { adapters += graphqlField }

    fun toNode() = object : GraphQlNode {
      override val properties: List<Adapter> = adapters.toList()
      override val propertyMap = adapters.map { it.qproperty to it }.toMap()
      override val scalars = adapters.filterIsInstance<PrimitiveStub>().toSet()
      override val models = adapters.filterIsInstance<ModelProvider>().toSet()
      override val fragments = adapters.filterIsInstance<FragmentContext>().toSet()
    }
  }
}