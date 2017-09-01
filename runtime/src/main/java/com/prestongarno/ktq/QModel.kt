package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.FieldAdapter
import kotlin.reflect.KClass

abstract class QModel<out T : QSchemaType>(of: KClass<T>) {
  protected val model: T = of.objectInstance!!

  internal val fields = mutableListOf<FieldAdapter>()
  override fun toString(): String {
    return this.toGraphql(0)
  }

  fun toGraphql(indentation: Int = 0): String {
    return ( "{\n".indent(indentation) + (fields.map { it.toRawPayload() }
        .joinToString(separator = ",\n")
        .indent(indentation + 1))+ "\n}".indent(indentation) )
  }

}
fun String.indent(times: Int = 1): String =
    replace("^".toRegex(), Jsonify.INDENT.repeat(times))
        .replace("\\n".toRegex(), ("\n${Jsonify.INDENT.repeat(times)}"))

