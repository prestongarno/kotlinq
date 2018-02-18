package org.kotlinq.api


interface GraphVisitor {

  fun visit(adapter: Adapter): Unit = adapter.accept(this)

  fun visit(target: DeserializingAdapter) = Unit

  fun visit(target: Fragment) = Unit

  fun visit(target: FragmentAdapter) = Unit

  fun visit(target: ModelAdapter) = Unit

  fun visit(target: ParsingAdapter) = Unit

  fun traverse(graph: GraphQlInstance): Unit = graph.accept(this)

}