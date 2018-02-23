package org.kotlinq.printer

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentContext
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.Printer

internal
fun Printer.visit(fragment: Fragment) =
    PluginPrinter(this, fragment).toString()


internal
class PluginPrinter(val printer: Printer, val fragment: Fragment) : GraphVisitor {

  val config = printer.printingConfiguration
  val metaPropertyStrategy = printer.metaStrategy

  val stringer = StringBuilder()
  private fun String.emit() = stringer.append(this)

  init {
    fragment.traverse(this)
  }

  private val string by lazy(stringer::toString)
  override fun toString(): String = string

  override fun notifyEnter(fragment: Fragment, inline: Boolean): Boolean {
    if (inline) {
      config.spreadOnFragmentOperator.emit()
      printer.typeNameEval(fragment.typeName).emit()
    }
    printer.enterContextEval().emit()
    return true // fragment extraction not supported yet
  }

  override fun visitContext(fragment: Fragment) {
    for ((propertyName, predicate) in metaPropertyStrategy.extraProperties) {
      if (predicate(fragment)) {
        printer.fieldNameEval(propertyName).emit()
      }
    }
    fragment.graphQlInstance.accept(this)
  }

  override fun notifyExit(fragment: Fragment) {
    printer.exitContextEval().emit()
  }

  override fun enterField(adapter: Adapter): Boolean {
    printer.fieldNameEval(
        adapter.propertyInfo.graphQlName).emit()

    var index = 0
    val size = adapter.propertyInfo.arguments.size

    if (size > 0) "(".emit() else return true

    for ((name, argument) in adapter.propertyInfo.arguments) {
      printer.argumentNameEval(name).emit()
      config.argumentSeparator.emit()
      printer.argumentValueEval(argument).emit()
      if (index++ < size) config.commaSeparator.emit()
    }

    ")".emit()

    return true
  }

  override fun visitFragmentContext(context: FragmentContext) {
    printer.enterContextEval().emit()
    super.visitFragmentContext(context)
    printer.exitContextEval().emit()
  }
}
