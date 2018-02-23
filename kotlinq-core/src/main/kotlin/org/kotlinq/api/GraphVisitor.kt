package org.kotlinq.api


interface GraphVisitor {

  /**
   * Acts as a predicate for entering a fragment,
   * allowing short-circuit traversal (Hierarchical visitor pattern)
   */
  fun notifyEnter(fragment: Fragment, inline: Boolean = false): Boolean = true

  fun visitContext(fragment: Fragment) {
    fragment.graphQlInstance.accept(this)
    notifyExit(fragment)
  }

  fun notifyExit(fragment: Fragment)

  // this is annoying, must be a way to get rid of this
  fun visitFragmentContext(context: FragmentContext) {
    context.fragments.forEach { _, fragment ->
      if (notifyEnter(fragment))
        visitContext(fragment)
      notifyExit(fragment)
    }
  }

  fun enterField(adapter: Adapter): Boolean
}

