package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.ktq.*
import com.prestongarno.transpiler.qlang.spec.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ClassName.Companion.bestGuess
import com.prestongarno.ktq.QType
import com.prestongarno.transpiler.QCompilationUnit
import com.prestongarno.transpiler.kotlin.spec.QInterfaceBuilder.Companion.determineTypeName
import com.prestongarno.transpiler.kotlin.spec.QInterfaceBuilder.Companion.buildInputArgTypes

class QTypeBuilder {

  fun createType(qType: QTypeDef, comp: QCompilationUnit): TypeSpec {

    val result = TypeSpec.objectBuilder(qType.name)
        .addSuperinterface(QType::class)
        .addSuperinterfaces(qType.interfaces.map {
          ClassName.bestGuess(it.name)
        })
    qType.fields.map {
      createProperty(it)
    }.also {
      result.addProperties(it)
    }

    qType.fields.filter {
      it.inheritedFrom.isEmpty() && it.args.isNotEmpty() && comp.confictOverrides[it] == null
    }.forEach { result.addType(buildInputArgTypes(field = it)) }

    return result.build()
  }

  private fun createProperty(field: QField): PropertySpec {
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
  private fun inputClazzTypeName(field: QField): TypeName {
    return if (field.inheritedFrom.isNotEmpty())
          bestGuess(QInterfaceBuilder.inputBuilderClassName(field.name) + "Base")
    else ClassName.bestGuess(classNameString = QInterfaceBuilder.inputBuilderClassName(field.name))
  }

}








