package com.prestongarno.transpiler

enum class RootToken(val token: String) {
	TYPE("type"),
	INTERFACE("interface"),
	UNION("union"),
	INPUT("input"),
	ENUM("enum");

	companion object {
		fun getFor(declaration: String): Pair<RootToken, String> {
			val type = declaration.split(" ")[0]
			val name = declaration.trim().removeRange(0, type.length)
			println(RootToken.valueOf(type.toUpperCase()))
			return Pair(TYPE, name)
		}
	}
}

enum class EvalTokens(val token: String) {
	ROOT("{"),
	ROOT_EXIT("}")
}
