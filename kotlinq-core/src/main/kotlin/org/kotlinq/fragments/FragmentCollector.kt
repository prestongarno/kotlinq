package org.kotlinq.fragments

import org.kotlinq.api.AbstractGraphVisitor
import org.kotlinq.api.Fragment
import org.kotlinq.api.GraphQlInstance


fun GraphQlInstance.getFragments(): Set<Fragment> {

  val frags = mutableSetOf<Fragment>()

  // ~10 LOC easy doing something this difficult, all it takes is eq & hashcode correctly
  AbstractGraphVisitor.createGeneralizedGraphVisitor {

    fragmentListener = {
      if (!frags.contains(it)) {
        frags += it
        it.prototype.graphQlInstance.accept(this)
      }
    }

  }.traverse(this)

  return frags
}
