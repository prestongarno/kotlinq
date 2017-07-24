package com.prestongarno.transpiler

import com.prestongarno.transpiler.RootType.*
import com.prestongarno.transpiler.qlang.specc.*
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors
import kotlin.collections.HashMap

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

		init {
			values = EnumSet.allOf(RootType::class.java).toList().stream()
					.collect(Collectors.toMap(Function<RootType, String> { t -> t.token },
							Function<RootType, RootType> { t -> t }))
					.toMap(HashMap<String, RootType>())
		}

		fun match(keyword: String): RootType = values[keyword] ?: UNKNOWN

	}

}

sealed class TypeIrMapper<out T : QType> {

	abstract fun build(name: String, statements: List<String>): T


	private class TypeDefMapper : TypeIrMapper<QTypeDef>() {
		override fun build(name: String, statements: List<String>): QTypeDef {
			val ifaceExtends: List<QDefinedType>
			val indexOf = name.indexOf(" ")
			val realName: String

			if (indexOf < 0) { // check for interface implementations
				realName = name
				ifaceExtends = Collections.emptyList()
			} else {
				realName = name.substring(0, indexOf)
				assert(name.substring(indexOf, name.length).startsWith("implements "),
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
	}

	companion object builder {

		fun build(type: RootType, name: String, body: List<String>): QType {
			return when (type) {
				TYPE -> typeMapperInstance.build(name, body)
				RootType.INTERFACE -> TODO()
				RootType.UNION -> TODO()
				RootType.INPUT -> TODO()
				RootType.ENUM -> TODO()
				RootType.SCALAR -> TODO()
				RootType.UNKNOWN -> TODO()
			}
		}

		private fun isValidVarName(symbol: String): Boolean = RootType.match(symbol) == UNKNOWN
				&& Scalar.match(symbol) == Scalar.UNKNOWN
				&& symbol.matches(Regex("^[a-zA-Z0-9_]*$"))
				&& !symbol.trim().isNullOrEmpty()  //todo: function to check not name of another type

		private val typeMapperInstance = TypeDefMapper()
		//private val ifaceMapperInstance =
		//private val unionMapperInstance =
		//private val inputMapperInstance =
		//private val scalarMapperInstance =
		//private val unknownMapperInstance =

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
		fun createSymbol(raw: String, parentTypeName: String): QSymbol { // Override in enum mapper
			println(raw)
			val inputArguments: List<QSymbol>
			val nameWithArgs: String
			val name: String
			val rawType: String
			val nullable: Boolean

			assert(raw.contains(Regex("[a-zA-Z]\\s*:\\s*[a-zA-Z]")),
					lazyMessage = { "Invalid field declaration '$raw'\nSyntax:\t[NAME]{INPUT_ARGS}: [TYPE]" })
			val splitIndex = raw.lastIndexOf(":")
			nameWithArgs = raw.substring(0, splitIndex)


			if (nameWithArgs.indexOf("(") < 0) assert(isValidVarName(nameWithArgs))
			else assert(isValidVarName(nameWithArgs.substring(0, nameWithArgs.indexOf("("))))

			rawType = raw.substring(raw.lastIndexOf(":") + 1, raw.length).trim()
			nullable = !rawType.endsWith("!")

			val openParens = nameWithArgs.indexOf("(") //check if the variable requires input arguments
			val closedParens = nameWithArgs.indexOf(")")
			val hasArguments = openParens > 0 && closedParens > 0

			val openBracket = raw.indexOf("[") > closedParens //Check if is a list type
			val closedBracket = rawType.endsWith("]")
			assert(openBracket == closedBracket, { "Unclosed bracket: $raw \n\tdeclared in type $parentTypeName" })

			name = if(openParens > 0) nameWithArgs.substring(0, openParens).trim() else nameWithArgs

			assert((hasArguments && openParens < closedParens) || (!hasArguments && openParens < 0 && closedParens < 0),
					{ "Invalid parentheses for input argument declaration: $raw \n\tdeclared in type $parentTypeName" })

			if (hasArguments) { //recursive call because it's the same syntax
				val rawArgs = raw.substring(openParens + 1, closedParens).trim().split(Regex("\\s*,\\s*"))
				inputArguments = rawArgs.map { s -> createSymbol(s, parentTypeName) }
			} else inputArguments = Collections.emptyList()

			val endTypeIndex = if (closedBracket && !nullable) rawType.length - 2
			else if (closedBracket && nullable || !nullable && !closedBracket) rawType.length - 1
			else rawType.length
			val beginTypeIndex = if (openBracket) 1 else 0
			val type = rawType.substring(beginTypeIndex, endTypeIndex).trim()

			assert(type.matches(Regex("^[a-zA-Z]*")), { "Illegal declaration '$raw' in type declaration '$parentTypeName'" })

			val result: QSymbol
			// If scalar, buildScalarSymbol with type reference, else wait until resolve stage (unknown for now)
			val scalarType = Scalar.match(type)

			if (scalarType != Scalar.UNKNOWN) {
				result = Scalar.buildScalarSymbol(scalarType, name, inputArguments, nullable)
			} else result = QField(name, QUnknownType(type), inputArguments, nullable)

			return if (openBracket) QCollectionType(result) else result
		}
	}
}

fun main(args: Array<String>) {
	//val result = TypeIrMapper.build(TYPE, "MyType implements TypeSuper, Query", listOf("number:Int"))
	//println(result)
	val result = TypeIrMapper.createSymbol("myfield : [Boolean", "TestObject")
	println(result)
}
