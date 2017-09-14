package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.FieldAdapter

open class QModel<out T : QSchemaType>(val model: T) {

  internal val fields = mutableListOf<FieldAdapter>()

  override fun toString() = this.toGraphql()

  fun toGraphql(indentation: Int = 0): String {
    return ((fields.joinToString(separator = ",\n") { it.toRawPayload() }
        .indent(1)) + "\n}").prepend("{\n").indent(indentation)
  }

}

fun String.indent(times: Int = 1): String =
    replace("^".toRegex(), Jsonify.INDENT.repeat(times))
        .replace("\\n".toRegex(), ("\n${Jsonify.INDENT.repeat(times)}"))

fun String.prepend(of: String): String = of + this

