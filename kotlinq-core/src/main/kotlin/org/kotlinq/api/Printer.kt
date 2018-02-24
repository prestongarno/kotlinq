package org.kotlinq.api

import org.kotlinq.printer.visit


class Printer private constructor(builder: Builder) {

  val configuration = builder.configuration
  internal val metaStrategy = builder.metaStrategy
  internal val argumentNameEval = builder.argumentNameEval
  internal val argumentValueEval = builder.argumentValueEval
  internal val enterContextEval = builder.enterContextEval
  internal val exitContextEval = builder.exitContextEval
  internal val fieldNameEval = builder.fieldNameEval
  internal val typeNameEval = builder.typeNameEval
  internal val inlineFragmentEval = builder.inlineFragmentEval

  fun printFragmentToString(fragment: Fragment) = visit(fragment)

  fun toBuilder() = Builder(configuration)
      .metaStrategy(metaStrategy)
      .evalArgumentName(argumentNameEval)
      .evalArgumentValue(argumentValueEval)
      .evalEnterContext(enterContextEval)
      .evalExitContext(exitContextEval)
      .evalFieldName(fieldNameEval)
      .evalTypeName(typeNameEval)
      .evalInlineFragment(inlineFragmentEval)


  class Builder internal constructor(val configuration: PrintingConfiguration) {

    internal var argumentNameEval: (String) -> String = { it }
    internal var argumentValueEval: (Any) -> String = { formatArg(it, configuration.quotationCharacter) }
    internal var enterContextEval: () -> String = { "${configuration.lcurlyChar}" }
    internal var exitContextEval: () -> String = { "${configuration.rcurlyChar}" }
    internal var fieldNameEval: (String) -> String = { it }
    internal var typeNameEval: (String) -> String = { it }
    internal var inlineFragmentEval: (String) -> String = { configuration.spreadOnFragmentOperator + it }
    internal var metaStrategy: Printer.MetaStrategy = configuration.metaStrategy

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

    fun evalTypeName(function: (String) -> String) =
        apply { this.typeNameEval = function }

    fun evalInlineFragment(function: (String) -> String) =
        apply { this.inlineFragmentEval = function }

    fun metaStrategy(it: Printer.MetaStrategy) =
        apply { metaStrategy = it }

    fun build() = Printer(this)
  }


  companion object {


    fun fromConfiguration(conf: PrintingConfiguration): Printer =

        if (conf.pretty)

          Builder(conf).apply {
            var currentDepth = 0
            val indent = conf.indent
            evalEnterContext {
              if (configuration.pretty && currentDepth++ > 0)
                " " + conf.lcurlyChar
              else
                conf.lcurlyChar.toString()
            }
            evalExitContext {
              "\n" + indent.repeat(--currentDepth) + conf.rcurlyChar
            }
            evalFieldName { "\n" + indent.repeat(currentDepth) + it }

            evalInlineFragment {
              "\n" + indent.repeat(currentDepth) + conf.spreadOnFragmentOperator + it
            }
          }.build()
        else
          Builder(conf).apply {

            var firstProperty = false

            evalEnterContext {
              firstProperty = true
              conf.lcurlyChar.toString()
            }

            evalFieldName {
              if (!firstProperty) {
                configuration.commaSeparator + it
              } else {
                firstProperty = false
                it
              }
            }

          }.build()


    fun transformationBuilder(
        printingConfiguration: PrintingConfiguration = PrintingConfiguration.DEFAULT)
        : Printer.Builder = fromConfiguration(printingConfiguration).toBuilder()


  }


  /**
   * Add properties to fragments based on predicates (__typename, id, etc.)
   */
  class MetaStrategy private constructor(
      internal val extraProperties: Map<String, (Fragment) -> Boolean>) {


    class Builder {

      private
      val shouldInclude = mutableMapOf<String, (Fragment) -> Boolean>()

      fun include(propertyName: String, predicate: (Fragment) -> Boolean = { true }) =
          apply { shouldInclude[propertyName] = predicate }

      fun includeId(predicate: (Fragment) -> Boolean = { true }) = include("id", predicate)
      fun includeTypename(predicate: (Fragment) -> Boolean = { true }) = include("__typename", predicate)

      fun build() = MetaStrategy(shouldInclude)
    }

    companion object {

      fun builder() = Builder()

      val NONE = MetaStrategy(emptyMap())

      /** Adds "__typename" and "id" fields to every fragment */
      val STANDARD = MetaStrategy.Builder()
          .includeId()
          .includeTypename()
          .build()
    }
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

private fun String.wrap(value: String): String = value + this + value
