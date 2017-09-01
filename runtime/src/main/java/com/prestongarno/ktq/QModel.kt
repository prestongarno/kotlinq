package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.FieldAdapter
import kotlin.reflect.KClass
import kotlin.reflect.KProperty0
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

abstract class QModel<out T : QSchemaType>(of: KClass<T>) {
  protected val model: T = of.objectInstance!!

  init {
    model::class.memberProperties.map { it.annotations.joinToString(", ", it.name) }.map { println(it) }
  }

  internal val fields = mutableListOf<FieldAdapter>()
  override fun toString(): String {
    return this.toJson(0)
  }

  internal fun toJson(indentation: Int = 0): String {
    return ( "{\n".indent(indentation) + (fields.map { it.toRawPayload() }
        .joinToString(separator = ",\n")
        .indent(indentation + 1))+ "\n}".indent(indentation) )
  }

}
fun String.indent(times: Int = 1): String =
    replace("^".toRegex(), Jsonify.INDENT.repeat(times))
        .replace("\\n".toRegex(), ("\n${Jsonify.INDENT.repeat(times)}"))

