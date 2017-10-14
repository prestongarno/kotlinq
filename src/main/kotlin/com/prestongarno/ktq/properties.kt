package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.internal.ModelProvider
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.jvm.jvmErasure


interface Property {
  val graphqlType: PropertyType
  val typeName: String
  val fieldName: String
  val isList: Boolean
  @Deprecated("Remove reflection pls") val kproperty: KProperty<*>

  fun toEnum(name: String): Enum<*>?

  companion object {
    fun from(property: KProperty<*>, typeName: String, isList: Boolean): Property = PropertyImpl(typeName, property, isList)

    internal val ROOT = object : Property {
      override val kproperty: KProperty<*> = this::fieldName
      override val graphqlType = PropertyType.OBJECT
      override val typeName = ""
      override val fieldName: String = ""
      override val isList: Boolean = false
      override fun toEnum(name: String): Enum<*>? = null
    }
  }
}

internal class PropertyImpl(override val typeName: String, property: KProperty<*>, override val isList: Boolean) : Property {
  override val graphqlType: PropertyType = PropertyType.from(typeName)
  override val fieldName: String = property.name
  override val kproperty: KProperty<*> = property

  override fun toEnum(name: String): Enum<*>? {
    return if (kproperty.returnType.jvmErasure.java.isEnum)
      kproperty.returnType.jvmErasure.javaObjectType.enumConstants.find {
        (it as Enum<*>).name == name
      } as? Enum<*>
    else null
  }

  override fun equals(other: Any?): Boolean {
    return (other as? Property)?.kproperty == kproperty
  }

  override fun hashCode(): Int {
    var result = kproperty.hashCode()
    result = 31 * result + typeName.hashCode()
    result = 31 * result + graphqlType.hashCode()
    result = 31 * result + fieldName.hashCode()
    result = 31 * result + isList.hashCode()
    return result
  }
}

enum class PropertyType {
    INT,
    BOOLEAN,
    STRING,
    FLOAT,
    ENUM,
    OBJECT,
    CUSTOM_SCALAR;

    companion object {
        fun from(name: String): PropertyType = all[name]?: OBJECT

        private val all = PropertyType.values().map { Pair(it.name, it) }.toMap()
      }
  }

internal fun KProperty<*>.typedValueFrom(value: Any): Any? {
  return if (this.returnType.jvmErasure == value::class)
    value
  else when (this.returnType.jvmErasure) {
    Int::class -> "$value".toIntOrNull()
    Boolean::class -> "$value".toBoolean()
    Float::class -> "$value".toFloatOrNull()
    String::class -> "$value"
    else -> {
      if (returnType.jvmErasure.java.isEnum)
        returnType.jvmErasure.javaObjectType.enumConstants.find {
          (it as Enum<*>).name == "$value"
        }
      else null
    }
  }
}

internal fun KProperty<*>.typedListValueFrom(value: Any): List<Any> {
  val type: KClass<*>? = returnType.arguments[0].type?.classifier as KClass<*>
  val values = (value as? List<*>)?.filterNotNull() ?: listOf(value)
  val responseType: KClass<*> = if (values.isNotEmpty()) values[0]::class else Any::class

  return when (type) {
  // TODO Take out generic type arguments from the stubs for primitives -> these are BOXED AND UNBOXED every time they're accessed!
    null -> emptyList()
    responseType -> values
    Int::class -> values.mapNotNull { "$it".toIntOrNull() }
    Boolean::class -> values.map { "$it".toBoolean() }
    Float::class -> values.mapNotNull { "$it".toFloatOrNull() }
    String::class -> values.map { "$it" }
    else -> {
      if (type.java.isEnum) {
        values.mapNotNull {
          type.java.enumConstants.find { (it as Enum<*>).name == "$it" }
        }
      } else {
        emptyList()
      }
    }
  }
}


class DispatchQueue {
  private var value: QSchemaUnion? = null

  internal fun put(value: QSchemaUnion) { this.value = value}

  internal fun pop() {
    value = null
  }

  fun get() = value
}

fun String.indent(times: Int = 1): String =
    replace("^".toRegex(), Jsonify.INDENT.repeat(times))
        .replace("\\n".toRegex(), ("\n${Jsonify.INDENT.repeat(times)}"))

fun String.prepend(of: String): String = of + this

internal fun QModel<*>.prettyPrinted(indentation: Int): String =
    if (model is QSchemaUnion) prettyPrintUnion(indentation) else
      ((fields.joinToString(separator = ",\n") { it.prettyPrinted() }
          .indent(1)) + "\n}").prepend("{\n").indent(indentation)
          .replace("\\s*([(,])".toRegex(), "$1").trim()

internal fun QModel<*>.prettyPrintUnion(indentation: Int) =
    (fields.joinToString(separator = ",\n", prefix = "{\n".indent(indentation)) {
      it.prettyPrinted().prepend("... fragment ")
    }.indent(1)
        .plus("\n}")
        .indent(indentation))
        .replace("\\s*([(,])".toRegex(), "$1").trim()

internal fun Adapter.prettyPrinted(): String = property.fieldName +
    (when {
      args.isNotEmpty() -> args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") {
            "${it.key}: ${formatAs(it.value)}" }
      this is ModelProvider -> getModel().toGraphql()
      else -> ""
    }).replace("\\s*([(,])".toRegex(), "$1").trim()

