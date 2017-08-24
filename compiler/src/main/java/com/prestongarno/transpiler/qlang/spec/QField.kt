package com.prestongarno.transpiler.qlang.spec

import com.prestongarno.ktq.Config
import com.prestongarno.ktq.ConfigType
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.Stub
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.util.*

class QField(name: String,
    var type: QDefinedType,
    var args: List<QFieldInputArg>,
    var directive: QDirectiveSymbol,
    var isList: Boolean = false,
    var nullable: Boolean = false)
  : QSchemaType<Pair<PropertySpec, Optional<TypeSpec>>>(name) {

  var inheritedFrom = mutableListOf<QInterfaceDef>()

  fun setKotlinSpec(to: Pair<PropertySpec, Optional<TypeSpec>>) = apply { this.kotlinSpec = to }

  override fun toKotlin(): Pair<PropertySpec, Optional<TypeSpec>> {
    val typeName = determineTypeName(this)
    val rawTypeName = if (this.args.isEmpty()) {
      if (this.type is QScalarType || this.type is QEnumDef)
        ParameterizedTypeName.get(ClassName.bestGuess("${Stub::class.simpleName}"), ClassName.bestGuess(this.type.name))
      else
        ParameterizedTypeName.get(ClassName.bestGuess("${InitStub::class.simpleName}"), typeName)
    } else {
      val inputTypeName = ClassName.bestGuess(inputBuilderClassName(this.name))
      val configName =
          if (this.type is QScalarType)
            Config::class.simpleName
          else
            ConfigType::class.simpleName
      ParameterizedTypeName.get(ClassName.bestGuess("$configName"), typeName, inputTypeName)
    }
    return Pair(PropertySpec.builder(this.name, rawTypeName).build(),
        if (this.args.isNotEmpty())
          Optional.of(buildArgBuilder(this).build())
        else
          Optional.empty())
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as QField

    if (type != other.type) return false
    if (isList != other.isList) return false
    if (nullable != other.nullable) return false

    return true
  }

  override fun hashCode(): Int {
    var result = type.hashCode()
    result = 31 * result + args.hashCode()
    result = 31 * result + isList.hashCode()
    result = 31 * result + nullable.hashCode()
    return result
  }

}