package org.kotlinq.dsl

import TypeBuilderBlock
import asInitializer
import org.kotlinq.api.Context


data class TypeDefinition internal constructor(
    val typeName: String,
    internal val contextDefinition: () -> Context) {

  companion object {
    internal fun fromBuilder(typeName: String, block: TypeBuilderBlock): TypeDefinition =
        TypeDefinition(typeName, block.asInitializer(typeName))
  }
}
