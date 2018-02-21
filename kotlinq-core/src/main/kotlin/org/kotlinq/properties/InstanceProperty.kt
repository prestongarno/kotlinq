package org.kotlinq.properties

import org.kotlinq.api.Fragment
import org.kotlinq.api.PropertyInfo
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.InstanceAdapter
import org.kotlinq.api.Resolver

internal
class InstanceProperty(
    override val propertyInfo: PropertyInfo,
    override val fragment: Fragment
) : InstanceAdapter {

  override fun isResolved(): Boolean {
    return fragment?.graphQlInstance?.isResolved() || propertyInfo.isNullable
  }

  override fun setValue(result: Map<String, Any?>, resolver: Resolver): Boolean {
    resolver.resolve(result, fragment)
    return isResolved()
  }

  override fun accept(resolver: GraphVisitor) {
    resolver.visit(this)
  }

  override fun getValue(): Fragment = fragment
}
