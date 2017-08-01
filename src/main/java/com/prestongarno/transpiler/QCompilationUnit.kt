package com.prestongarno.transpiler

import com.prestongarno.transpiler.qlang.spec.*

/**
 * Contains instance variables of Lists of different kinds of GraphQL schema declarations, each one with a nested
 * list of Strings for which each entry represents a declaration within a type declaration (primitive value, nested
 * type, or nested list of types
 */
class QCompilationUnit(val types: List<QTypeDef>,
                       val ifaces: List<QInterfaceDef>,
                       val inputs: List<QInputType>,
                       val scalar: List<QScalarType>,
                       val enums: List<QEnumDef>,
                       val unions: List<QUnionTypeDef>) {

	fun getAll() : List<QDefinedType> = types + ifaces + inputs + scalar + enums + unions
}
