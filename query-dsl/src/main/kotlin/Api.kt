package org.kotlinq.dsl

import org.kotlinq.api.Fragment

/**
 * Top-level query.
 *
 * @param name the name of the operation. This does nothing
 * @param selectionSet the query selectionSet
 *
 * @author prestongarno
 * @since 0.4.0
 */
fun query(name: String = "Query", selectionSet: SelectionSet): Fragment =
    GraphBuilder(selectionSet).build(name)


/**
 * Creates a named type definition
 *
 * @author prestongarno
 * @since 0.4.0
 */
fun fragment(typeName: String, block: SelectionSet) = query(typeName, block)

