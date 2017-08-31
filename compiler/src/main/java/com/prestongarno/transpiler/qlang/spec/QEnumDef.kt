package com.prestongarno.transpiler.qlang.spec

import com.prestongarno.ktq.QSchemaType
import com.squareup.kotlinpoet.TypeSpec

class QEnumDef(name: String, var options: List<String>) : QDefinedType(name) {

  override fun toKotlin(): TypeSpec {
    if(this.kotlinSpec == null) {
      val spec = TypeSpec.enumBuilder(name)
          .addSuperinterface(QSchemaType::class)
      options.forEach { str -> spec.addEnumConstant(str) }
      this.kotlinSpec = spec.build()
    }
    return this.kotlinSpec!!
  }
}