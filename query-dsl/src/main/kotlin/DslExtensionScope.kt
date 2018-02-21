package org.kotlinq.dsl

import org.kotlinq.dsl.extensions.FreePropertyExtensionScope
import org.kotlinq.dsl.extensions.ListDeclarationExtensionScope
import org.kotlinq.dsl.extensions.NullabilityOperatorScope
import org.kotlinq.dsl.extensions.StringExtensionScope

@GraphQlDslObject
interface DslExtensionScope :
    NullabilityOperatorScope,
    FreePropertyExtensionScope,
    StringExtensionScope,
    ListDeclarationExtensionScope {

  operator fun ScalarSymbol.not() = this to true
}

