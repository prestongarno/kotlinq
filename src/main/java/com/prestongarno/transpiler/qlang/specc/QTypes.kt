package com.prestongarno.transpiler.qlang.specc

import com.prestongarno.transpiler.qlang.specc.Scalar.*
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

/**
 * The base class for all components of the compilation
 */
abstract class QType(val name: String) {

	var description: String = ""

	override fun toString(): String {
		return "'$name' (${this::class.simpleName})"
	}
}

//========================================//
// Primitive types

enum class Scalar(val token: String) {
	INT("Int"),
	FLOAT("Float"),
	BOOL("Boolean"),
	STRING("String"),
	UNKNOWN("");

	companion object matcher {
		private val values: Map<String, Scalar>

		init {
			values = Arrays.stream(enumValues<Scalar>()).collect(Collectors.toMap(Function<Scalar, String> { t -> t.token },
					Function<Scalar, Scalar> { t -> t }))
		}

		fun match(keyword: String): Scalar = values[keyword] ?: UNKNOWN

		inline fun <reified T : QScalarSymbol<*>> buildScalarSymbol(type: Scalar, name: String, args: List<QSymbol>, nullable: Boolean = false): T {
			return when (type) {
				INT -> QIntSymbol(name, args) as T
				FLOAT -> QFloatSymbol(name, args) as T
				BOOL -> QBoolSymbol(name, args) as T
				STRING -> QStringSymbol(name, args, nullable) as T
				UNKNOWN -> throw IllegalArgumentException("No such scalar")
			}
		}
	}
}

class QScalarType(val typeEnum: Scalar, name: String) : QType(name)

sealed class QScalarSymbol<out V>(name: String, val scalarType: Scalar, args: List<QSymbol>, val defValue: V, nullable: Boolean)
	: QSymbol(name, QScalarType(scalarType, scalarType.token), args, nullable) {

	constructor(name: String, scalarType: Scalar, args: List<QSymbol>, defValue: V)
			: this(name, scalarType, args, defValue, false)

	override fun toString(): String {
		return "Q${scalarType.token}::$name::Default='$defValue'::args=${args.joinToString("\n\t", "")}"
	}
}

class QIntSymbol(name: String, args: List<QSymbol>, defValue: Int = 0) : QScalarSymbol<Int>(name, INT, args, defValue)

class QFloatSymbol(name: String, args: List<QSymbol>, defValue: Float = 0f) : QScalarSymbol<Float>(name, FLOAT, args, defValue)

class QBoolSymbol(name: String, args: List<QSymbol>, defValue: Boolean = false) : QScalarSymbol<Boolean>(name, BOOL, args, defValue)

class QStringSymbol(name: String, args: List<QSymbol>, nullable: Boolean, defValue: String = "")
	: QScalarSymbol<String>(name, STRING, args, defValue, nullable)

class QCustomScalar<out T : Any>(name: String, args: List<QSymbol>, defValue: T) : QScalarSymbol<Any>(name, STRING, args, defValue)

//========================================//
// Symbol/field types
abstract class QSymbol(name: String, var type: QType, val args: List<QSymbol>, val nullable: Boolean = true) : QType(name) {
	override fun toString(): String {
		return "${this::class.simpleName}(type=$type, args=${args.joinToString("\n\t", "")}, nullable=$nullable)"
	}
}


class QField(name: String, type: QType, args: List<QFieldInputArg>, val directive: QDirectiveSymbol, val isList: Boolean, nullable: Boolean)
	: QSymbol(name, type, args, nullable) {
	override fun toString(): String = "'$name' - type $type - List? $isList || Nullable? $nullable " +
			if (args.isNotEmpty()) "\n\t\t\targs = ${args.joinToString(",\n\t\t\t\t")}" else ""
}

class QFieldInputArg(name: String, type: QType, val defaultValue : String = "", val isList: Boolean, nullable: Boolean)
	: QSymbol(name, type, Collections.emptyList(), nullable) {
	override fun toString(): String = "Arg : $name -- type $type -- isList? $isList -- nullable? $nullable" +
			"${if(defaultValue.isNotBlank()) " -- default val = '$defaultValue'" else ""}"
}

//========================================//
// Type definitions

/**
 * Base class for all "types" defined by the schema
 */
abstract class QDefinedType(name: String) : QType(name) {
	var isAbstract: Boolean = false
}

/** Nullable QInterfaceDef pointer allowed for resolving types after creating IR objects
 */
class QTypeDef(name: String, val interfaces: List<QDefinedType>, val fields: List<QSymbol>) : QDefinedType(name) {
	override fun toString(): String {
		return "QDefinedType:: \"$name\"\n  --interfaces::\n\t\t${interfaces.joinToString("\n\t\t")}\n" +
				"  --fields::\n\t\t${fields.joinToString("\n\t\t")}"
	}
}

class QUnknownType(name: String) : QDefinedType(name)

class QInterfaceDef(name: String, val fields: List<QSymbol>) : QDefinedType(name)

class QUnionTypeDef(name: String, val possibleTypes: List<QDefinedType>) : QDefinedType(name)

class QEnumDef(name: String, val options: List<String>) : QDefinedType(name)

class QInputType(name: String, val fields: List<QSymbol>) : QDefinedType(name)

class QDirectiveType(name: String, val arg: String): QDefinedType(name)

class QDirectiveSymbol(type: QDefinedType, val value: String) : QSymbol("DIRECTIVE", type, Collections.emptyList())
