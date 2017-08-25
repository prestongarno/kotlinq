package com.prestongarno.transpiler.qlang.spec

import com.prestongarno.ktq.QType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.prestongarno.ktq.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ClassName.Companion.bestGuess

/** Type definition class
 *
 */
class QTypeDef(name: String, var interfaces: List<QInterfaceDef>, fields: List<QField>) : QStatefulType(name, fields) {

  override fun toKotlin(): TypeSpec {
    return createType()
  }

  private fun createType(): TypeSpec {
    val result = TypeSpec.objectBuilder(this.name)
        .addSuperinterface(QType::class)
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

