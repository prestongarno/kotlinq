package org.kotlinq.dsl

import org.kotlinq.dsl.extensions.FreePropertyExtensionScope
import org.kotlinq.dsl.extensions.NullabilityOperatorScope
import org.kotlinq.dsl.extensions.StringExtensionScope

/**
 * TODO different query operations documentation
 */
interface DslExtensionScope : NullabilityOperatorScope, FreePropertyExtensionScope, StringExtensionScope {
  operator fun ScalarSymbol.not() = this to true
}

