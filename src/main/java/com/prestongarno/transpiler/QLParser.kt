package com.prestongarno.transpiler

import com.prestongarno.transpiler.RootType.*
import java.io.File
import java.io.InputStream
import java.util.*
import java.util.function.Function
import java.util.function.Function.*
import java.util.regex.Pattern
import java.util.stream.Collectors
import kotlin.streams.toList

/**
 * Created by preston on 7/20/17.
 */

class QLParser {

	fun parse(files: Set<File>): List<QCompilationUnit> = files.stream()
			.map { f -> parse(f.inputStream()) }
			.toList()

	private fun parse(ioStream: InputStream): QCompilationUnit {

		val rawTypes = EnumSet.allOf(RootType::class.java).toList().stream()
				.collect(Collectors.toMap( identity<RootType>(),
						Function<RootType, MutableList<Pair<String, List<String>>>>
				{ LinkedList<Pair<String, List<String>>>() }))

		val scanner: Scanner = Scanner(ioStream, "UTF-8")
		val scopeEntry = Pattern.compile("\\s*?\\{\\s*?", Pattern.MULTILINE)
		val lineBreak = Pattern.compile("\\s*\n\\s*", Pattern.MULTILINE)
		val scopeExit = Pattern.compile("\\s*[}]\\s*", Pattern.MULTILINE)
		val nextCharacter = Pattern.compile("[a-z]", Pattern.MULTILINE)
		val nextSpace = Pattern.compile("\\s|\n", Pattern.MULTILINE)

		while (scanner.hasNext()) {
			val kind = RootType.match(scanner.useDelimiter(nextSpace).next().trim())
			if (kind == UNION) {
				// no brackets on the unions -> special case
				val unionStatement = scanner.useDelimiter(lineBreak).next()
				val splitIndex = unionStatement.indexOf("=")
				val name = unionStatement.substring(0, splitIndex).trim()
				val declaration = unionStatement.substring(splitIndex + 1, unionStatement.length)
				rawTypes[UNION]!!.add(0, Pair(name, declaration.split(Regex("\\s*\\|\\s*"))))
			} else if (kind == SCALAR) {
				rawTypes[SCALAR]!!.add(0, Pair(scanner.useDelimiter(nextSpace).next().trim(), Collections.emptyList()))
			} else if (kind == UNKNOWN) {
				// log and keep going
				val line = scanner.nextLine()
				val errorBlock: String
				if (line.contains("\\|")) {
					errorBlock = line
				} else {
					errorBlock = scanner.useDelimiter(scopeExit).next()
				}
				rawTypes[UNKNOWN]!!.add(0, Pair(errorBlock, Collections.emptyList()))
			} else {
				val name = scanner.useDelimiter(scopeEntry).next()
				val block = scanner.useDelimiter(scopeExit).next().substring(1).trim()
				val statements = block.split(Regex("\\s*[,|\n]\\s*")).stream()
						.filter({ t -> t != "{" })
						.map({ t -> t.replace(Regex("\\s*"), "").replace(Regex("[}{]"), "").trim() }).toList()
				rawTypes[kind]!!.add(0, Pair(name, statements))
			}
			scanner.useDelimiter(nextCharacter).next()
		}
		rawTypes.forEach({ entry -> entry.value.forEach({ pair -> println(pair) }) })

		if (rawTypes[UNKNOWN]!!.size > 0) {

		}

		val types = rawTypes[RootType.TYPE]!!.asIterable().toList()
		val interfaces = rawTypes[RootType.INTERFACE]!!.asIterable().toList()
		val unions = rawTypes[RootType.UNION]!!.asIterable().toList()
		val scalars = rawTypes[RootType.SCALAR]!!.asIterable().toList()
		val enums = rawTypes[RootType.ENUM]!!.asIterable().toList()
		return QCompilationUnit(types, interfaces, unions, scalars, enums)
	}

	companion object {
		fun main(args: Array<String>) {
			QLParser().parse(File("/home/preston/AndroidStudioProjects/ktq/src/com/prestongarno/example/graphql.schema.graphqls").inputStream())
			QLParser().parse(("type MyDefinition {hello: String        \n}\n  " +
					"interface SchemaInterface{ age: Int        \n      definition: MyDefinition} \n" +
					"enum Switcher { KIND, OF,WAY}  ").byteInputStream())
		}
	}
}






















