package com.prestongarno.transpiler

import com.prestongarno.transpiler.qlang.spec.*
import com.prestongarno.transpiler.qlang.spec.RootType.*
import java.io.File
import java.io.InputStream
import java.util.*

/**
 * Created by preston on 7/20/17.
 */

class QLParser {

  fun parse(file: File): QCompilationUnit = parse(file.inputStream())

  fun parse(ioStream: InputStream): QCompilationUnit {

    val all = LinkedList<QSchemaType<*>>()

    val scanner = Scanner(ioStream)


    while (scanner.hasNext()) {
      val declType = scanner.useDelimiter("\\s").next().trim()
      val typeKind = RootType.match(declType)

      val name = scanner.next().trim()

      when (typeKind) {
        UNKNOWN -> throw IllegalArgumentException("Unknown type declaration \"$declType\"")
        UNION -> {
          scanner.useDelimiter("[a-zA-Z0-9_]".toRegex().pattern).next()
          val block = scanner.nextLine()
          all.add(0, QUnionTypeDef(name, QLexer.unionFields(block)
              .map { str -> QUnknownType(str) }))
        }
        ENUM -> all.add(0, QEnumDef(name, QLexer.enumFields(scanner.useDelimiter("}").next())))
        TYPE -> {
          val ifaces = scanner.useDelimiter("\\{").next().split("[\\s,]".toRegex()).filter { s -> s.isNotBlank() }
          val fields = mapLexerFieldsToSymbols(QLexer.baseFields(scanner.useDelimiter("}").next().trim().substring(1)))
          all.add(0, QTypeDef(name, if (ifaces.isEmpty()) Collections.emptyList() else ifaces.subList(1, ifaces.size).map { s -> QUnknownInterface(s) }, fields))
        }
        INTERFACE -> all.add(0, QInterfaceDef(name,
            mapLexerFieldsToSymbols(QLexer
                .baseFields(scanner.useDelimiter("}")
                    .next()
                    .trim()
                    .substring(1)))
                .also { it.forEach { it.abstract(true) } }))
        SCALAR -> all.add(0, QCustomScalarType(name))
        INPUT -> all.add(0, QInputType(name, mapLexerFieldsToSymbols(QLexer.baseFields(scanner.useDelimiter("}").next().trim().substring(1)))))
      }
      scanner.useDelimiter("[a-zA-Z]").next()
    }
    return QCompilationUnit(all.filter { q -> q is QTypeDef }.map { q -> q as QTypeDef },
        all.filter { q -> q is QInterfaceDef }.map { q -> q as QInterfaceDef },
        all.filter { q -> q is QInputType }.map { q -> q as QInputType },
        all.filter { q -> q is QScalarType }.map { q -> q as QScalarType },
        all.filter { q -> q is QEnumDef }.map { q -> q as QEnumDef },
        all.filter { q -> q is QUnionTypeDef }.map { q -> q as QUnionTypeDef })
  }

  private fun mapLexerFieldsToSymbols(fields: List<Field>): List<QField> = fields.map { (symbol, inputArgs, type, directive, isList, isNullable) ->
    QField(symbol, QUnknownType(type),
        inputArgs.map { arg -> QFieldInputArg(arg.symbol, QUnknownType(arg.type), arg.defaultValue, arg.isList, arg.isNullable) },
        QDirectiveSymbol(QUnknownType(directive.first), directive.second),
        isList,
        isNullable)
  }
}
