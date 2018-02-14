package org.kotlinq.api

import org.kotlinq.common.empty


/**
 * Abstraction on top of [GraphVisitor] interface,
 * reduces the graph visitor to listen for only "Nodes" and "Edges"
 *
 * [GraphVisitor] is still needed in order to resolve the
 * graph with proper values from deserialization, so this might be called 'backwards abstraction'
 *
 * TODO     When traversing the graph, the [GraphVisitor] should be passed as a parameter to the listeners,
 * TODO     so that listeners can control the paths that the underlying implementation aware visitor takes
 * TODO     (i.e. when coming across undiscovered fragments,
 * TODO     implementations might be able to call [Fragment.prototype].graphQlInstance.accept(\<resolver passed as parameter\>)
 * TODO     to continue to all adjacent nodes)
 *
 * TODO [Context] is making this overcomplicated, should maybe add "visitSelectionSet(instance: GraphQlInstance)" method?
 * TODO (cont.) otherwise both Visitor interfaces are relying on [ModelAdapter] and [Fragment] implementations to call visitor on edge nodes
 */
class AbstractGraphVisitor(
    val nodeListener: (Adapter) -> Unit,
    val controller: GraphVisitor.(Fragment) -> Unit = { it.prototype.graphQlInstance.accept(this) }, // default behaviour is to traverse entire graph
    val exitNodeListener: (Adapter) -> Unit = empty()
) {


  private val implementationAwareVisitor = object : GraphVisitor {

    override fun visitDeserializer(target: DeserializingAdapter) = visitNodeDef(target)
    override fun visitFragment(target: Fragment) = controller(target)
    override fun visitScalar(target: ParsingAdapter) = visitNodeDef(target)

    override fun visitModel(target: ModelAdapter) {
      nodeListener(target)
      this.controller(target.fragment) // instead of visiting fragment def, pass control to callback
      exitNodeListener(target)
    }

    override fun visitFragmentContext(target: FragmentAdapter) {
      nodeListener(target)
      target.fragments.values.forEach(this::visitFragment)
      exitNodeListener(target)
    }

    private fun visitNodeDef(adapter: Adapter) {
      nodeListener(adapter)
      exitNodeListener(adapter)
    }

  }

  /**
   * Traverse the GraphQL document
   * @param graph the instance to analyze
   */
  fun traverse(graph: GraphQlInstance) =
      graph.accept(implementationAwareVisitor)


  companion object {

    fun createGeneralizedGraphVisitor(block: Builder.() -> Unit)
        : AbstractGraphVisitor = Builder().apply(block).let {
          AbstractGraphVisitor(it.nodeListener, it.fragmentListener, it.exitNodeListener)
        }

    fun generalizedGraphVisitor(
        nodeListener: (Adapter) -> Unit = empty(),
        fragmentListener: GraphVisitor.(Fragment) -> Unit
    ): AbstractGraphVisitor =
        AbstractGraphVisitor(nodeListener, fragmentListener)
  }


  class Builder {

    var nodeListener: (Adapter) -> Unit = empty()

    var fragmentListener: GraphVisitor.(Fragment) -> Unit =
        { it.prototype.graphQlInstance.accept(this) }

    var exitNodeListener: (Adapter) -> Unit = empty()
  }

}

