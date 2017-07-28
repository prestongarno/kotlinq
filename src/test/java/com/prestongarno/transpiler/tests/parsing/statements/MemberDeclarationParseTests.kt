package com.prestongarno.transpiler.tests.parsing.statements

import com.prestongarno.transpiler.QLexer
import com.prestongarno.transpiler.qlang.specc.*
import org.junit.Test
import java.util.*

class MemberDeclarationParseTests {

	@Test
	fun fieldParsingTestAllOptions() {

		val rawField = "foofield(limitTo: Int = 10): [Int!] @foo(bar)"

		QLexer.baseFields(rawField).forEach { field ->
			assert(field.symbol == "foofield"
					&& field.type == "Int"
					&& field.inputArgs.size == 1
					&& field.inputArgs[0]!!.symbol == "limitTo"
					&& field.inputArgs[0]!!.type == "Int"
					&& !field.inputArgs[0]!!.isList
					&& field.inputArgs[0]!!.isNullable
					&& field.directive.first == "foo"
					&& field.directive.second == "bar"
					&& field.isList
					&& !field.isNullable)
			println(field)
		}

	}

	@Test
	fun fieldBasic() {

		val rawField = "foofield: String"

		QLexer.baseFields(rawField).forEach { field ->
			assert(field.symbol == "foofield"
					&& field.type == "String"
					&& field.inputArgs == Collections.emptyList<QFieldInputArg>()
					&& !field.isList
					&& field.isNullable)
			println(field)
		}

	}

	@Test
	fun fieldListNullable() {

		val rawField = "foofield: [Object]"

		QLexer.baseFields(rawField).forEach { field ->
			assert(field.symbol == "foofield"
					&& field.type == "Object"
					&& field.inputArgs == Collections.emptyList<QFieldInputArg>()
					&& field.isList
					&& field.isNullable)
			println(field)
		}

	}

	@Test
	fun fieldListNonNullable() {

		val rawField = "foofield: [Object!]"

		QLexer.baseFields(rawField).forEach { field ->
			assert(field.symbol == "foofield"
					&& field.type == "Object"
					&& field.inputArgs == Collections.emptyList<QFieldInputArg>()
					&& field.isList
					&& !field.isNullable)
			println(field)
		}

	}

	@Test
	fun fieldListWithDirective() {

		val rawField = "foofield: [Object] @directive(\"foo @*^%$@^&%$#@}{\")"

		QLexer.baseFields(rawField).forEach { field ->
			println(field)
			assert(field.symbol == "foofield"
					&& field.type == "Object"
					&& field.inputArgs == Collections.emptyList<QFieldInputArg>()
					&& field.isList
					&& field.isNullable
					&& field.directive.first == "directive"
					&& field.directive.second == "\"foo @*^%$@^&%$#@}{\"")
		}

	}

	@Test
	fun fieldInputWithDirective() {

		val rawField = "\n\t    foofield(\n\tquery: String!): [Object] @directive(\"foo: @*^%$@^&%$#@}{\")"

		val baseFields = QLexer.baseFields(rawField)
		baseFields.forEach { field ->
			println(field)
			assert(field.symbol == "foofield"
					&& field.type == "Object"
					&& field.inputArgs.size == 1
					&& field.inputArgs[0]!!.symbol == "query"
					&& field.inputArgs[0]!!.type == "String"
					&& !field.inputArgs[0]!!.isList
					&& !field.inputArgs[0]!!.isNullable
					&& field.isList
					&& field.isNullable
					&& field.directive.first == "directive"
					&& field.directive.second == "\"foo: @*^%$@^&%$#@}{\"")
		}
		assert(baseFields.size == 1)

	}
}
