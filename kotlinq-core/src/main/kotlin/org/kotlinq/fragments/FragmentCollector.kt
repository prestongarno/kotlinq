package org.kotlinq.fragments

import org.kotlinq.api.Fragment
import org.kotlinq.api.GraphVisitor


fun Fragment.getFragments(uniqueOnly: Boolean = true): Set<Fragment> {
  val fragments = mutableSetOf<Fragment>()

  GraphVisitor.builder()
      .onNotifyEnter { current ->

        if (uniqueOnly && current in fragments)
          false
        else let {
          fragments += current
          true
        }

      }.build()
      .also(::traverse)

  return fragments
}
