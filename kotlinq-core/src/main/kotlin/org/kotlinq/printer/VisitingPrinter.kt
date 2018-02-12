package org.kotlinq.printer

import org.kotlinq.api.Adapter
import org.kotlinq.api.DeserializingAdapter
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.ModelAdapter
import org.kotlinq.api.ParsingAdapter
import org.kotlinq.common.stringify

private const val SPREAD: String = "..."
private const val ENTER_SCOPE: String = "{"
private const val EXIT_SCOPE: String = "}"


class VisitingPrinter(private val instance: GraphQlInstance) : GraphVisitor {


  private var stringBuilder: StringBuilder = StringBuilder()

  override fun toString(): String {
    return printInstance(instance)
  }


  private
  fun printInstance(instance: GraphQlInstance): String {
    val properties = instance.properties.values.toList()

    stringBuilder.apply {
      append(ENTER_SCOPE)
      properties.take(properties.size - 1).forEach {
        it.accept(this@VisitingPrinter)
        append(",")
      }
      properties.last().accept(this@VisitingPrinter)
      append(EXIT_SCOPE)
    }
    val result = stringBuilder.toString()
    stringBuilder = StringBuilder()
    return result
  }

  private fun printAdapter(adapter: Adapter) {
    stringBuilder.append(adapter.name)
    stringBuilder.append(adapter.arguments.stringify())
  }

  override fun visitModel(target: ModelAdapter) {
    printAdapter(target)
    printInstance(target.prototype.graphQlInstance)
  }

  override fun visitFragment(target: FragmentAdapter) {
    printAdapter(target)
    stringBuilder.apply {
      append(ENTER_SCOPE)
      target.fragments.forEach { (type, fragment) ->
        append(SPREAD)
        append(" on ")
        append(type)
        printInstance(fragment.prototype.graphQlInstance)
      }
      append(EXIT_SCOPE)
    }
  }

  override fun visitScalar(target: ParsingAdapter) =
      printAdapter(target)

  override fun visitDeserializer(target: DeserializingAdapter) =
      printAdapter(target)
}