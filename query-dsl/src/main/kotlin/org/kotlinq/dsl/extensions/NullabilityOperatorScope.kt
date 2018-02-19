package org.kotlinq.dsl.extensions

import org.kotlinq.dsl.Node
import org.kotlinq.dsl.fields.FreeProperty
import LeafGetter


interface NullabilityOperatorScope {
  // scalars TODO might need to return from [LeafBinding] to allow for scalar lists/arrayss
  operator fun LeafGetter.not()
  // nodes TODO should it only be on freeprops?
  operator fun String.not(): FreeProperty = FreeProperty(this)
  //free property invokations
  operator fun FreeProperty.not(): FreeProperty
}