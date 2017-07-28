package com.prestongarno.transpiler

import com.prestongarno.transpiler.qlang.specc.*
import com.prestongarno.transpiler.qlang.specc.RootType.*
import java.io.File
import java.io.InputStream
import java.util.*
import kotlin.streams.toList

/**
 * Created by preston on 7/20/17.
 */

class QLParser {

	fun parse(files: Set<File>): List<QCompilationUnit> = files.stream()
			.map { f -> parse(f.inputStream()) }
			.toList()

	fun parse(ioStream: InputStream): QCompilationUnit {

		val all = LinkedList<QType>()

		val scanner = Scanner(ioStream)


		while (scanner.hasNext()) {
			val declType = scanner.useDelimiter("\\s").next().trim()
			val typeKind = RootType.match(declType)

			val name = scanner.next().trim()

			println(name)

			when (typeKind) {
				UNKNOWN -> throw IllegalArgumentException("Unknown type declaration \"$declType\"")
				UNION -> {
					val block = scanner.nextLine()
					all.add(QUnionTypeDef(name, QLexer.unionFields(block.substring(block.indexOf("=")))
							.map { str -> QUnknownType(str) }))
				}
				ENUM -> all.add(QEnumDef(name, QLexer.enumFields(scanner.useDelimiter("}").next())))
				TYPE -> {
					val ifaces = scanner.useDelimiter("\\{").next().split("[\\s,]".toRegex()).filter { s -> s.isNotBlank() }
					val fields = mapLexerFieldsToSymbols(QLexer.baseFields(scanner.useDelimiter("}").next().trim().substring(1)))
					all.add(QTypeDef(name, if(ifaces.isEmpty()) Collections.emptyList() else ifaces.subList(1, ifaces.size).map { s -> QUnknownType(s) }, fields))
				}
				INTERFACE -> all.add(QInterfaceDef(name, mapLexerFieldsToSymbols(QLexer.baseFields(scanner.useDelimiter("}").next().trim().substring(1)))))
				SCALAR -> all.add(QScalarType(Scalar.UNKNOWN, name))
				INPUT -> all.add(QInputType(name, mapLexerFieldsToSymbols(QLexer.baseFields(scanner.useDelimiter("}").next().trim().substring(1)))))
			}
			scanner.useDelimiter("[a-zA-Z]").next()
		}
		return QCompilationUnit(all)
	}

	fun mapLexerFieldsToSymbols(fields: List<Field>) : List<QSymbol> = fields.map { (symbol, inputArgs, type, isList, isNullable) ->
				QField(symbol, QUnknownType(type),
						inputArgs.map { arg -> QField(arg.symbol, QUnknownType(arg.type), Collections.emptyList(), arg.isList, arg.isNullable) },
						isList,
						isNullable)
			}
}
