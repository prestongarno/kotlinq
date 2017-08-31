package com.prestongarno.transpiler.qlang.spec

import com.squareup.kotlinpoet.TypeSpec
import com.prestongarno.ktq.QSchemaType
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ClassName.Companion.bestGuess

open class QInterfaceDef(name: String, fields: List<QField>) : QStatefulType(name, fields) {

  override fun toKotlin(): TypeSpec {
    if (this.kotlinSpec == null)
      kotlinSpec = build()
    return kotlinSpec!!
  }

  private fun build(): TypeSpec = TypeSpec.interfaceBuilder(this.name)
      .addSuperinterface(QSchemaType::class)
      .addProperties(this.fields.map { field -> field.toKotlin().first })
      .addTypes(this.fields
          .filter { it.builderStatus == QField.BuilderStatus.ENCLOSING && it.toKotlin().second.isPresent }
          .map { it.toKotlin().second.get() })
      .build()
}

fun determineTypeName(f: QFieldInputArg): TypeName {
  return if (f.type is QScalarType)
    bestGuess(f.type.name)
  else
    bestGuess(f.type.name.split(".").last())
}

fun determineTypeName(f: QField): TypeName {
  return if (f.type is QScalarType)
    bestGuess(f.type.name)
  else
    bestGuess(f.type.name.split(".").last())
}

