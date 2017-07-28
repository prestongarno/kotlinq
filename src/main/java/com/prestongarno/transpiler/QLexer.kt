package com.prestongarno.transpiler

import java.util.*
import java.util.regex.Pattern

object QLexer {

	var NAME = Regex("[a-zA-Z_][a-zA-Z0-9_]+")
	var TYPE = Regex("\\[?[a-zA-Z_][a-zA-Z0-9_]+!?\\]?!?")
	var INPUT = Regex("($NAME)(\\s*:\\s*)($TYPE)(?:\\s*=\\s*)?(.*?)?")
	//====== Sub-Type declaration types ======//
	var LIST = Regex("\\[[a-zA-Z_]+!?]")
	var NON_NULL = Regex("\\[?[a-zA-Z_]+!]?")

	/** Regex to match entire field (input) **/
	var INPUT_FIELD = Regex("($NAME)\\((.*?)\\):\\s*?($TYPE)", RegexOption.DOT_MATCHES_ALL)
	var FIELD = Regex("($NAME)(\\s*:\\s*)($TYPE)")
	val DIRECTIVE = Regex("@[a-zA-Z0-9_]*\\(.*?\\)")

	fun baseFields(block: String): List<Field> = baseFields(block, INPUT_FIELD) + baseFields(block.replace(INPUT_FIELD, ""), FIELD)

	private fun baseFields(block: String, whichRegex: Regex): List<Field> {
		val fields = whichRegex.findAll(block.replace(DIRECTIVE, "")).map { result -> result.groups }.map { group ->
			Field(group[1]!!.value,
					if (group[2] != null && whichRegex == INPUT_FIELD) {
						baseFields(group[2]!!.value.trim(), INPUT)
					} else Collections.emptyList(),
					group[3]!!.value.replace("[\\[\\]!]".toRegex(), ""),
					LIST.matches(group[3]!!.value),
					!NON_NULL.containsMatchIn(group[3]!!.value))
		}.toList()
		val shouldBeEmpty = block.replace(DIRECTIVE, "").replace(INPUT_FIELD, "").replace(FIELD, "").replace("[@\n]".toRegex(), "").trim()
		assert(shouldBeEmpty.isEmpty())//TODO take directives into account
		return fields
	}

	fun enumFields(block: String): List<String> = NAME.findAll(block).map { result -> result.groups }
			.map { col -> col[0]!!.value }.toList()

	fun unionFields(block: String): List<String> = block.split("|").map { s -> s.trim() }.filter { s -> s.isNotEmpty() }

}

data class Field(val symbol: String, val inputArgs: List<Field>, val type: String, val isList: Boolean, val isNullable: Boolean) {
	override fun toString(): String {
		return "$symbol || args => ${inputArgs.joinToString(", ")} || type:\t$type || isList=$isList || isNullable=$isNullable)"
	}
}

