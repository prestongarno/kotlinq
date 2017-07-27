package com.prestongarno.transpiler

import com.prestongarno.transpiler.qlang.specc.*
import com.prestongarno.transpiler.qlang.specc.Scalar.*
import java.util.*


inline fun <reified T : QDefinedType> build(type: RootType, name: String, body: List<String>): T {
	return when (type) { //todo make this return a QDefinedType & encapsulate reification to this file
		RootType.TYPE -> buildTypeDef(name, body) as T
		RootType.INTERFACE -> TODO()
		RootType.UNION -> TODO()
		RootType.INPUT -> TODO()
		RootType.ENUM -> TODO()
		RootType.SCALAR -> TODO()
		RootType.UNKNOWN -> TODO()
	}
}

fun buildTypeDef(name: String, statements: List<String>): QTypeDef {
	val ifaceExtends: List<QDefinedType>
	val indexOf = name.indexOf(" ")
	val realName: String

	if (indexOf < 0) { // check for interface implementations
		realName = name.trim()
		ifaceExtends = Collections.emptyList()
	} else {
		realName = name.substring(0, indexOf)
		assert(name.substring(indexOf + 1, name.length).startsWith("implements "),
				lazyMessage = {
					"Illegal keyword at type declaration $realName" +
							"\nUsage: type [NAME] {implements [INTERFACE...]}"
				})
		val split = name.substring(indexOf, name.length).trim().split(Regex("\\s*[,|\\s*]\\s*"))
		val ifaces: MutableList<QDefinedType> = LinkedList()
		for (i in 1..split.size - 1) {
			val iface = split[i].trim()
			assert(iface.isNotEmpty(), lazyMessage = { "Illegal declaration of type implementation: $iface on type $realName" })
			ifaces.add(0, QUnknownType(iface))
		}
		ifaceExtends = ifaces.toList()
	}
	return QTypeDef(realName, ifaceExtends, statements.map { s -> createSymbol(s, realName) })
}

private fun isValidVarName(symbol: String): Boolean =
		Scalar.match(symbol) == UNKNOWN
				&& symbol.matches(Regex("[a-zA-Z_]+!?"))



/**
 * Function which takes as input a raw String which describes a field in a type kind declaration/block and returns a
 * QSymbol E.g.:
 *
 * 		fun("user(query: String): \[User])
 *
 * 	will return:
 *
 * 		QSymbol("user", QCollectionType("User", unknown), nullable = true)
 *
 * 	@param parentTypeName The parent type name of this statement, for debug/error messages
 * 	@param raw The string value of the statement
 */
fun createSymbol(raw: String, parentTypeName: String): QSymbol {
	val inputArguments: List<QSymbol>
	val nameWithArgs: String
	val name: String
	val rawType: String
	val nullable: Boolean

	/*("Declare constant regexes at the top representing different kinds of type field/variable declarations::" +
			"E.g. ones for a single field, one for a nullable field, one for a not-nullable list of nullables, " +
			"one for a nullable list, one for a not-nullable list of non-nullables, etc.")
	assert(raw.contains(Regex("[a-zA-Z]:\\s*\\[?[a-zA-Z]")),
			lazyMessage = { "Invalid field declaration '$raw' at $parentTypeName\nSyntax:\t[NAME]{INPUT_ARGS}: [TYPE]" })
	val splitIndex = raw.lastIndexOf(":")
	nameWithArgs = raw.substring(0, splitIndex)


	if (nameWithArgs.indexOf("(") < 0) assert(isValidVarName(nameWithArgs))
	else assert(isValidVarName(nameWithArgs.substring(0, nameWithArgs.indexOf("("))))

	rawType = raw.substring(raw.lastIndexOf(":") + 1, raw.length).trim()
	nullable = !rawType.endsWith("!")

	val openParens = nameWithArgs.indexOf("(") //check if the variable requires input arguments
	val closedParens = nameWithArgs.indexOf(")")
	val hasArguments = nameWithArgs.contains(Regex("[a-zA-Z]+:\\s+[a-zA-Z_!]+"))
	if (hasArguments && openParens < 0 || closedParens < 0)
		throw IllegalArgumentException("Invalid parentheses for input argument declaration: $raw \n\tdeclared in type $parentTypeName")

	val openBracket = raw.indexOf("[") > closedParens //Check if is a list type
	val closedBracket = rawType.indexOf("]")
	assert(openBracket == closedBracket, { "Unclosed bracket: $raw \n\tdeclared in type $parentTypeName" })

	name = if (openParens > 0) nameWithArgs.substring(0, openParens).trim() else nameWithArgs

	assert((hasArguments && openParens < closedParens) || (!hasArguments && openParens < 0 && closedParens < 0),
			{ "" })

	if (hasArguments) { //recursive call because it's the same syntax
		val rawArgs = raw.substring(openParens + 1, closedParens).trim().split(Regex("\\s*,\\s*"))
		inputArguments = rawArgs.map { s -> createSymbol(s, parentTypeName) }
	} else inputArguments = Collections.emptyList()

	val endTypeIndex = if (closedBracket && !nullable) rawType.length - 2
	else if (closedBracket && nullable || !nullable && !closedBracket) rawType.length - 1
	else rawType.length
	val beginTypeIndex = if (openBracket) 1 else 0
	val type = rawType.substring(beginTypeIndex, endTypeIndex).trim()

	assert(type.matches(Regex("\\[?[a-zA-Z_]+!?]")), { "Illegal declaration '$raw' in type declaration '$parentTypeName'" })

	val result: QSymbol
	// If scalar, buildScalarSymbol with type reference, else wait until resolve stage (unknown for now)
	val scalarType = Scalar.match(type)

	if (scalarType != UNKNOWN) {
		result = Scalar.buildScalarSymbol(scalarType, name, inputArguments, nullable)
	} else result = QField(name, QUnknownType(type), inputArguments, nullable)

	return if (openBracket) QCollectionType(result) else result*/
	TODO()
}

