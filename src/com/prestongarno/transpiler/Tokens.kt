package com.prestongarno.transpiler

import java.util.*
import java.util.function.Function
import java.util.stream.Collectors
import kotlin.collections.HashMap

enum class RootToken(val token: String) {
	TYPE("type"),
	INTERFACE("interface"),
	UNION("union"),
	INPUT("input"),
	ENUM("enum"),
	UNKNOWN("unknown");

	companion object {
		val values: Map<String, RootToken>

		init {
			values = EnumSet.allOf(RootToken::class.java).toList().stream()
					.collect(Collectors.toMap(Function<RootToken, String>{ t -> t.token },
							Function<RootToken, RootToken>{ t -> t })).toMap(HashMap<String, RootToken>())
		}

		fun match(keyword: String) : RootToken = values[keyword] ?: UNKNOWN
	}
}

enum class EvalTokens(val token: String) {
	ROOT("\\{"),
	ROOT_EXIT("\\}"),
	NEXT_DECLARATION("[a-z]"),
	BREAK("<b>")
}
