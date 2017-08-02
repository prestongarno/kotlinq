package com.prestongarno.transpiler

import com.prestongarno.transpiler.QCompilationUnit
import com.prestongarno.transpiler.qlang.spec.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.streams.toList

class Attr {
	companion object {

		fun attributeCompilationUnit(comp: QCompilationUnit): QCompilationUnit {
			verifyTypeInterfaceImplementation(comp.types, comp.ifaces)
			var unit = attrUnionTypes(comp.unions, comp)
			unit = attrFieldTypes(comp.types + comp.ifaces + comp.inputs, comp)
			return unit
		}

		/**
		 * Complete list of QTypes passed to this method to find and attribute all QUnknownType
		 * objects (the type each field is tagged with throughout the parsing process)
		 */
		private fun verifyTypeInterfaceImplementation(types: List<QTypeDef>, ifaces: List<QInterfaceDef>) {
			val ifaceMap = HashMap<String, QInterfaceDef>(ifaces.size + 10, 0.99f)
			ifaces.forEach { iface -> ifaceMap.put(iface.name, iface) }

			types.forEach { t ->
				val attrInterfaces: LinkedList<QInterfaceDef> = LinkedList()
				val fields = HashMap<String, QSymbol>(t.fields.size + 4, 0.99f)
				t.fields.forEach { sym -> fields.put(sym.name, sym) }

				t.interfaces.forEach { iface ->
					val attrIf = ifaceMap.get(iface.name) ?: throw IllegalArgumentException("No interface definition " +
							"'${iface.name}' found (declared on type ${t.name})")
					attrInterfaces.add(0, attrIf as QInterfaceDef)
					attrIf.fields.forEach { field ->
						fields.get(field.name) ?:
								throw IllegalArgumentException("Type '${t.name}' implements ${attrIf.name} " +
										"but does not contain a field named '${field.name}' in its declaration")
					}
				}
				t.interfaces = attrInterfaces
			}
		}

		private fun attrUnionTypes(unions: List<QUnionTypeDef>, comp: QCompilationUnit): QCompilationUnit {
			val all = comp.enums + comp.types + comp.unions + comp.ifaces + comp.scalar + comp.inputs
			val sortedAll: Array<QDefinedType> = comp.getAll().stream().sorted(compareBy { t: QDefinedType -> t.name }).toList().toTypedArray()
			unions.forEach { union ->
				union.possibleTypes = union.possibleTypes.map { t ->
					sortedAll.find(t.name)
							?: throw IllegalArgumentException("Unknown type '${t.name}' in union '$union'")
				}
			}
			return comp
		}

		private fun attrFieldTypes(types: List<QStatefulType>, comp: QCompilationUnit): QCompilationUnit {
			val all = comp.enums + comp.types + comp.unions + comp.ifaces + comp.scalar + comp.inputs
			val sortedAll: Array<QDefinedType> = comp.getAll().stream().sorted(compareBy { t: QDefinedType -> t.name }).toList().toTypedArray()
			//sortedAll.forEach { t -> println(t.name) }
			types.forEach { type ->
				type.fields.forEach { field ->
					val fieldType = sortedAll.find(field.type.name) ?:
							throw IllegalArgumentException("Unknown type '${field.type.name}' on field '${field.name}' in type ${type.name}")
					field.type = fieldType
					field.args.forEach { arg ->
						arg.type = sortedAll.find(arg.type.name) ?:
								throw IllegalArgumentException("Unknown type '${arg.type.name}' on field '${field.name}', argument '${arg.name}', in type ${type.name}")
					}
				}
			}
			return comp
		}

		/** Extension method for binary searching all types for attributing all fields
		 */
		private fun Array<QDefinedType>.find(key: String): QDefinedType? {
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
}
