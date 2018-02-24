package org.kotlinq.api

import org.kotlinq.printer.visit


class Printer private constructor(
    val printingConfiguration: PrintingConfiguration,
    internal val metaStrategy: MetaStrategy,
    internal val argumentNameEval: (String) -> String,
    internal val argumentValueEval: (Any) -> String,
    internal val enterContextEval: () -> String,
    internal val exitContextEval: () -> String,
    internal val fieldNameEval: (String) -> String,
    internal val typeNameEval: (String) -> String) {

  fun printFragmentToString(fragment: Fragment) = visit(fragment)

  fun toBuilder() = Builder(printingConfiguration)
      .metaStrategy(metaStrategy)
      .evalArgumentName(argumentNameEval)
      .evalArgumentValue(argumentValueEval)
      .evalEnterContext(enterContextEval)
      .evalExitContext(exitContextEval)
      .evalFieldName(fieldNameEval)
      .evalTypeName(typeNameEval)


  class Builder internal constructor(val configuration: PrintingConfiguration) {

    private var argumentNameEval: (String) -> String = { it }
    private var argumentValueEval: (Any) -> String = {
      configuration.quotationCharacter + it.toString() + configuration.quotationCharacter
    }
    private var enterContextEval: () -> String = { "${configuration.lcurlyChar}" }
    private var exitContextEval: () -> String = { "${configuration.rcurlyChar}" }
    private var fieldNameEval: (String) -> String = { it }
    private var typeNameEval: (String) -> String = { it }
    private var metaStrategy: Printer.MetaStrategy = MetaStrategy.STANDARD

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

    fun metaStrategy(it: Printer.MetaStrategy) =
        apply { metaStrategy = it }

    fun build() = Printer(
        printingConfiguration = configuration,
        metaStrategy = metaStrategy,
        argumentNameEval = argumentNameEval,
        argumentValueEval = argumentValueEval,
        enterContextEval = enterContextEval,
        exitContextEval = exitContextEval,
        fieldNameEval = fieldNameEval,
        typeNameEval = typeNameEval)
  }


  companion object {

    private const val ENTER_SCOPE: String = "{"
    private const val EXIT_SCOPE: String = "}"


    fun fromConfiguration(printingConfiguration: PrintingConfiguration): Printer =

        if (printingConfiguration.pretty)

          Builder(printingConfiguration).apply {
            var currentDepth = 0
            val indent = "  "
            evalEnterContext {
              if (configuration.pretty && currentDepth++ > 0)
                " " + printingConfiguration.lcurlyChar
              else
                printingConfiguration.lcurlyChar.toString()
            }
            evalExitContext {
              "\n" + indent.repeat(--currentDepth) + EXIT_SCOPE
            }
            evalFieldName { "\n" + indent.repeat(currentDepth) + it }
          }.build()

        else
          Builder(printingConfiguration).apply {

            var firstProperty = false

            evalEnterContext {
              firstProperty = true
              ENTER_SCOPE
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