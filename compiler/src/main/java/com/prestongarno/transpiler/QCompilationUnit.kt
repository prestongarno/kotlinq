package com.prestongarno.transpiler

import com.prestongarno.transpiler.qlang.spec.*
import java.util.*

/**
 * Contains instance variables of Lists of different kinds of GraphQL schema declarations, each one with a field
 * list of Strings for which each entry represents a declaration within a type declaration (primitive value, field
 * type, or field list of types
 */
class QCompilationUnit(val types: List<QTypeDef>,
                       val ifaces: List<QInterfaceDef>,
                       val inputs: List<QInputType>,
                       val scalar: List<QScalarType>,
                       val enums: List<QEnumDef>,
                       val unions: List<QUnionTypeDef>) {

	private fun <T : QDefinedType> addAllStuff(vararg foo: List<out T>): List<QDefinedType> {
		var doingGymnasticsForThis = LinkedList<QDefinedType>()
		for (f in foo) {
			doingGymnasticsForThis.addAll(f)
		}
		Collections.sort(doingGymnasticsForThis, kotlin.Comparator { o1, o2 -> o1.name.compareTo(o2.name) })
		return doingGymnasticsForThis
	}

	val all: Array<QDefinedType> = addAllStuff(types, ifaces, unions, inputs, scalar, unions, enums).toTypedArray()

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
