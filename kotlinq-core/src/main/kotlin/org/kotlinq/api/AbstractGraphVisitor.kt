package org.kotlinq.api

import org.kotlinq.common.empty


/**
 * Abstraction on top of [GraphVisitor] interface,
 * reduces the graph visitor to listen for only "Nodes" and "Edges"
 *
 * [GraphVisitor] is still needed in order to resolve the
 * graph with proper values from deserialization, so this might be called 'backwards abstraction'
 *
 * @param controller implementations can call
 *   [Fragment.prototype].graphQlInstance.accept(this) withing the block to continue to all adjacent nodes.
 *   This allows for path control when traversing
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
      controller(target.fragment) // instead of visiting fragment def, pass control to callback
      exitNodeListener(target)
    }

    override fun visitFragmentContext(target: FragmentAdapter) {
      nodeListener(target)
      target.fragments.values.forEach(::visitFragment)
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
        controller: GraphVisitor.(Fragment) -> Unit = { it.prototype.graphQlInstance.accept(this) },
        exitNodeListener: (Adapter) -> Unit = empty()
    ) = AbstractGraphVisitor(nodeListener, controller, exitNodeListener)
  }


  class Builder {

    var nodeListener: (Adapter) -> Unit = empty()

    var fragmentListener: GraphVisitor.(Fragment) -> Unit =
        { it.prototype.graphQlInstance.accept(this) }

    var exitNodeListener: (Adapter) -> Unit = empty()
  }

}

