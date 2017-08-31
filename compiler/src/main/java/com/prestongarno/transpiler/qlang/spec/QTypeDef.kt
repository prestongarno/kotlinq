package com.prestongarno.transpiler.qlang.spec

import com.prestongarno.ktq.QSchemaType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeSpec

/** Type definition class
 */
class QTypeDef(name: String, var interfaces: List<QInterfaceDef>, fields: List<QField>) : QStatefulType(name, fields) {

  override fun toKotlin(): TypeSpec {
    val result = TypeSpec.objectBuilder(this.name)
        .addSuperinterface(QSchemaType::class)
        .addSuperinterfaces(this.interfaces.map { ClassName.bestGuess(it.name) })
    this.fields.forEach{
      it.toKotlin().first.also { result.addProperty(it) }
      it.toKotlin().second.ifPresent { result.addType(it) } }
    return result.build()
  }

  override fun equals(other: Any?): Boolean = this === other
  override fun hashCode(): Int {
    return interfaces.hashCode()
  }
}

