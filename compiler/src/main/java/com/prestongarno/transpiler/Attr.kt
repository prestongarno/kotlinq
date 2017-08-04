package com.prestongarno.transpiler

import com.prestongarno.transpiler.QCompilationUnit
import com.prestongarno.transpiler.qlang.spec.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.streams.toList

object Attr {

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
				attrInterfaces.add(0, attrIf)
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
		unions.forEach { union ->
			union.possibleTypes = union.possibleTypes.map { t ->
				comp.find(t.name)
						?: throw IllegalArgumentException("Unknown type '${t.name}' in union '$union'")
			}
		}
		return comp
	}

	private fun attrFieldTypes(types: List<QStatefulType>, comp: QCompilationUnit): QCompilationUnit {
		val all = comp.enums + comp.types + comp.unions + comp.ifaces + comp.scalar + comp.inputs

		types.forEach { type ->
			type.fields.forEach { field ->
				val fieldType = comp.find(field.type.name) ?:
						throw IllegalArgumentException("Unknown type '${field.type.name}' on field '${field.name}' in type ${type.name}")
				field.type = fieldType
				field.args.forEach { arg ->
					arg.type = comp.find(arg.type.name) ?:
							throw IllegalArgumentException("Unknown type '${arg.type.name}' on field '${field.name}', argument '${arg.name}', in type ${type.name}")
				}
			}
		}
		return comp
	}
}
