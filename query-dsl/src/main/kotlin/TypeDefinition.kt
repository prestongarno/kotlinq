package org.kotlinq.dsl

import asInitializer
import org.kotlinq.api.Definition


data class TypeDefinition internal constructor(
    val typeName: String,
    internal val definition: () -> Definition) {

  companion object {
    internal fun fromBuilder(typeName: String, block: TypeBuilderBlock): TypeDefinition =
        TypeDefinition(typeName, block.asInitializer(typeName))
  }
}
