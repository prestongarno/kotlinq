package org.kotlinq.resolvers

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

  override fun visitModel(name: String, target: ModelAdapter): Unit =
      throw UnsupportedOperationException()

  override fun visitFragment(name: String, target: FragmentAdapter): Unit =
      throw UnsupportedOperationException()

  override fun visitScalar(name: String, target: ParsingAdapter): Unit =
      throw UnsupportedOperationException()

  override fun visitDeserializer(name: String, target: DeserializingAdapter): Unit =
      throw UnsupportedOperationException()

  private
  class InstanceResolver(private val context: Context, private val values: Map<String, Any?>) : Resolver {

    val stack = LinkedList(listOf(values))

    fun push(values: Map<String, Any?>) = stack.addFirst(values)
    fun pop() { stack.removeFirst() }

    fun resolveFromRoot(): Boolean = resolve(values, context)

    override fun resolve(value: Map<String, Any?>, target: Context): Boolean {
      push(value)
      target.graphQlInstance.properties.forEach { _, adapter -> adapter.accept(this) }
      pop()
      return target.graphQlInstance.isResolved()
    }

    override fun visitModel(name: String, target: ModelAdapter) {
      @Suppress("UNCHECKED_CAST")
      (stack.peek()[name] as? Map<String, Any?>)?.let {
        stack.push(it)
        target.setValue(it, this)
      }
    }

    override fun visitFragment(name: String, target: FragmentAdapter) {
      @Suppress("UNCHECKED_CAST")
      (stack.peek()[name] as? Map<String, Any?>)?.let { values ->
        stack.push(values)
        values["__typename"]?.toString()?.let { type ->
          target.setValue(type, values, this)
        }
      }
    }

    override fun visitScalar(name: String, target: ParsingAdapter) {
      stack.peek()[name]?.toString()?.let(target::setValue)
    }

    override fun visitDeserializer(name: String, target: DeserializingAdapter) {
      (stack.peek()[name]?.let {
        it as? InputStream ?: it.toString().byteInputStream()
      } ?: "".byteInputStream()).let(target::setValue)
    }
  }

}

