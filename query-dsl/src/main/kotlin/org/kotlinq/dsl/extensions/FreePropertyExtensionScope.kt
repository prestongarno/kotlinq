package org.kotlinq.dsl.extensions

import org.kotlinq.dsl.FragmentScopeBuilder
import org.kotlinq.dsl.TypeBuilder
import org.kotlinq.dsl.fields.FreeProperty


interface FreePropertyExtensionScope {
  operator fun FreeProperty.invoke(block: TypeBuilder.() -> Unit)
  operator fun FreeProperty.rangeTo(block: FragmentScopeBuilder.() -> Unit)
}