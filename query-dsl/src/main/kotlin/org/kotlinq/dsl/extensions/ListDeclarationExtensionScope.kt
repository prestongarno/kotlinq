package org.kotlinq.dsl.extensions

import org.kotlinq.api.Fragment
import org.kotlinq.dsl.FragmentSelection
import org.kotlinq.dsl.ScalarSymbol
import org.kotlinq.dsl.fields.FreeProperty


interface ListDeclarationExtensionScope {

  infix fun String.listOf(typeSymbol: ScalarSymbol)

  infix fun FreeProperty.listOf(definition: Fragment)

  fun listOf(definitions: FragmentSelection): FragmentSelection = {
    this.apply(definitions)
    this.flagField(isCollection = true)
  }
}