package org.kotlinq.api


interface GraphVisitor {

  fun visit(target: DeserializingAdapter) = Unit

  fun visit(target: Fragment) = Unit

  fun visit(target: FragmentAdapter) = Unit

  fun visit(target: InstanceAdapter) = Unit

  fun visit(target: ParsingAdapter) = Unit

  fun traverse(graph: GraphQlInstance): Unit = graph.accept(this)

}