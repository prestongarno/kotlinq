package com.prestongarno.transpiler.qlang.spec

import java.util.*
import java.util.stream.Collectors

enum class RootType(val token: String) {
	TYPE("type"),
	INTERFACE("interface"),
	UNION("union"),
	INPUT("input"),
	ENUM("enum"),
	SCALAR("scalar"),
	UNKNOWN("unknown");

	companion object matcher {
		val values: Map<String, RootType>

		init { values = EnumSet.allOf(RootType::class.java).toList().stream().collect(Collectors.toMap({ t -> t.token }, { t -> t})) }

		fun match(keyword: String): RootType = values[keyword] ?: UNKNOWN
	}

}