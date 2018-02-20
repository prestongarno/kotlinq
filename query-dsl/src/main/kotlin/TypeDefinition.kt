package org.kotlinq.dsl

import org.kotlinq.api.Definition


data class TypeDefinition internal constructor(
    val typeName: String,
    internal val definition: () -> Definition) {

  companion object {
    internal fun fromBuilder(typeName: String, block: SelectionSet): TypeDefinition =
        TypeDefinition(typeName, block.asInitializer(typeName))
  }
}
