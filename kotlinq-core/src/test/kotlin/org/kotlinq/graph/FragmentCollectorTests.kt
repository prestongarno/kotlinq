package org.kotlinq.graph

import org.junit.Test
import org.kotlinq.GraphBuilder
import org.kotlinq.createGraph
import org.kotlinq.fragments.getFragments
import org.kotlinq.println

class FragmentCollectorTests {

  @Test fun twoIdenticalFragmentsOnGraph() {

    val fragment: GraphBuilder.FragmentBuilder.() -> Unit = {
      "Type1" fragmentDef {
        scalar("name")
        scalar("id")
      }
    }


    val graph = createGraph {
      "query" ofType "Query" spread fragment
      "query2" ofType "Query" spread fragment
    }.graphQlInstance

    require(graph.getFragments().size == 1) // failing
  }
}