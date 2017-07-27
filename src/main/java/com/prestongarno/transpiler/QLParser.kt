package com.prestongarno.transpiler

import com.prestongarno.transpiler.qlang.specc.RootType
import com.prestongarno.transpiler.qlang.specc.RootType.*
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

	fun parse(ioStream: InputStream): QCompilationUnit {

		val rawTypes = EnumSet.allOf(RootType::class.java).toList().stream()
				.collect(Collectors.toMap(identity<RootType>(),
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

				val line = scanner.nextLine()
				val errorBlock: String

				if (line.contains("\\|")) {
					errorBlock = line
				} else {
					errorBlock = scanner.useDelimiter(scopeExit).next()
				}
				rawTypes[UNKNOWN]!!.add(0, Pair(errorBlock, Collections.emptyList()))
			} else {
				val name = scanner.useDelimiter(scopeEntry).next().trim()
				var target = scanner.useDelimiter(scopeExit).next().trim().substring(1)
				val compile = Pattern.compile("\\(([^)]+)\\)", Pattern.MULTILINE)
				var matcher = compile.matcher(target)
				val groupCount = matcher.groupCount()
				var indexer: Int = target.indexOf('(')
				while (indexer > 0) {
					val endIndex = target.indexOf(')', indexer)
					target = target.replaceRange(indexer, endIndex + 1, target.substring(indexer, endIndex + 1)
							.replace("([^(])\n+".toRegex(), "\$1,")
							.replace("\\s+".toRegex(), ""))
					indexer = target.indexOf('(', indexer + 1)
				}
				//target = target.replace("<,", "(").replace(">", ")")
				val split = target.split(Regex("\n+")).filter { s -> s.isNotEmpty().and(s.isNotBlank()) }
						.map { s -> s.replace(Regex("@.*$"), "").trim() } //TODO -> handle directives in statement parser
				rawTypes[kind]!!.add(0, Pair(name, split))
			}
			scanner.useDelimiter(nextCharacter).next()
		}
		//rawTypes.forEach({ entry -> entry.value.forEach({ pair -> println(pair) }) })

		if (rawTypes[UNKNOWN]!!.size > 0) {
			// todo log errors and exit
		}

		val types = rawTypes[TYPE]!!.asIterable().toList()
		val interfaces = rawTypes[INTERFACE]!!.asIterable().toList()
		val unions = rawTypes[UNION]!!.asIterable().toList()
		val scalars = rawTypes[SCALAR]!!.asIterable().toList()
		val enums = rawTypes[ENUM]!!.asIterable().toList()
		return QCompilationUnit(types, interfaces, unions, scalars, enums)
	}
}

object Lexer {

	var NAME = Regex("[a-zA-Z_]+")
	var TYPE = Regex("\\[?[a-zA-Z_]+!?\\]?!?")
	var INPUT = Regex("[a-zA-Z_]+\\s*?:\\s*?[a-zA-Z_]+[,[\\n]+]")
	var INPUT_DEF_VALUE = Regex("=\\s*[a-zA-Z0-9_\"]+[,[\\n]+]")
	//====== Sub-Type declaration types ======//
	var LIST = Regex("\\[[a-zA-Z_]+!?]")
	var NON_NULL = Regex("\\[?[a-zA-Z_]+!]?")

	/** Regex to match entire field (input) **/
	var INPUT_FIELD = Regex("($NAME)\\([$INPUT|$INPUT_DEF_VALUE]+\\)[\\s\n]*:[\\s\n]*($TYPE)", RegexOption.MULTILINE)
	var FIELD = Regex("($NAME)\\s*:\\s*($TYPE)")


	fun readFields(block: String): List<Field> {
		val inputFields = INPUT_FIELD.findAll(block).map { result -> result.groups }.map { group ->
			Field(group[1]!!.value,
					FIELD.findAll("\\(([^)].*)".toRegex().find(group[0]!!.value)!!.value).map { result -> result.value }.map { str ->
						val indexOf = str.indexOf("=")
						val defaultVal = if (indexOf < 0) "" else str.substring(indexOf + 1, str.length).trim()
						val splitIndex = str.indexOf(":")
						InputArgument(str.substring(0, splitIndex),
								str.substring(splitIndex + 1, if (indexOf < 0) str.length else str.length).trim(),
								defaultVal)
					}.toList(),
					group[2]!!.value.replace("[\\[\\]!]".toRegex(), ""),
					LIST.matches(group[2]!!.value),
					NON_NULL.containsMatchIn(group[2]!!.value))
		}.toList()
		val blockMinusInputFields = block.replace(INPUT_FIELD, "")
		val basicFields = FIELD.findAll(blockMinusInputFields).map { result -> result.groups }.map { group ->
			Field(group[1]!!.value,
					Collections.emptyList(),
					group[2]!!.value.replace("[\\[\\]!]".toRegex(), ""),
					LIST.matches(group[2]!!.value),
					NON_NULL.containsMatchIn(group[2]!!.value))
		}.toList()
		val shouldBeEmpty = blockMinusInputFields.replace(FIELD, "").replace("[{}\n]".toRegex(), "").trim()
		assert(shouldBeEmpty.isEmpty())
		return inputFields.plus(basicFields)
	}

}

val testFields = listOf<String>("login: String!", "resourcePath: URI!", "url: URI!")
val test = "{\n avatarUrl(\nsize: Int): URI!\nlogin: String!\nresourcePath: URI!\nurl: URI!\n}"

data class Field(val symbol: String, val inputArgs: List<InputArgument>, val type: String, val isList: Boolean, val isNullable: Boolean)
data class InputArgument(val name: String, val type: String, val defaultValue: String)


fun main(array: Array<String>) {
	Lexer.readFields(test).forEach { r -> println(r) }
}














