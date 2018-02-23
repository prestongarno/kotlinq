package org.kotlinq.printer

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.GraphVisitor

internal
fun Printer.visit(fragment: Fragment) =
  PluginPrinter(this, fragment).toString()


internal
class PluginPrinter(val printer: Printer, val fragment: Fragment) : GraphVisitor {

  val config = printer.printingConfiguration
  val metaPropertyStrategy = config.metaPropertyStrategy

  val stringer = StringBuilder()
  private fun String.emit() = stringer.append(this)

  init {
    notifyEnter(fragment, inline = false)
  }

  private val string by lazy(stringer::toString)
  override fun toString(): String = string

  override fun notifyEnter(fragment: Fragment, inline: Boolean): Boolean {
    if (inline) {
      "... on ".emit()
      fragment.typeName.emit()
      " ".emit()
    }
    printer.enterContextEval().emit()
    return true // fragment extraction not supported yet
  }

  override fun visitContext(fragment: Fragment) {
    for ((propertyName, predicate) in metaPropertyStrategy.extraProperties) {
      if (predicate(fragment)) {
        propertyName.emit()
        ",".emit() //TODO make sure that empty fragments aren't possible
      }
    }
  }

  override fun notifyExit(fragment: Fragment) {
    printer.exitContextEval().emit()
  }

  override fun enterField(adapter: Adapter): Boolean {
    adapter.propertyInfo.graphQlName.emit()
    for ((name, argument) in adapter.propertyInfo.arguments) {
      printer.argumentNameEval(name).emit()
      ": ".emit()
      printer.argumentValueEval(argument).emit()
    }
    return true
  }
}
