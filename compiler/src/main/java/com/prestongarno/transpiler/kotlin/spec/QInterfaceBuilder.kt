package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.transpiler.qlang.spec.*
import com.squareup.kotlinpoet.*

/**
 * Creates interfaces for GraphQL Interfaces.
 * Thank God/the Kotlin team for abstract properties and delegates
 *
 * However, there is a problem with a query in a schema which is of an interface type.
 * I think the easiest way to do this is to make all interfaces as classes and
 * forget about inheritance/delegation. In order to get around this you'd have to
 * basically implement a DSL Reflection system within the JVM - not trivial
 *
 * Essentially if you're relying on interface definitions + introspection
 * the schema probably is the problem
 */
class QInterfaceBuilder(val packageName: String) {
	fun createType(qIface: QInterfaceDef, packageName: String = "com.prestongarno.ktq"): Nothing = TODO("Don't worry about this class")
}
