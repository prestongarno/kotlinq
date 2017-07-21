package com.prestongarno.transpiler

import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.util.*
import java.util.function.BiConsumer
import java.util.function.BinaryOperator
import java.util.function.Consumer
import java.util.function.Function
import java.util.stream.Collectors
import kotlin.collections.HashMap

/**
 * Created by preston on 7/20/17.
 */

class QLParser(val source: InputStream) {
	fun parse(): QCompilationUnit {

		val mList = EnumSet.allOf(RootToken::class.java).toList();
		val map = mList.stream().collect(Collectors.toMap(Function.identity<RootToken>(), Function<RootToken, String>{t -> t.token }))
		map.entries.forEach(Consumer { t -> println(t) })
		//mList.forEach(Consumer { t -> println(t) })
		val foo = HashMap<Pair<RootToken, String>, String>()
		val scanner = Scanner(source)
		scanner.useDelimiter("[{]")

/*		while(scanner.hasNext()) {
			scanner.nextRoot()
		}*/

		println(RootToken.getFor(scanner.next()))
		return QCompilationUnit()
	}

	fun Scanner.nextRoot(): String {
		return this.useDelimiter("[{]").next()
	}

	fun Scanner.codeBlock(): String {
		return useDelimiter("[}]").next()
	}
}


fun main(args: Array<String>) {
	QLParser("type Definition {\nhello: String\n}".byteInputStream()).parse()
}
