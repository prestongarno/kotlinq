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
             var nullable: Boolean = false,
             comment: String = "")
  : QSchemaType<Pair<PropertySpec, Optional<TypeSpec>>>(name) {

  init {
    super.description = comment
  }

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
    val rawTypeName =
        if (this.args.isEmpty()) {
          val stubType : KClass<*> =
              if (!isList) {
                if (this.type is QScalarType || this.type is QEnumDef)
                  Stub::class
                else
                  InitStub::class
              } else {
                if (this.type is QScalarType || this.type is QEnumDef)
                  ListStub::class
                else {
                  ListInitStub::class }
              }

          ParameterizedTypeName.get(ClassName.bestGuess("${stubType.simpleName}"), typeName)

        } else {
          val configName =
              if (!isList) {
                if (this.type is QScalarType || this.type is QEnumDef)
                  QConfigStub::class.simpleName
                else
                  QTypeConfigStub::class.simpleName
              } else {
                if (this.type is QScalarType || this.type is QEnumDef)
                  ListConfig::class.simpleName
                else ListConfigType::class.simpleName
              }
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
    val complex = determineArgBuilderType(this)
    val argBuilderSpec = // This can be cleaned up
        if (this.args.isEmpty()) {
          Optional.empty()
        } else if (this.inheritedFrom.isEmpty() || abstract && builderStatus != BuilderStatus.TOP_LEVEL) {
          Optional.of(buildArgBuilder(this, complex, ClassName.bestGuess(inputBuilderClassName(this.name))).build())
        } else {
          (checkSuper())
        }

    val spec = PropertySpec.builder(this.name, rawTypeName)
    if(description.isNotEmpty())
      spec.addKdoc(CodeBlock.of(description, "%W"))
    if (!abstract) {
      spec.delegate(
          if (args.isEmpty()) {
            initializerNoConfig()
          } else {
            initializerConfig(
                if (argBuilderSpec.isPresent)
                  ClassName.bestGuess(argBuilderSpec.get().name ?: throw IllegalStateException("report this"))
                else
                  ClassName.bestGuess(inheritedFrom[0].name).nestedClass(inputBuilderClassName(this.name)))
          })
    }
    if (inheritedFrom.isNotEmpty())
      spec.addModifiers(KModifier.OVERRIDE)
    this.kotlinSpec = Pair(spec.build(), argBuilderSpec)
    return this.kotlinSpec!!
  }

  private fun checkSuper(): Optional<TypeSpec> {
    val superField = inheritedFrom[0].fieldMap[name] ?: throw IllegalStateException()
    require(this.builderStatus == superField.builderStatus)
    return if (superField.builderStatus == BuilderStatus.TOP_LEVEL)
      Optional.of(buildArgBuilder(this, ClassName.bestGuess("Base" + inputBuilderClassName(this.name))).build())
    else Optional.empty()
  }

  private fun initializerNoConfig(): CodeBlock {
    if (abstract || args.isNotEmpty()) throw IllegalStateException()
    return CodeBlock.of("${getStubTargetInvoke(this)}()")
  }

  private fun initializerConfig(argTypeName: TypeName): CodeBlock {
    if (abstract || args.isEmpty()) throw IllegalStateException("abstract=$abstract args=$args")

    val constructorInvocation = "${argTypeName.asNonNullable()}(it)"
    if (builderStatus == BuilderStatus.NONE) throw IllegalStateException()
    return CodeBlock.of("${getStubTargetInvoke(this)} { $constructorInvocation }"
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

fun inputBuilderClassName(forField: String): String = "${forField[0].toUpperCase()}${forField.substring(1)}Args"

fun buildArgBuilder(field: QField, superInterface: KClass<*>, name: TypeName): TypeSpec.Builder {
  val result = TypeSpec.classBuilder(name.toString())
      .primaryConstructor(FunSpec.constructorBuilder()
          .addParameter(ParameterSpec.builder("args", superInterface)
              //.defaultValue("${superInterface.simpleName}.create<${determineTypeName(field)}, $name>()")
              .build())
          .build())
      .addSuperinterface(ClassName.bestGuess(superInterface.simpleName?.replace("$".toRegex(), "_by_args")!!))
  field.args.map {
    builderTypesMethod(determineTypeName(it), it, name.toString())
  }.let { result.addFunctions(it) } // kotlinpoet bug
  return result
}

/**
 * Builds an argbuilder inheriting from a top-level class denoted by `superclass`
 * @param field the field to build the argbuilder for
 * @param superclass the supertype of the argbuilder class
 */
fun buildArgBuilder(field: QField, superclass: TypeName): TypeSpec.Builder {
  val rawType = determineArgBuilderType(field)
  val argClassName = ClassName.bestGuess(inputBuilderClassName(field.name))

  val argBuilderSpec = TypeSpec.classBuilder(argClassName.toString())
      .primaryConstructor(FunSpec.constructorBuilder()
          .addParameter(ParameterSpec.builder("args", rawType)
              //.defaultValue("${rawType.simpleName}.create<${determineTypeName(field)}, $argClassName>()")
              .build())
          .build())
      .superclass(superclass)
      .addSuperclassConstructorParameter(CodeBlock.of("args"))

  field.args.map {
    builderTypesMethod(determineTypeName(it), it, argClassName.toString())
  }.let { argBuilderSpec.addFunctions(it) } // kotlinpoet bug
  return argBuilderSpec
}

private fun determineArgBuilderType(field: QField): KClass<*> =
    if (!field.isList) {
      if (field.type is QScalarType)
        ArgBuilder::class
      else
        TypeArgBuilder::class
    } else {
      if (field.type is QScalarType)
        ListArgBuilder::class
      else
        TypeListArgBuilder::class
    }

private fun getStubTargetInvoke(field: QField): String =
    (if (!field.isList) {
      if (field.type is QScalarType || field.type is QEnumDef) {
        "QScalar"
      } else "QType"
    } else {
      if (field.type is QScalarType || field.type is QEnumDef) {
        "QScalarList"
      } else com.prestongarno.ktq.QSchemaType.QTypeList::class.simpleName
    }) +
        "." +
        if (field.args.isNotEmpty())
          "configStub"
        else "stub"

private fun builderTypesMethod(typeName: TypeName, param: QFieldInputArg, inputClazzName: String) =
    FunSpec.builder(param.name)
        .addParameter("value", typeName)
        .addCode(CodeBlock.builder().addStatement("return apply { addArg(\"${param.name}\", value) }\n").build())
        .returns(ClassName.bestGuess(inputClazzName))
        .build()


