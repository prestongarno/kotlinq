package org.kotlinq.printer

import org.kotlinq.api.Fragment
import org.kotlinq.api.PrintingConfiguration

class Printer private constructor(
    val printingConfiguration: PrintingConfiguration,
    internal val argumentNameEval: (String) -> String,
    internal val argumentValueEval: (Any) -> String,
    internal val enterContextEval: () -> String,
    internal val exitContextEval: () -> String,
    internal val fieldNameEval: (String) -> String) {

  fun printFragmentToString(fragment: Fragment) = visit(fragment)


  class Builder(val configuration: PrintingConfiguration) {
    private var argumentNameEval: (String) -> String = { it }
    private var argumentValueEval: (Any) -> String = {
      configuration.quotationCharacter + it.toString() + configuration.quotationCharacter
    }
    private var enterContextEval: () -> String = ::ENTER_SCOPE
    private var exitContextEval: () -> String = ::EXIT_SCOPE
    private var fieldNameEval: (String) -> String = { it }

    fun evalArgumentName(function: (String) -> String) =
        apply { this.argumentNameEval = function }

    fun evalArgumentValue(function: (Any) -> String) =
        apply { this.argumentValueEval = function }

    fun evalEnterContext(function: () -> String) =
        apply { this.enterContextEval = function }

    fun evalExitContext(function: () -> String) =
        apply { this.exitContextEval = function }

    fun evalFieldName(function: (String) -> String) =
        apply { this.fieldNameEval = function }

    fun build() = Printer(
        configuration,
        argumentNameEval,
        argumentValueEval,
        enterContextEval,
        exitContextEval,
        fieldNameEval)
  }


  companion object {

    private const val ENTER_SCOPE: String = "{"
    private const val EXIT_SCOPE: String = "}"


    fun fromConfiguration(printingConfiguration: PrintingConfiguration): Printer {
      return printingConfiguration.toStrategy()
    }

    fun transformationBuilder(printingConfiguration: PrintingConfiguration): Printer.Builder =
        Builder(printingConfiguration)


    private fun PrintingConfiguration.toStrategy(): Printer =

        if (pretty)
          Builder(this).apply {
            var currentDepth = 0
            val indent = "  "
            this.evalEnterContext {
              "$ENTER_SCOPE\n" + indent.repeat(++currentDepth)
            }.evalExitContext {
              "$EXIT_SCOPE\n" + indent.repeat(--currentDepth)
            }
          }.build()

        else Builder(this).build()
  }
}


private
fun formatArg(value: Any, quotationMark: String): String =
    when (value) {
      is Int, is Boolean -> "$value"
      is Float -> "${value}f"
      is String -> value.wrap(quotationMark)
      is Enum<*> -> value.name.wrap(quotationMark)
      is Iterable<*> -> value
          .map { formatArg(it ?: "", quotationMark) }
          .filter { it.isNotBlank() }
          .joinToString(",", "[ ", " ]")
      else -> value.toString().wrap(quotationMark)
    }

private fun String.wrap(value: String): String = value + String + value
