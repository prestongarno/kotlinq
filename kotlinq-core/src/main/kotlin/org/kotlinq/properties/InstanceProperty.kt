package org.kotlinq.properties

import org.kotlinq.api.ModelAdapter
import org.kotlinq.api.Definition
import org.kotlinq.api.Fragment
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.Resolver

internal
class InstanceProperty(
    override val propertyInfo: GraphQlPropertyInfo,
    initializer: () -> Definition
) : ModelAdapter {

  override val fragment: Fragment =
      Kotlinq.createFragment(initializer)

  private var instance: Definition? = null

  override fun isResolved(): Boolean {
    return instance?.graphQlInstance?.isResolved() == true || propertyInfo.isNullable
  }

  override fun setValue(result: Map<String, Any?>, resolver: Resolver): Boolean {
    this.instance = fragment.initializer().apply {
      resolver.resolve(result, this)
    }
    return isResolved()
  }

  override fun accept(resolver: GraphVisitor) {
    resolver.visit(this)
  }

  override fun getValue(): Definition? = instance
}
