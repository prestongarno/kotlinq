package com.prestongarno.transpiler

import com.prestongarno.transpiler.kotlin.spec.QTypeBuilder
import com.prestongarno.transpiler.qlang.spec.QDefinedType
import com.prestongarno.transpiler.qlang.spec.Scalar
import com.squareup.kotlinpoet.KotlinFile
import java.io.File
import kotlin.streams.toList

class QCompiler {
	fun compile(file: File) : QCompilationUnit {
		val result = Attr.attributeCompilationUnit(QLParser().parse(file))
		return result
	}

	fun generateKotlinTypes(comp: QCompilationUnit, rootQuery: String, rootMutation: String, rootPackageName: String = "com.prestongarno.ktq") : List<String> {
		val sortedAll:
		val queryType = comp.find(rootQuery) as QDefinedType?: throw IllegalArgumentException("Root query type was specified as $rootQuery but was not declared in schema")
		val mutationType = comp.find(rootMutation) as QDefinedType?:
				throw IllegalArgumentException("Root mutation type was specified as $rootMutation but was not declared in schema")
		val ktBuilder = KotlinFile.builder("com.prestongarno.ktq", "QTypes")
		comp.types.map { t -> ktBuilder.addType(QTypeBuilder.createType(t)) }
		ktBuilder.build().writeTo(System.out)
		return listOf("none")
	}

	/** Extension method for binary searching all types for attributing all fields
	 */
	fun Array<QDefinedType>.find(key: String): QDefinedType? {
		// if scalar type get the predefined ones in Scalar companion object
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