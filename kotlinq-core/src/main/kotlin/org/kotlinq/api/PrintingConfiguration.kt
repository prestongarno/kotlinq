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
    val quotationCharacter: String = "\"",
    val metaPropertyStrategy: MetaPropertyStrategy = MetaPropertyStrategy.NONE,
    val pretty: Boolean) {

  /**
   * Adds properties to fragments based on predicates
   */
  class MetaPropertyStrategy private constructor(
      internal val extraProperties: Map<String, (Fragment) -> Boolean>) {


    class Builder {

      private
      val shouldInclude = mutableMapOf<String, (Fragment) -> Boolean>()

      fun include(propertyName: String, predicate: (Fragment) -> Boolean) =
          apply { shouldInclude[propertyName] = predicate }

      fun includeId(predicate: (Fragment) -> Boolean = { true }) = include("id", predicate)
      fun includeTypename(predicate: (Fragment) -> Boolean = { true }) = include("__typename", predicate)

      fun build() = MetaPropertyStrategy(shouldInclude)
    }

    companion object {

      fun builder() = Builder()

      val NONE = MetaPropertyStrategy(emptyMap())

      /** Adds "__typename" and "id" fields to every fragment */
      val STANDARD = MetaPropertyStrategy.Builder()
          .includeId()
          .includeTypename()
          .build()
    }
  }


  class Builder {
    private var quotationCharacter: String = "\""
    private var metaPropertyStrategy: MetaPropertyStrategy = MetaPropertyStrategy.NONE
    private var pretty: Boolean = false

    fun quotationCharacter(it: String) =
        apply { quotationCharacter = it }

    fun metaPropertyStrategy(it: MetaPropertyStrategy) =
        apply { metaPropertyStrategy = it }

    fun pretty(it: Boolean = false) =
        apply { pretty = it }

    fun build() = PrintingConfiguration(
        quotationCharacter,
        metaPropertyStrategy,
        pretty)
  }


  companion object {

    fun builder() = PrintingConfiguration.Builder()

    /**
     * Double quotations (escaped), includes __typename and ID on all objects
     */
    val DEFAULT: PrintingConfiguration = builder()
        .metaPropertyStrategy(MetaPropertyStrategy.STANDARD)
        .pretty(false)
        .quotationCharacter("\\\"")
        .build()
  }
}

