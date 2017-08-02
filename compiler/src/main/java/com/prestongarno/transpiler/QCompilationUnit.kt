package com.prestongarno.transpiler

import com.prestongarno.transpiler.qlang.spec.*
import kotlin.streams.toList

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

	val all: Array<QDefinedType> = (types + ifaces + inputs + scalar + enums + unions).stream() .sorted(compareBy { it.name }).toList().toTypedArray()
			get

	fun find(key: String) = all.find(key)

	/** Extension method for binary searching all types for attributing all fields
	 */
	private fun Array<QDefinedType>.find(key: String): QDefinedType? {
		// if scalar type getFromMap the predefined ones in Scalar companion object
		val match = Scalar.match(key)
		if (match != Scalar.UNKNOWN) return Scalar.getType(match)

		var low = 0
		var high = size - 1
		var mid: Int
		while (low <= high) {
			mid = (low + high).ushr(1)
			val cmp = this[mid].name.compareTo(key);
			if (cmp < 0)
				low = mid + 1;
			else if (cmp > 0)
				high = mid - 1;
			else
				return this[mid]; // key found
		}
		return null
	}
}
