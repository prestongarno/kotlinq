package org.kotlinq.api


/**
 * Added to support different requirements by endpoints
 *
 * Options supported by both:
 *   * Quotation character
 *   * Escape quotation marks
 * Pretty Print options:
 *   * Indent
 */
class PrintingConfiguration private constructor(builder: Builder) {

  val pretty: Boolean = builder.pretty
  val indent: String = builder.indent
  val quotationCharacter = builder.quotationCharacter
  val argumentSeparator = builder.argumentSeparator
  val commaSeparator = builder.commaSeparator
  val spreadOnFragmentOperator = builder.spreadOnFragmentOperator
  val lcurlyChar = builder.lcurlyChar
  val rcurlyChar = builder.rcurlyChar
  val metaStrategy = builder.metaStrategy

  fun toBuilder() = Builder()
      .pretty(pretty)
      .indent(indent)
      .quotationCharacter(quotationCharacter)
      .argumentSeparator(argumentSeparator)
      .commaSeparator(commaSeparator)
      .spreadOnFragmentOperator(spreadOnFragmentOperator)
      .lcurlyChar(lcurlyChar)
      .rcurlyChar(rcurlyChar)
      .metaStrategy(metaStrategy)

  /**
   * Builder for a [PrintingConfiguration].
   */
  class Builder internal constructor() {


    // TODO dedup
    internal var metaStrategy: Printer.MetaStrategy = Printer.MetaStrategy.STANDARD
    internal var pretty: Boolean = false
    internal var indent: String = "  "
    internal var quotationCharacter: String = "\""
    internal var argumentSeparator: String = ": "
    internal var spreadOnFragmentOperator: String = "... on "
    internal var commaSeparator: String = ","
    internal var lcurlyChar: Char = '{'
    internal var rcurlyChar: Char = '}'


    fun metaStrategy(it: Printer.MetaStrategy) = apply { metaStrategy = it }
    fun quotationCharacter(it: String) = apply { quotationCharacter = it }
    fun pretty(it: Boolean = false) = apply { pretty = it }
    fun argumentSeparator(it: String) = apply { argumentSeparator = it }
    fun spreadOnFragmentOperator(it: String) = apply { spreadOnFragmentOperator = it }
    fun commaSeparator(it: String) = apply { commaSeparator = it }
    fun indent(it: String) = apply { indent = it }
    fun lcurlyChar(it: Char) = apply { lcurlyChar = it }
    fun rcurlyChar(it: Char) = apply { rcurlyChar = it }


    fun build() = PrintingConfiguration(this)

  }

  companion object {


    fun builder() = PrintingConfiguration.Builder()

    /**
     * Double quotations (escaped), includes __typename and ID on all objects
     */
    val DEFAULT: PrintingConfiguration = builder()
        .pretty(false)
        .commaSeparator(",")
        .spreadOnFragmentOperator("...on ")
        .build()

    val PRETTY = builder()
        .pretty(true)
        .commaSeparator("")
        .spreadOnFragmentOperator("... on ")
        .build()
  }
}

