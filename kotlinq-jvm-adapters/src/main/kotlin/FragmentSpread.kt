package org.kotlinq.jvm

import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentContext
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.PropertyInfo

class FragmentSpread<out T : Data?> internal constructor(
    internal val fragmentLookupTable: Map<Fragment, (GraphQlResult) -> T>,
    override val propertyInfo: PropertyInfo
) : FragmentContext {

  override val fragments: Map<String, Fragment> =
      fragmentLookupTable.keys
          .map { it.typeName to it }
          .toMap()

  override fun getValue(): Any? { TODO("not implemented") }

  override fun accept(resolver: GraphVisitor) =
      resolver.visitFragmentContext(this)

  override fun isResolved(): Boolean { TODO("not implemented") }

}