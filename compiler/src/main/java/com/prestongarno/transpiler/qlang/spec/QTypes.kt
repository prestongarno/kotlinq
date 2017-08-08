package com.prestongarno.transpiler.qlang.spec

import java.util.*
import java.util.function.Function
import java.util.stream.Collectors
import kotlin.reflect.KClass

/**
 * The base class for all components of the compilation
 */
abstract class QType(var name: String) {

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
	ID("ID"),
	UNKNOWN("");

	companion object matcher {
		private val values: Map<String, Scalar>

		init { values = Arrays.stream(enumValues<Scalar>()).collect(Collectors.toMap({ t -> t.token }, { t -> t })) }

		fun match(keyword: String): Scalar = values[keyword] ?: UNKNOWN

		fun getType(type: Scalar) : QScalarType = when(type) {
			INT -> intType
			FLOAT -> floatType
			BOOL -> boolType
			STRING -> stringType
			ID -> stringType
			UNKNOWN -> customType
		}

		private val intType = QInt();
		private val floatType = QFloat();
		private val boolType = QBool();
		private val stringType = QString();
		private val customType = QCustomScalar();
	}
}

sealed class QScalarType(name: String, val clazz: KClass<*>) : QDefinedType(name)

class QCustomScalarType(name: String) : QScalarType(name, Int::class)

class QInt(defValue: Int = 0) : QScalarType("Int", Int::class)

class QFloat(defValue: Float = 0f) : QScalarType("Float", Float::class)

class QBool(defValue: Boolean = false) : QScalarType("Boolean", Boolean::class)

class QString(defValue: String = "") : QScalarType("String", String::class)

class QCustomScalar(defValue: String = "") : QScalarType("Scalar", String::class)

/** Symbol/field types */
abstract class QSymbol(name: String, var type: QDefinedType, val args: List<QSymbol>, val isList: Boolean = false, val nullable: Boolean = true) : QType(name)


class QField(name: String, type: QDefinedType, args: List<QFieldInputArg>, val directive: QDirectiveSymbol, isList: Boolean, nullable: Boolean)
	: QSymbol(name, type, args, isList, nullable)

class QFieldInputArg(name: String, type: QDefinedType, val defaultValue : String = "", isList: Boolean, nullable: Boolean)
	: QSymbol(name, type, Collections.emptyList(), isList, nullable)


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

class QEnumDef(name: String, var options: List<String>) : QDefinedType(name)

class QInputType(name: String, fields: List<QSymbol>) : QStatefulType(name, fields)

class QDirectiveType(name: String, val arg: String): QDefinedType(name)

class QDirectiveSymbol(type: QDefinedType, val value: String) : QSymbol("DIRECTIVE", type, Collections.emptyList()) {
	companion object {
		val default = QDirectiveSymbol(QUnknownType(""), "")
	}
}
