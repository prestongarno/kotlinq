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

    var comments = ""

    while (scanner.hasNext()) {
      val declType = scanner.useDelimiter("\\s").next().trim()

      comments =
          if (comments.isNotEmpty()) {
            comments
          } else if (declType.trim().startsWith("#")) {
            var entire = declType.append(scanner.nextLine())
            while (scanner.hasNext("\\s*#.*"))
              entire = entire.append("<<||>>" + scanner.nextLine().trim())
            comments = entire.trim()
                .substring(1)
                .replace("<<||>>#", "\n")
            continue
          } else ""

      val typeKind = RootType.match(declType)

      val name = scanner.next().trim()

      when (typeKind) {
        UNKNOWN -> {
          throw IllegalArgumentException("Unknown type declaration \"$declType\"")
        }

        UNION -> {
          scanner.useDelimiter("[a-zA-Z0-9_]".toRegex().pattern).next()
          val block = scanner.nextLine()
          all.add(0, QUnionTypeDef(name, QLexer.unionFields(block)
              .map { str -> QUnknownType(str) }))
        }

        ENUM -> all.add(0, QEnumDef(name, QLexer.enumFields(scanner.useDelimiter("}").next())))

        TYPE -> {
          val ifaces = scanner.useDelimiter("\\{")
              .next()
              .split("[\\s,]".toRegex())
              .filter { s -> s.isNotBlank() }

          val fields = lexFieldsToSymbols(QLexer.baseFields(scanner.getClosure()))
          all.add(0, QTypeDef(name,
              if (ifaces.isEmpty()) {
                emptyList()
              } else {
                ifaces.subList(1, ifaces.size)
                    .map { s -> QUnknownInterface(s) }
              }
              , fields))
        }

        INTERFACE -> all.add(0, QInterfaceDef(name,
            lexFieldsToSymbols(QLexer.baseFields(scanner.getClosure()))
                .also {
                  it.forEach { it.abstract(true) }
                }))

        SCALAR -> all.add(0, QCustomScalarType(name))
        INPUT -> all.add(0, QInputType(name, lexFieldsToSymbols(QLexer.baseFields(scanner.getClosure()))))
      }
      if (comments.trim().isNotEmpty()) {
        all[0].description = comments
        comments = ""
      }
      scanner.useDelimiter("[a-zA-Z#]").next()
    }
    return QCompilationUnit(all.only(), all.only(), all.only(), all.only(), all.only(), all.only())
  }

  private fun lexFieldsToSymbols(fields: List<Field>): List<QField> =
      fields.map { (symbol, inputArgs, type, directive, isList, isNullable, comment) ->
        QField(symbol, QUnknownType(type),
            inputArgs.map { arg ->
              QFieldInputArg(arg.symbol,
                  QUnknownType(arg.type),
                  arg.defaultValue,
                  arg.isList,
                  arg.isNullable)
            },
            QDirectiveSymbol(QUnknownType(directive.first), directive.second),
            isList,
            isNullable,
            comment)
      }
}

private fun Scanner.getClosure() = useDelimiter("}").next().trim().substring(1)

private inline fun <reified T : QSchemaType<*>> List<QSchemaType<*>>.only(): List<T> = filterIsInstance(T::class.java)

private fun String.append(to: String) = this + to
