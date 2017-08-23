package com.prestongarno.transpiler.qlang.spec

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.util.*
import kotlin.reflect.KClass

/** The base class for all components of the compilation
 */
abstract class QSchemaType<E>(var name: String) {

  var kotlinSpec: E? = null // sue me

  var description: String = ""

  override fun toString(): String {
    return "'$name' (${this::class.simpleName})"
  }
}

sealed class QScalarType(name: String, val clazz: KClass<*>) : QDefinedType(name)

class QCustomScalarType(name: String) : QScalarType(name, String::class)

class QInt(val defValue: Int = 0) : QScalarType("Int", Int::class)

class QFloat(val defValue: Float = 0f) : QScalarType("Float", Float::class)

class QBool(val defValue: Boolean = false) : QScalarType("Boolean", Boolean::class)

class QString(val defValue: String = "") : QScalarType("String", String::class)

class QCustomScalar(defValue: String = "") : QScalarType("Scalar", String::class)

class QField(name: String,
    var type: QDefinedType,
    var args: List<QFieldInputArg>,
    var directive: QDirectiveSymbol,
    var isList: Boolean = false,
    var nullable: Boolean = false)
  : QSchemaType<Pair<PropertySpec, Optional<TypeSpec>>>(name) {
  var inheritedFrom = mutableListOf<QInterfaceDef>()
}

class QFieldInputArg(name: String,
    var type: QDefinedType,
    var defaultValue: String = "",
    var isList: Boolean = false,
    var nullable: Boolean = true)
  : QSchemaType<FunSpec>(name)

/**
 * Base class for all "types" defined by the schema
 */
abstract class QDefinedType(name: String) : QSchemaType<TypeSpec>(name)

abstract class QStatefulType(name: String, val fields: List<QField>) : QDefinedType(name)

/** Type definition class
 */
class QTypeDef(name: String, var interfaces: List<QInterfaceDef>, fields: List<QField>) : QStatefulType(name, fields)

class QUnknownType(name: String) : QDefinedType(name)

class QUnknownInterface(name: String) : QInterfaceDef(name, emptyList())

open class QInterfaceDef(name: String, fields: List<QField>) : QStatefulType(name, fields)

class QUnionTypeDef(name: String, var possibleTypes: List<QDefinedType>) : QDefinedType(name)

class QEnumDef(name: String, var options: List<String>) : QDefinedType(name)

class QInputType(name: String, fields: List<QField>) : QStatefulType(name, fields)

class QDirectiveType(name: String, val arg: String) : QDefinedType(name)

class QDirectiveSymbol(type: QDefinedType, val value: String) : QSchemaType<Any>(type.name) {
  companion object {
    val default = QDirectiveSymbol(QUnknownType(""), "")
  }
}
