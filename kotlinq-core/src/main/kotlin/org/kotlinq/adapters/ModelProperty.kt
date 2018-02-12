package org.kotlinq.adapters

import org.kotlinq.api.ModelAdapter
import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlType
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.Resolver
import kotlin.reflect.KType

internal
class ModelPropertyImpl(
    override val name: String,
    override val type: GraphQlType,
    override val arguments: Map<String, Any>,
    override val initializer: () -> Context
) : ModelAdapter {

  private var instance: Context? = null

  override val prototype: Context by lazy { initializer() }

  override fun isResolved(): Boolean {
    return instance?.graphQlInstance?.isResolved() == true || type.isNullable
  }

  override fun setValue(result: Map<String, Any?>, resolver: Resolver): Boolean {
    this.instance = initializer().apply {
      resolver.resolve(result, this)
    }
    return isResolved()
  }

  override fun accept(resolver: GraphVisitor) {
    resolver.visitModel(this)
    resolver.visitFragment(this)
  }

  override fun getValue(): Context? = instance
}
