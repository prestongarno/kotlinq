package org.kotlinq.printer

import org.kotlinq.api.Adapter
import org.kotlinq.api.DeserializingAdapter
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.InstanceAdapter
import org.kotlinq.api.ParsingAdapter
import org.kotlinq.common.stringify
import org.kotlinq.common.unit

private const val SPREAD: String = "..."
private const val ENTER_SCOPE: String = "{"
private const val EXIT_SCOPE: String = "}"


class VisitingPrinter(private val instance: GraphQlInstance) {


  override fun toString(): String {
    return StringBuildingPrinter(instance).print()
  }

  private
  class StringBuildingPrinter(instance: GraphQlInstance) : GraphVisitor {

    private val stringBuilder: StringBuilder = StringBuilder()

    private
    val toString by lazy { printInstance(instance) }

    fun print(): String = toString

    private
    fun printInstance(instance: GraphQlInstance): String {
      val properties = instance.properties.values.toList()

      stringBuilder.apply {
        append(ENTER_SCOPE)
        append("__typename,") // TODO config printer with builder pattern & strategy
        properties.take(properties.size - 1).forEach {
          it.accept(this@StringBuildingPrinter)
          append(",")
        }
        properties.last().accept(this@StringBuildingPrinter)
        append(EXIT_SCOPE)
      }
      return stringBuilder.toString()
    }

    private fun printField(adapter: Adapter) =
        adapter.propertyInfo.unit {
          stringBuilder.append(graphQlName)
          stringBuilder.append(arguments.stringify())
        }

    override fun visit(target: InstanceAdapter) {
      printField(target)
      printInstance(target.fragment.graphQlInstance)
    }

    override fun visit(target: FragmentAdapter) {
      printField(target)
      stringBuilder.apply {
        append(ENTER_SCOPE)
        target.fragments.forEach { (type, fragment) ->
          append(SPREAD)
          append(" on ")
          append(type)
          printInstance(fragment.graphQlInstance)
        }
        append(EXIT_SCOPE)
      }
    }

    override fun visit(target: ParsingAdapter) =
        printField(target)

    override fun visit(target: DeserializingAdapter) =
        printField(target)
  }
}