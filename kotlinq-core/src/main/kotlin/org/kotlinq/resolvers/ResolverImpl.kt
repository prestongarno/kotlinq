package org.kotlinq.resolvers

import org.kotlinq.api.Adapter
import org.kotlinq.api.Context
import org.kotlinq.api.DeserializingAdapter
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.ModelAdapter
import org.kotlinq.api.ParsingAdapter
import org.kotlinq.api.Resolver
import java.io.InputStream
import java.util.*

internal
class ResolverImpl : Resolver {

  override fun resolve(value: Map<String, Any?>, target: Context): Boolean =
      InstanceResolver(target, value).resolveFromRoot()

  override fun visitModel(target: ModelAdapter) = Unit

  override fun visitFragmentContext(target: FragmentAdapter) = Unit

  override fun visitScalar(target: ParsingAdapter) = Unit

  override fun visitDeserializer(target: DeserializingAdapter) = Unit

  /**
   * Stack-based resolver algorithm
   */
  private
  class InstanceResolver(private val context: Context, private val values: Map<String, Any?>) : Resolver {

    val stack = LinkedList<Map<String, Any?>>()

    fun push(values: Map<String, Any?>) = stack.addFirst(values)
    fun pop() {
      stack.removeFirst()
    }

    fun resolveFromRoot(): Boolean = resolve(values, context)

    override fun resolve(value: Map<String, Any?>, target: Context): Boolean {
      push(value)
      target.graphQlInstance.accept(this)
      pop()
      return target.graphQlInstance.isResolved()
    }

    override fun visitModel(target: ModelAdapter) {
      stack.peek().jsonObjectNamed(target)?.let {
        push(it)
        // TODO add Transformer<T> interface for not only list properties, but also so that
        // visitors can have control over setting values & verifying result
        target.setValue(it, this)
        pop()
      }
    }

    override fun visitFragmentContext(target: FragmentAdapter) {

      stack.peek().jsonObjectNamed(target)?.let { values ->
        push(values)
        values["__typename"]?.toString()?.let { target.setValue(it, values, this) }
        pop()
      }
    }

    override fun visitScalar(target: ParsingAdapter) {
      target.setValue(stack.peek()[target.propertyInfo.graphQlName]?.toString())
    }

    override fun visitDeserializer(target: DeserializingAdapter) {
      (stack.peek()[target.propertyInfo.graphQlName]?.let {
        it as? InputStream ?: it.toString().byteInputStream()
      } ?: "".byteInputStream()).let(target::setValue)
    }
  }

}

@Suppress("UNCHECKED_CAST")
fun Map<String, Any?>.jsonObjectNamed(adapter: Adapter): Map<String, Any>? {
  return this[adapter.propertyInfo.graphQlName] as? Map<String, Any>
}

