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
class PrintingConfiguration private constructor(
    val pretty: Boolean = false,
    val quotationCharacter: String = "\"",
    val argumentSeparator: String = ": ",
    val commaSeparator: String = ",",
    val spreadOnFragmentOperator: String = "... on ",
    val lcurlyChar: Char = '{',
    val rcurlyChar: Char = '}') {

  /**
   * Builder for a [PrintingConfiguration].
   */
  class Builder {


    // TODO dedup
    private var quotationCharacter: String = "\""
    private var pretty: Boolean = false
    private var argumentSeparator: String = ": "
    private var spreadOnFragmentOperator: String = "... on "
    private var commaSeparator: String = ","
    private var indent: String = "  "
    private var lcurlyChar: Char = '{'
    private var rcurlyChar: Char = '}'


    fun quotationCharacter(it: String) = apply { quotationCharacter = it }
    fun pretty(it: Boolean = false) = apply { pretty = it }
    fun argumentSeparator(it: String) = apply { argumentSeparator = it }
    fun spreadOnFragmentOperator(it: String) = apply { spreadOnFragmentOperator = it }
    fun commaSeparator(it: String) = apply { commaSeparator = it }
    fun indent(it: String) = apply { indent = it }
    fun lcurlyChar(it: Char) = apply { lcurlyChar = it }
    fun rcurlyChar(it: Char) = apply { rcurlyChar = it }


    fun build() = PrintingConfiguration(
        pretty, quotationCharacter,
        argumentSeparator, commaSeparator,
        spreadOnFragmentOperator)

  }

  companion object {


    fun builder() = PrintingConfiguration.Builder()

    /**
     * Double quotations (escaped), includes __typename and ID on all objects
     */
    val DEFAULT: PrintingConfiguration = builder()
        .pretty(false)
        .quotationCharacter("\\\"")
        .argumentSeparator(": ")
        .commaSeparator(",")
        .spreadOnFragmentOperator("...on ")
        .build()

    val PRETTY = builder()
        .pretty(true)
        .quotationCharacter("\\\"")
        .argumentSeparator(": ")
        .commaSeparator("")
        .spreadOnFragmentOperator("... on ")
        .build()
  }
}

