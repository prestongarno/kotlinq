package org.kotlinq.graph

import org.junit.Ignore
import org.junit.Test
import org.kotlinq.api.AbstractGraphVisitor
import org.kotlinq.api.Fragment
import org.kotlinq.eq

class FragmentCollectorTests {

  @Suppress("UNREACHABLE_CODE")
  @Ignore
  @Test fun twoIdenticalFragmentsOnGraph() {

    val graph = TODO()

    val frags = mutableSetOf<Fragment>()

    // ~10 LOC easy doing something this difficult, all it takes is eq & hashcode correctly
    AbstractGraphVisitor.createGeneralizedGraphVisitor {

      fragmentListener = {
        if (!frags.contains(it)) {
          frags += it
          it.graphQlInstance.accept(this)
        }
      }

    }.traverse(graph)

    frags.size eq 2
  }

}

