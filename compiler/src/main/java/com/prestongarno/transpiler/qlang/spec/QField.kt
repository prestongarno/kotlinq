package com.prestongarno.transpiler.qlang.spec

import com.prestongarno.ktq.*
import com.squareup.kotlinpoet.*
import java.util.*
import kotlin.reflect.KClass

class QField(name: String,
    var type: QDefinedType,
    var args: List<QFieldInputArg>,
    var directive: QDirectiveSymbol,
    var isList: Boolean = false,
    var nullable: Boolean = false)
  : QSchemaType<Pair<PropertySpec, Optional<TypeSpec>>>(name) {

  enum class BuilderStatus {
    NONE,
    ENCLOSING,
    TOP_LEVEL
  }

  var builderStatus: BuilderStatus
    get() = field
  var abstract: Boolean = false
    get() = field

  init {
    builderStatus = if (args.isEmpty()) BuilderStatus.NONE else BuilderStatus.ENCLOSING
  }

  fun abstract(abstract: Boolean) = apply { this.abstract = abstract }
  fun flag(status: BuilderStatus) = apply { this.builderStatus = status }

  var inheritedFrom = mutableListOf<QInterfaceDef>()

  override fun toKotlin(): Pair<PropertySpec, Optional<TypeSpec>> {
    if (this.kotlinSpec != null) return kotlinSpec!!

    val typeName = determineTypeName(this)
    val rawTypeName = if (this.args.isEmpty()) {
      if (this.type is QScalarType || this.type is QEnumDef)
        ParameterizedTypeName.get(ClassName.bestGuess("${Stub::class.simpleName}"), ClassName.bestGuess(this.type.name))
      else
        ParameterizedTypeName.get(ClassName.bestGuess("${InitStub::class.simpleName}"), typeName)
    } else {
      val configName =
          if (this.type is QScalarType)
            Config::class.simpleName
          else
            ConfigType::class.simpleName
      ParameterizedTypeName.get(ClassName.bestGuess("$configName"), typeName,
          if (abstract) {
            // TOP_LEVEL on abstract means that it's a Base... name, enclosing means on iface,
            when (builderStatus) {
              BuilderStatus.NONE -> throw IllegalStateException()
              BuilderStatus.ENCLOSING -> ClassName.bestGuess(inputBuilderClassName(name))
              BuilderStatus.TOP_LEVEL -> ClassName.bestGuess("Base" + inputBuilderClassName(name))
            }
          } else {
            // concrete -> needs to declare type if TOP_LEVEL as enclosing (inherited from Base_)
            when (builderStatus) {
              BuilderStatus.NONE -> throw IllegalStateException()
              BuilderStatus.ENCLOSING ->
                if (inheritedFrom.isEmpty() || inheritedFrom[0].fieldMap[name]!!.builderStatus == BuilderStatus.TOP_LEVEL)
                  ClassName.bestGuess(inputBuilderClassName(name))
                else
                  ClassName.bestGuess(inheritedFrom[0].name).nestedClass(inputBuilderClassName(name))
              BuilderStatus.TOP_LEVEL -> if (inheritedFrom.isEmpty()) throw IllegalStateException()
              else inputBuilderClassName(name).let { ClassName.bestGuess(it) }
            }
          }
      )
    }
    val complex = if (type !is QScalarType && type !is QEnumDef) TypeArgBuilder::class else ArgBuilder::class
    val argBuilderOpt = // This can be cleaned up
        if (this.args.isEmpty())
          Optional.empty()
        else if (this.inheritedFrom.isEmpty() || abstract && builderStatus != BuilderStatus.TOP_LEVEL)
          Optional.of(buildArgBuilder(this, complex, ClassName.bestGuess(inputBuilderClassName(this.name))).build())
        else (checkSuper())

    val spec = PropertySpec.builder(this.name, rawTypeName)
    if (!abstract) {
      spec.delegate(
          if (args.isEmpty()) lazyInitializerNoConfig()
          else
            lazyInitializerConfig(
                if (argBuilderOpt.isPresent)
                  ClassName.bestGuess(argBuilderOpt.get().name?: throw IllegalStateException("report this"))
                else
                  ClassName.bestGuess(inheritedFrom[0].name).nestedClass(inputBuilderClassName(this.name))
            )).also {
        if (inheritedFrom.isNotEmpty())
          it.addModifiers(KModifier.OVERRIDE)
      }
    }
    this.kotlinSpec = Pair(spec.build(), argBuilderOpt)
    return this.kotlinSpec!!
  }

  private fun checkSuper(): Optional<TypeSpec> {
    val superField = inheritedFrom[0].fieldMap[name] ?: throw IllegalStateException()
    require(this.builderStatus == superField.builderStatus)
    return if (superField.builderStatus == BuilderStatus.TOP_LEVEL)
      Optional.of(buildArgBuilder(this, ClassName.bestGuess("Base" + inputBuilderClassName(this.name))).build())
    else Optional.empty()
  }

  private fun lazyInitializerNoConfig(): CodeBlock {
    if (abstract || args.isNotEmpty()) throw IllegalStateException()
    val rawType =
        if (type is QScalarType || type is QEnumDef)
          Stub::class
        else InitStub::class
    return CodeBlock.of(
        if (rawType == Stub::class) "lazy { stub<${this.type.name}>() }"
        else "lazy { typeStub<${this.type.name}>() }"
    )
  }

  private fun lazyInitializerConfig(argTypeName: TypeName): CodeBlock {
    if (abstract || args.isEmpty()) throw IllegalStateException("abstract=$abstract args=$args")
    val rawType =
        if (type is QScalarType || type is QEnumDef) Config::class
        else ConfigType::class
    val constructorInvocation = "${argTypeName.asNonNullable()}()"

    if (builderStatus == BuilderStatus.NONE) throw IllegalStateException()

    return CodeBlock.of(
        if (rawType == Config::class) "lazy { configStub<${this.type.name}, ${argTypeName.asNonNullable()}>($constructorInvocation) }"
        else "lazy { typeConfigStub<${this.type.name}, ${argTypeName.asNonNullable()}>($constructorInvocation) }"
    )
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

fun buildArgBuilder(field: QField, superInterface: KClass<*>, name: TypeName): TypeSpec.Builder {
  val result = TypeSpec.classBuilder(name.toString())
      .primaryConstructor(FunSpec.constructorBuilder()
          .addParameter(ParameterSpec.builder("args", superInterface)
              .defaultValue("${superInterface.simpleName}.create<${determineTypeName(field)}, $name>()")
              .build())
          .build())
      .addSuperinterface(ClassName.bestGuess(superInterface.simpleName?.replace("$".toRegex(), "_by_args")!!))
  field.args.map {
    builderTypesMethod(determineTypeName(it), it, name.toString())
  }.let { result.addFunctions(it) } // kotlinpoet bug
  return result
}

/**
 * Builds an argbuilder inheriting from a top-level class
 */
fun buildArgBuilder(field: QField, superclass: TypeName): TypeSpec.Builder {
  val rawType =
      if (field.type is QScalarType)
        ArgBuilder::class
      else
        TypeArgBuilder::class

  val argClassName = ClassName.bestGuess(inputBuilderClassName(field.name))

  val argBuilderSpec = TypeSpec.classBuilder(argClassName.toString())
      .primaryConstructor(FunSpec.constructorBuilder()
          .addParameter(ParameterSpec.builder("args", rawType)
              .defaultValue("${rawType.simpleName}.create<${determineTypeName(field)}, $argClassName>()")
              .build())
          .build())
      .superclass(superclass)
      .addSuperclassConstructorParameter(CodeBlock.of("args"))

  field.args.map {
    builderTypesMethod(determineTypeName(it), it, argClassName.toString())
  }.let { argBuilderSpec.addFunctions(it) } // kotlinpoet bug
  return argBuilderSpec
}

private fun builderTypesMethod(typeName: TypeName, param: QFieldInputArg, inputClazzName: String) =
    FunSpec.builder(param.name)
        .addParameter("value", typeName)
        .addCode(CodeBlock.builder().addStatement("return apply { addArg(\"${param.name}\", value) }\n").build())
        .returns(ClassName.bestGuess(inputClazzName))
        .build()

fun inputBuilderClassName(forField: String): String = "${forField[0].toUpperCase()}${forField.substring(1)}Args"


