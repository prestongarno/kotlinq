package extensions

import org.kotlinq.dsl.Node
import org.kotlinq.dsl.TypeBuilder
import org.kotlinq.dsl.fields.LeafBinding
import kotlin.reflect.KFunction0


interface NullabilityOperatorScope {
  // scalars TODO might need to return from [LeafBinding] to allow for scalar lists/arrayss
  operator fun KFunction0<LeafBinding>.not()
  operator fun KFunction0<LeafBinding>.unaryMinus()
  // nodes
  operator fun String.not(): Node
  operator fun String.unaryMinus(): Node
  // list of nodes
  infix fun Node.listOf(block: TypeBuilder.() -> Unit)
}