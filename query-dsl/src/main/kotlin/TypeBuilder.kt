package org.kotlinq.dsl

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq
import org.kotlinq.dsl.fields.FreeProperty
import org.kotlinq.dsl.fields.LeafBinding
import kotlin.reflect.KFunction0

@GraphQlDslObject
class TypeBuilder(
    private val graph: GraphQlInstance,
    val defaultTypeName: String = "Object"
) : DslExtensionScope, GraphQlInstance by graph {

  override fun FreeProperty.invoke(block: TypeBuilder.() -> Unit) {
    TODO("not implemented")
  }

  override fun FreeProperty.spread(block: FragmentScopeBuilder.() -> Unit) {
    TODO("not implemented")
  }

  override fun String.invoke(arguments: Map<String, Any>): FreeProperty {
    return FreeProperty(this, arguments)
  }

  override fun KFunction0<LeafBinding>.not() {
    this.invoke().invoke(this@TypeBuilder, false)
  }

  override fun KFunction0<LeafBinding>.unaryMinus() {
    this.invoke().invoke(this@TypeBuilder, false)
  }

  override fun FreeProperty.not(): Node {
    return Node(name, arguments, nullable = false, context = this@TypeBuilder)
  }

  override fun FreeProperty.unaryMinus(): Node {
    return Node(name, arguments, nullable = true, context = this@TypeBuilder)
  }

  override fun String.invoke(arguments: Map<String, Any>, typeName: String?): FreeProperty {
    return FreeProperty(this, arguments)
  }

  override fun String.invoke(vararg arguments: Pair<String, Any>): FreeProperty {
    return FreeProperty(this, arguments.toMap())
  }

  private val adapterService get() = Kotlinq.adapterService
}