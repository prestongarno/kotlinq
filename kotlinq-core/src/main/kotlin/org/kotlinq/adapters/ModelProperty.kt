package org.kotlinq.adapters

import org.kotlinq.api.ModelAdapter
import org.kotlinq.api.Context
import org.kotlinq.api.Fragment
import org.kotlinq.api.GraphQlType
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.Resolver

internal
class ModelPropertyImpl(
    override val name: String,
    override val type: GraphQlType,
    override val arguments: Map<String, Any>,
    initializer: () -> Context,
    override val fragment: Fragment = FragmentImpl(initializer)
) : ModelAdapter {

  private var instance: Context? = null

  override fun isResolved(): Boolean {
    return instance?.graphQlInstance?.isResolved() == true || type.isNullable
  }

  override fun setValue(result: Map<String, Any?>, resolver: Resolver): Boolean {
    this.instance = fragment.initializer().apply {
      resolver.resolve(result, this)
    }
    return isResolved()
  }

  override fun accept(resolver: GraphVisitor) {
    resolver.visitModel(this)
    resolver.visitFragment(fragment)
  }

  override fun getValue(): Context? = instance
}
