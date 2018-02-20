package org.kotlinq.dsl

import org.kotlinq.api.Definition

/**
 * Top-level query.
 *
 * @param name the name of the operation. This does nothing
 * @param definition the query definition
 *
 * @author prestongarno
 * @since 0.4.0
 */
fun query(name: String = "Query", definition: TypeBuilder.() -> Unit): Definition =
    GraphBuilder(name, definition).build()


/**
 * Creates a named type definition
 *
 * @author prestongarno
 * @since 0.4.0
 */
fun defineType(typeName: String, block: SelectionSet) =
    TypeDefinition.fromBuilder(typeName, block)

