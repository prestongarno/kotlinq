package com.prestongarno.transpiler.qlang.spec

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

/**
 * Enum class representing primitive types
 */
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

		fun getType(type: Scalar) : QScalarType = when(type) {
			INT -> intType
			FLOAT -> floatType
			BOOL -> boolType
			STRING -> stringType
			else -> throw IllegalArgumentException("No such scalar type")
		}

		private val intType = QInt();
		private val floatType = QFloat();
		private val boolType = QBool();
		private val stringType = QString();
	}
}

sealed class QScalarType(name: String) : QDefinedType(name)

class QCustomScalarType(name: String) : QScalarType(name)

class QInt(defValue: Int = 0) : QScalarType("Int")

class QFloat(defValue: Float = 0f) : QScalarType("Float")

class QBool(defValue: Boolean = false) : QScalarType("Boolean")

class QString(defValue: String = "") : QScalarType("String")

/** Symbol/field types */
abstract class QSymbol(name: String, var type: QDefinedType, val args: List<QSymbol>, val nullable: Boolean = true) : QType(name)


class QField(name: String, type: QDefinedType, args: List<QFieldInputArg>, val directive: QDirectiveSymbol, val isList: Boolean, nullable: Boolean)
	: QSymbol(name, type, args, nullable)

class QFieldInputArg(name: String, type: QDefinedType, val defaultValue : String = "", val isList: Boolean, nullable: Boolean)
	: QSymbol(name, type, Collections.emptyList(), nullable)


/**
 * Base class for all "types" defined by the schema
 */
abstract class QDefinedType(name: String) : QType(name)

abstract class QStatefulType(name: String, val fields: List<QSymbol>) : QDefinedType(name)

/** Type definition class
 */
class QTypeDef(name: String, var interfaces: List<QDefinedType>, fields: List<QSymbol>) : QStatefulType(name, fields)

class QUnknownType(name: String) : QDefinedType(name)

class QInterfaceDef(name: String, fields: List<QSymbol>) : QStatefulType(name, fields)

class QUnionTypeDef(name: String, var possibleTypes: List<QDefinedType>) : QDefinedType(name)

class QEnumDef(name: String, val options: List<String>) : QDefinedType(name)

class QInputType(name: String, fields: List<QSymbol>) : QStatefulType(name, fields)

class QDirectiveType(name: String, val arg: String): QDefinedType(name)

class QDirectiveSymbol(type: QDefinedType, val value: String) : QSymbol("DIRECTIVE", type, Collections.emptyList())
