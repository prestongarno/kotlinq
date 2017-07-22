package com.prestongarno.transpiler

import com.prestongarno.transpiler.EvalTokens.*
import java.io.File
import java.io.InputStream
import java.util.*
import java.util.function.*
import java.util.function.Function
import java.util.stream.Collectors
import kotlin.streams.toList

/**
 * Created by preston on 7/20/17.
 */

class QLParser(val source: InputStream) {
	fun parse(): QCompilationUnit {

		val typesMap = EnumSet.allOf(RootToken::class.java).toList().stream()
				.collect(Collectors.toMap(
						Function.identity<RootToken>(), Function<RootToken, MutableList<Pair<String, List<String>>>>
				{ LinkedList<Pair<String, List<String>>>() }))

		val schema = formatToString(source).trim()
				.replace(Regex("\\s*}\\s*"), "<>")
				.replace(Regex("\\s*\\{\\s*"), "<I>")
				.replace(Regex("\\s*\n\\s*"), "<b>")
				.replace(Regex("\\s*,\\s*"), "<b>")
				.split(Regex("\\s*<>\\s*")).stream()
				.filter({ t -> !t.isNullOrEmpty() })
				.map({ t -> t.trim() })
				.map({ t ->
					{
						val endIndexKind = t.indexOf(" ")
						val typeKind = t.substring(0, endIndexKind)
						val endIndexName = t.indexOf("<")
						val name = t.substring(t.indexOf(" ") + 1, endIndexName)
						val kind = RootToken.match(typeKind)
						val value = Pair(name, t.split("<I>")[1].replace(" ", ""))
						Pair(kind, value)
					}
				}).peek({ t -> println("${t.invoke().first} ---> ${t.invoke().second}") })
				.toList() // creates a list of all types

		return QCompilationUnit()
	}

	fun formatToString(inputStream: InputStream): String {
		val scanner: Scanner = Scanner(inputStream, "UTF-8").useDelimiter("\\A")
		val result = scanner.next()
		println(result)
		return result
	}
}


fun main(args: Array<String>) {
	QLParser(File("/home/preston/AndroidStudioProjects/ktq/src/com/prestongarno/example/graphql.schema.graphqls").inputStream()).parse()
	QLParser(("type MyDefinition {hello: String        \n}\n  " +
			"interface SchemaInterface{ age: Int        \n      definition: MyDefinition} \n" +
			"enum Switcher { KIND, OF,WAY}  ").byteInputStream()).parse()


}





















