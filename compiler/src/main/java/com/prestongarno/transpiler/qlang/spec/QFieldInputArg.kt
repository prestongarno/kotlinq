package com.prestongarno.transpiler.qlang.spec

import com.squareup.kotlinpoet.FunSpec

class QFieldInputArg(name: String,
    var type: QDefinedType,
    var defaultValue: String = "",
    var isList: Boolean = false,
    var nullable: Boolean = true)
  : QSchemaType<FunSpec>(name) {
  override fun toKotlin(): FunSpec {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}