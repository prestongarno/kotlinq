package org.kotlinq.fragments

import org.kotlinq.api.Fragment
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphVisitor


fun GraphQlInstance.getFragments() = FragmentCollector(this).collect()

private class FragmentCollector(val root: GraphQlInstance) : GraphVisitor {

  private
  val fragments = mutableSetOf<Fragment>()

  fun collect(): Set<Fragment> {
    root.properties.forEach { _, adapter ->
      adapter.accept(this)
    }
    return fragments.toSet()
  }

  override fun visitFragment(target: Fragment) {
    if (!fragments.contains(target)) {
      fragments += target
      target.prototype.graphQlInstance.properties.forEach { _, adapter -> adapter.accept(this) }
    }
  }
}