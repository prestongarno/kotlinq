package com.prestongarno.transpiler

import com.prestongarno.transpiler.qlang.spec.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.streams.toList

object Attr {

  fun attributeCompilationUnit(comp: QCompilationUnit): QCompilationUnit {
    verifyTypesToInterfaces(comp.types, comp.ifaces)
    var unit = attrUnionTypes(comp.unions, comp)
    unit = attrFieldTypes(comp.types + comp.ifaces + comp.inputs, comp)
    unit = validateNames(unit)
    return unit
  }

  /**
   * Complete list of QTypes passed to this method to find and attribute all QUnknownType
   * objects (the type each field is tagged with throughout the parsing process)
   */
  private fun verifyTypesToInterfaces(types: List<QTypeDef>, ifaces: List<QInterfaceDef>) {
    val ifaceMap = HashMap<String, QInterfaceDef>(ifaces.size + 1, 0.99f)
    ifaces.forEach { iface -> ifaceMap.put(iface.name, iface) }

    types.forEach { t ->
      val attrInterfaces: LinkedList<QInterfaceDef> = LinkedList()
      val fields = t.fields.map { sym -> Pair(sym.name, sym) }.toMap()

      t.interfaces.forEach { iface ->
        val attrIf = ifaceMap.get(iface.name) ?: throw IllegalArgumentException("No interface definition " +
            "'${iface.name}' found (declared on type ${t.name})")
        attrInterfaces.add(0, attrIf)
        attrIf.fields.forEach { field ->
          val inherited = fields[field.name]
          if (inherited != null) inherited.inheritedFrom.add(attrIf)
          else
            throw IllegalArgumentException("Type '${t.name}' implements ${attrIf.name} " +
                "but does not contain a field named '${field.name}' in its declaration")
        }
      }
      t.interfaces = attrInterfaces
    }
  }

  private fun attrUnionTypes(unions: List<QUnionTypeDef>, comp: QCompilationUnit): QCompilationUnit {
    unions.forEach { union ->
      union.possibleTypes = union.possibleTypes.map { t ->
        comp.find(t.name)
            ?: throw IllegalArgumentException("Unknown type '${t.name}' in union '$union'")
      }
    }
    return comp
  }

  private fun attrFieldTypes(types: List<QStatefulType>, comp: QCompilationUnit): QCompilationUnit {
    types.parallelStream().map { type ->
      type.fields.map { field ->
        field.type = comp.find(field.type.name) ?:
            throw IllegalArgumentException("Unknown type '${field.type.name}' on field '${field.name}' in type ${type.name}")
        field.args.forEach { arg ->
          arg.type = comp.find(arg.type.name) ?:
              throw IllegalArgumentException("Unknown type '${arg.type.name}' on field '${field.name}', argument '${arg.name}', in type ${type.name}")
        }
        checkDiamondOverride(field, type)
      }.filter { it.isPresent }
    }.flatMap { it.stream() }
        .map { it.get() }
        .toList()
        .also { comp.addConflicts(it) }
    return comp
  }

  private fun checkDiamondOverride(field: QField, type: QStatefulType)
      : Optional<Pair<QField, Pair<QTypeDef, List<QInterfaceDef>>>> {
    if (type !is QTypeDef)
      return Optional.empty()
    type.interfaces.map { iface ->

      iface.fields.filter {
        it.name == field.name
      }.map {
        Pair(iface, it)
      }
    }.flatten().also { dup ->
      if (dup.size > 1) {
        if (field.args.isNotEmpty()) {
          println("Diamond on: [ ${type.name}.${field.name}  ] from -> ${dup.joinToString { it.first.name }}")
        }

        return Optional.of(Pair(field, Pair(type, dup.map { (first) -> first })))

      }
    }
    return Optional.empty()
  }

  private fun validateNames(comp: QCompilationUnit): QCompilationUnit {
    comp.all.map {
      if (KEYWORDS.contains(it.name))
        it.name = "${it.name}Def"
    }
    comp.stateful.map {
      it.fields.map { f ->
        if (KEYWORDS.contains(f.name))
          f.name = "${f.name}Val"
      }
    }
    return comp
  }

  internal val KEYWORDS: HashSet<String> = hashSetOf(
      "package",
      "as",
      "typealias",
      "class",
      "this",
      "super",
      "val",
      "var",
      "fun",
      "for",
      "null",
      "true",
      "false",
      "is",
      "in",
      "throw",
      "return",
      "break",
      "continue",
      "object",
      "if",
      "try",
      "else",
      "while",
      "do",
      "when",
      "interface",
      "yield",
      "typeof",
      "yield",
      "typeof"
  )
}

