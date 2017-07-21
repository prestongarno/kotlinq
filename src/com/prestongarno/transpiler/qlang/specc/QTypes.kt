package com.prestongarno.transpiler.qlang.specc

import com.prestongarno.transpiler.qlang.specc.Scalar.*
import javax.lang.model.type.PrimitiveType
import kotlin.reflect.KClass

fun main(args : Array<String>) {
	val nullableString = QString("MyString")
	val nonNullableString = QString("OtherString", "defaultValue")
	nonNullableString.nullable = true
	println("$nullableString\n$nonNullableString")
}

/**
 * The base class for all components of the compilation
 */
abstract class QType(val getName: String) {

	var nullable: Boolean = false

	var description: String = ""

	override fun toString(): String {
		return "QType(getName='$getName', nullable=$nullable)"
	}
}

//========================================//
// Primitive types

enum class Scalar(val type: KClass<*>) {
	INT(Int::class),
	FLOAT(Float::class),
	BOOL(Boolean::class),
	STRING(String::class)
}

abstract class QPrimitive<out V>(getName: String, primitiveType: Scalar, defValue: V) : QType(getName)

class QInt(getName: String, defValue: Int = 0) : QPrimitive<Int>(getName, INT, defValue)

class QFloat(getName: String, defValue: Float = 0f) : QPrimitive<Float>(getName, FLOAT, defValue)

class QBool(getName: String, defValue: Boolean = false) : QPrimitive<Boolean>(getName, BOOL, defValue)

class QString(getName: String, defValue: String = "") : QPrimitive<String>(getName, STRING, defValue)

//========================================//
// Type definitions

/**
 * Base class for all "types" defined by the schema
 */
abstract class QDefinedType(getName: String) : QType(getName) {
	var isAbstract: Boolean = false
}

class QTypeDef(getName: String, val interfaces: MutableList<QInterfaceDef>, val uniqueFields: MutableList<QType>) : QDefinedType(getName)

class QInterfaceDef(getName: String) : QDefinedType(getName)

class QUnionTypeDef(getName: String, val possibleTypes:MutableList<out QTypeDef>) : QDefinedType(getName)

class QEnumDef(getName: String, val options:MutableList<String>) : QDefinedType(getName)

/**
 * Special type used for fields to represent a List/Collection of objects or primitives as fields in a type instance
 */
class QCollectionType(getName: String, val collectionType: QType) : QDefinedType(getName)


//========================================//
// Query types and their components

/** The base class for all query types
 */
class QQueryType(getName: String, queryArgs: MutableList<in QQArgumentType>) : QType(getName)

/** Argument types are parameters which can be passed to queries.
 */
open class QQArgumentType(getName: String, argumentType: QType) : QType(getName)

/** Primitive argument to a query
 */
class QQPrimitiveArgument(getName: String, argType: QPrimitive<Scalar>) : QQArgumentType(getName, argType)

/** Type argument to a query
 */
class QQTypeArgument(getName: String, argType: QDefinedType)
















