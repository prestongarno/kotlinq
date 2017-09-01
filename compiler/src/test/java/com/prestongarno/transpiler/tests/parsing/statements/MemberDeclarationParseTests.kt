package com.prestongarno.transpiler.tests.parsing.statements

import com.prestongarno.transpiler.QLexer
import com.prestongarno.transpiler.qlang.spec.*
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
					&& field.inputArgs[0].symbol == "limitTo"
					&& field.inputArgs[0].type == "Int"
					&& !field.inputArgs[0].isList
					&& field.inputArgs[0].isNullable
					&& field.directive.first == "foo"
					&& field.directive.second == "bar"
					&& field.isList
					&& !field.isNullable)
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
		}

	}

	@Test
	fun fieldListWithDirective() {

		val rawField = "foofield: [Object] @directive(\"foo @*^%$@^&%$#@}{\")"

		QLexer.baseFields(rawField).forEach { field ->
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
			assert(field.symbol == "foofield"
					&& field.type == "Object"
					&& field.inputArgs.size == 1
					&& field.inputArgs[0].symbol == "query"
					&& field.inputArgs[0].type == "String"
					&& !field.inputArgs[0].isList
					&& !field.inputArgs[0].isNullable
					&& field.isList
					&& field.isNullable
					&& field.directive.first == "directive"
					&& field.directive.second == "\"foo: @*^%$@^&%$#@}{\"")
		}
		assert(baseFields.size == 1)

	}

	@Test
	fun multipleFields() {

		val rawField = "\n\t    foofield(\n\tquery: String!): [Object] @directive(\"foo: @*^%$@^&%$#@}{\")" +
				"\n\t    fooFieldTwo(\n   \t   queryTwo: [Object!] = null): Int @directiveTwo(fooTwo: \"bars\")"

		val baseFields = QLexer.baseFields(rawField)

		assert(baseFields.size == 2)

		assert(baseFields[0].symbol == "foofield"
				&& baseFields[0].type == "Object"
				&& baseFields[0].inputArgs.size == 1
				&& baseFields[0].inputArgs[0].symbol == "query"
				&& baseFields[0].inputArgs[0].type == "String"
				&& !baseFields[0].inputArgs[0].isList
				&& !baseFields[0].inputArgs[0].isNullable
				&& baseFields[0].isList
				&& baseFields[0].isNullable
				&& baseFields[0].directive.first == "directive"
				&& baseFields[0].directive.second == "\"foo: @*^%$@^&%$#@}{\"")

		assert(baseFields[1].symbol == "fooFieldTwo"
				&& baseFields[1].type == "Int"
				&& baseFields[1].inputArgs.size == 1
				&& baseFields[1].inputArgs[0].symbol == "queryTwo"
				&& baseFields[1].inputArgs[0].type == "Object"
				&& baseFields[1].inputArgs[0].isList
				&& !baseFields[1].inputArgs[0].isNullable
				&& !baseFields[1].isList
				&& baseFields[1].isNullable
				&& baseFields[1].directive.first == "directiveTwo"
				&& baseFields[1].directive.second == "fooTwo: \"bars\"")
	}
}
