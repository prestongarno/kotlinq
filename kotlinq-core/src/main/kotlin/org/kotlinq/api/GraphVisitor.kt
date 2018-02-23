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

  fun notifyExit(fragment: Fragment) = Unit

  // this is annoying, must be a way to get rid of this
  fun visitFragmentContext(context: FragmentContext) {
    context.fragments.forEach { _, fragment ->
      if (notifyEnter(fragment)) {
        visitContext(fragment)
        notifyExit(fragment)
      }
    }
  }

  fun enterField(adapter: Adapter): Boolean

  companion object {

    fun builder() = Builder()
  }

  class Builder internal constructor() {

    private var onNotifyEnter: (Fragment) -> Boolean = { true }
    private var onVisitContext: (Fragment) -> Unit = { }
    private var onVisitField: (Adapter) -> Unit = { }

    /**
     * Predicate for visiting a fragment.
     * If the predicate evaluates to false, the fragment will not be called with [onVisitContext]
     */
    fun onNotifyEnter(function: (Fragment) -> Boolean) =
        apply { onNotifyEnter = function }

    fun onVisitContext(function: (Fragment) -> Unit) =
        apply { onVisitContext = function }

    fun onVisitField(function: (Adapter) -> Unit) =
        apply { onVisitField = function }

    fun build(): GraphVisitor = DelegatingVisitor(this)


    private
    class DelegatingVisitor(builder: Builder) : GraphVisitor {

      private val onNotify = builder.onNotifyEnter
      private val onContext = builder.onVisitContext
      private val onField = builder.onVisitField

      override fun visitContext(fragment: Fragment) {
        if (onNotify(fragment)) {
          onContext(fragment)
          super.visitContext(fragment)
        }
      }

      override fun enterField(adapter: Adapter): Boolean {
        onField(adapter)
        return true
      }
    }
  }
}


