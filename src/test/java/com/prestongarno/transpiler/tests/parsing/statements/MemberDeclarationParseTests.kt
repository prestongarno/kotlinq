package com.prestongarno.transpiler.tests.parsing.statements

import com.prestongarno.transpiler.TypeIrMapper
import com.prestongarno.transpiler.qlang.specc.*
import com.prestongarno.transpiler.tests.parsing.GraphQlParseTest

class MemberDeclarationParseTests : GraphQlParseTest() {

	@org.junit.Test
	fun primitiveStringNoArgs() {
		val symbol = TypeIrMapper.createSymbol("myVariableDeclaration: String", "TestSuite")
		assert(symbol.nullable)
		assert(symbol.args.isEmpty())
		assert(symbol.type is QScalarType)
	}

	@org.junit.Test
	fun primitiveStringNoArgsNotNullable() {
		val symbol = TypeIrMapper.createSymbol("myVar: String!", "TestSuite")
		assert(!symbol.nullable)
		assert(symbol.args.isEmpty())
		assert(symbol.type is QScalarType)
		assert(symbol is QStringSymbol)
		assert(symbol.name == "myVar")
	}

	@org.junit.Test
	fun primitiveIntNoArgsNotNullable() {
		val symbol = TypeIrMapper.createSymbol("myVar: Int", "TestSuite")
		assert(!symbol.nullable)
		assert(symbol.args.isEmpty())
		assert(symbol.type is QScalarType)
		assert(symbol is QIntSymbol)
		assert(symbol.name == "myVar")
	}

	@org.junit.Test
	fun intStatementWithArgs() {
		val symbol = TypeIrMapper.createSymbol("myVar(maxLength: Int): String", "TestSuite")
		assert(!symbol.args.isEmpty())
		assert(symbol.args[0].type is QScalarType)
		assert(symbol.name == "myVar")
	}

	@org.junit.Test
	fun intStatementWithArgsReturningCollection() {
		val symbol = TypeIrMapper.createSymbol("myVar(maxLength: Int): [String]", "TestSuite")
		println(symbol)
		assert(!symbol.args.isEmpty())
		assert(symbol is QCollectionType)
		assert(symbol.type is QListWrapper)
		assert(( symbol.type as QListWrapper ).wrappedType.name == "String")
	}

	@org.junit.Test
	fun customSingleTypeReturn() {
		val symbol = TypeIrMapper.createSymbol("myVar: CustomType", "TestSuite")
		println(symbol)
		assert(symbol.type is QUnknownType)
	}

}