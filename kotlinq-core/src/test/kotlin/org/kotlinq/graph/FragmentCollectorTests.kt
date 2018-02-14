package org.kotlinq.graph

import org.junit.Test
import org.kotlinq.GraphBuilder
import org.kotlinq.api.AbstractGraphVisitor
import org.kotlinq.api.Fragment
import org.kotlinq.createGraph
import org.kotlinq.eq

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

      "foo" ofType "Any" definedAs {
        "query1" ofType "Query" spread fragment
      }
    }.graphQlInstance

    val frags = mutableSetOf<Fragment>()

    // ~10 LOC easy doing something this difficult, all it takes is eq & hashcode correctly
    AbstractGraphVisitor.createGeneralizedGraphVisitor {

      fragmentListener = {
        if (!frags.contains(it)) {
          frags += it
          it.prototype.graphQlInstance.accept(this)
        }
      }

    }.traverse(graph)

    frags.size eq 2
  }
}

