package extensions

import org.kotlinq.dsl.Node
import org.kotlinq.dsl.fields.FreeProperty
import org.kotlinq.dsl.fields.LeafBinding
import kotlin.reflect.KFunction0


interface NullabilityOperatorScope {
  // scalars TODO might need to return from [LeafBinding] to allow for scalar lists/arrayss
  operator fun KFunction0<LeafBinding>.not()
  operator fun KFunction0<LeafBinding>.unaryMinus()
  // nodes TODO should it only be on freeprops?
  //operator fun String.not(): Node
  //operator fun String.unaryMinus(): Node
  //free property invokations
  operator fun FreeProperty.not(): Node
  operator fun FreeProperty.unaryMinus(): Node
}