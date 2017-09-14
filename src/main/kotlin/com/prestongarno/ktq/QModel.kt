package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.FieldAdapter
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties

open class QModel<out T : QSchemaType>(val model: T) {

  internal val fields = mutableListOf<FieldAdapter>()

  override fun toString() = this.toGraphql()

  fun toGraphql(indentation: Int = 0): String {
    return ((fields.joinToString(separator = ",\n") { it.toRawPayload() }
        .indent(1)) + "\n}").prepend("{\n").indent(indentation)
  }

  internal fun allGraphQl() : String {
    model::class.declaredMemberProperties.map { it.call(model) as FieldAdapter }
        .forEach { if(!fields.contains(it)) fields.add(it) }
    return toGraphql()
  }

}

fun String.indent(times: Int = 1): String =
    replace("^".toRegex(), Jsonify.INDENT.repeat(times))
        .replace("\\n".toRegex(), ("\n${Jsonify.INDENT.repeat(times)}"))

fun String.prepend(of: String): String = of + this
