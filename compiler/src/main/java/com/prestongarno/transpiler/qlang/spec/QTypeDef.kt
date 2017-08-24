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
    if (kotlinSpec != null)
      return kotlinSpec!!

    val result = TypeSpec.objectBuilder(this.name)
        .addSuperinterface(QType::class)
        .addSuperinterfaces(this.interfaces.map { ClassName.bestGuess(it.name) })
    this.fields.map {
      //createProperty(it)
      it.toKotlin().first
    }.also { result.addProperties(it) }

    this.fields.filter {
      it.getStatus() != QField.BuilderStatus.NONE && it.toKotlin().second.isPresent
    }.forEach { result.addType(it.toKotlin().second.get()) }

    return result.build()
  }

  fun createProperty(field: QField): PropertySpec {
    val type: ParameterizedTypeName = getPropertyType(field)
    val result = PropertySpec.builder(field.name, type)
        .delegate(CodeBlock.of(" lazy { ${initFunctionCall(field, type)} } "))
    if (field.inheritedFrom.isNotEmpty())
      result.addModifiers(KModifier.OVERRIDE)
    return result.build()
  }

  private fun initFunctionCall(field: QField, type: ParameterizedTypeName): String {
    val paramType = bestGuess(field.type.name)
    return when (type.rawType.asNonNullable().simpleName()) {
      "${Stub::class.simpleName}" -> "stub<$paramType>()"
      "${InitStub::class.simpleName}" -> "typeStub<$paramType>()"
      "${Config::class.simpleName}" -> "configStub<$paramType, ${inputClazzTypeName(field)}>(${inputClazzTypeName(field)}())"
      "${ConfigType::class.simpleName}" -> "typeConfigStub<$paramType, ${inputClazzTypeName(field)}>(${inputClazzTypeName(field)}())"
      else -> throw IllegalStateException("unexpected stub type '$type")
    }
  }

  private fun getPropertyType(field: QField): ParameterizedTypeName =
      if (field.args.isEmpty())
        noConfigStubName(field)
      else
        configStubName(field)

  private fun noConfigStubName(field: QField): ParameterizedTypeName =
      if (field.type is QScalarType || field.type is QEnumDef) ParameterizedTypeName.get(
          bestGuess("${Stub::class.simpleName}"),
          bestGuess(field.type.name))
      else ParameterizedTypeName.get(
          bestGuess("${InitStub::class.simpleName}"),
          determineTypeName(field))

  private fun configStubName(field: QField): ParameterizedTypeName {
    val inputArgTypeName = inputClazzTypeName(field)
    val clazz = if (field.type is QScalarType || field.type is QEnumDef)
      Config::class.simpleName
    else
      ConfigType::class.simpleName
    return ParameterizedTypeName.get(bestGuess("$clazz"), bestGuess(field.type.name), inputArgTypeName)
  }

  /** This figures out if the field is an inherited field from an interface with required nested Arg/TypeArgBuilder type name
   *
   * Name the top-level superclass FIELD_NAME + BASE
   */

  override fun equals(other: Any?): Boolean = this === other
  override fun hashCode(): Int {
    return interfaces.hashCode()
  }
}

