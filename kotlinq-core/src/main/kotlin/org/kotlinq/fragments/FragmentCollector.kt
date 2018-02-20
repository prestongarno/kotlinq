package org.kotlinq.fragments

import org.kotlinq.api.AbstractGraphVisitor
import org.kotlinq.api.Fragment
import org.kotlinq.api.GraphQlInstance


fun GraphQlInstance.getFragments(uniqueOnly: Boolean = true): Set<Fragment> {

  val frags = mutableSetOf<Fragment>()

  // ~10 LOC easy doing something this difficult, all it takes is eq & hashcode correctly
  AbstractGraphVisitor.createGeneralizedGraphVisitor {
    fragmentListener = {
      if (uniqueOnly && !frags.contains(it)) {
        frags += it
        it.graphQlInstance.accept(this)
      } else {
        frags += it
      }
    }
  }.traverse(this)

  return frags
}
