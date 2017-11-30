package com.prestongarno.ktq.compiler

import com.prestongarno.ktq.QInputType
import com.prestongarno.ktq.org.antlr4.gen.GraphQLSchemaParser
import com.prestongarno.ktq.stubs.CustomScalarListStub
import com.prestongarno.ktq.stubs.CustomScalarStub
import com.prestongarno.ktq.stubs.EnumListStub
import com.prestongarno.ktq.stubs.EnumStub
import com.prestongarno.ktq.stubs.InterfaceListStub
import com.prestongarno.ktq.stubs.InterfaceStub
import com.prestongarno.ktq.stubs.TypeListStub
import com.prestongarno.ktq.stubs.TypeStub
import com.prestongarno.ktq.stubs.UnionListStub
import com.prestongarno.ktq.stubs.UnionStub
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import org.antlr.v4.runtime.ParserRuleContext

sealed class ScopedSymbol : SymbolElement {
  abstract val nullable: Boolean
  abstract val isList: Boolean
  abstract var type: SchemaType<*>
  abstract val context: ParserRuleContext
}

data class FieldDefinition(override val context: GraphQLSchemaParser.FieldDefContext) : ScopedSymbol(), KotlinPropertyElement {


  override val name = context.fieldName().Name().text

  override val typeName = context.typeSpec()?.typeName()?.Name()?.text
      ?: context.typeSpec().listType()?.children?.get(1)?.text
      ?: throw IllegalArgumentException("Unknown type name for $name at ${context.typeSpec().text}")
  // TODO actually use the parse trees so don't have to exception on ctor^

  override val nullable = context.typeSpec().nullable() == null

  override val isList = context.typeSpec().listType() != null

  override lateinit var type: SchemaType<*>

  var isAbstract: Boolean = false

  lateinit var inheritsFrom: Set<InterfaceDef>

  /** has to be done from an outside context. Created in [GraphQLCompiler.attrInheritance]*/
  internal var argBuilder: ArgBuilderDef? = null

  val arguments: List<ArgumentDefinition> = context.fieldArgs()
      ?.argument()
      ?.map(::ArgumentDefinition) ?: emptyList()

  override fun toKotlin(): PropertySpec =
      PropertySpec.builder(
          name,
          ktqGraphQLDelegateKotlinpoetTypeName()
      ).apply {
        if (!isAbstract)
          delegate(type.getStubDelegationCall(this@FieldDefinition))
        if (inheritsFrom.isNotEmpty())
          addModifiers(KModifier.OVERRIDE)
      }.build()

  /**
   * ***Finally*** a simple and consistent API call structure for all types,
   * where all combinations of field configurations are covered. The structure
   * of the ktq type is described like this:
   *
   * [ StubType ].[ Query<T> | OptionalConfigQuery<T, A> | ConfigurableQuery<T, A> ]
   *
   *
   * ***where***
   *
   *
   * StubType: [ [String|Float|Int|Boolean]{Array}Delegate | [Type|Interface|Union|Enum|CustomScalar]{List}Stub ]
   *
   * Primitive delegates/stubs don't have a type argument.
   * Only for the the associated ArgBuilder class on the graphql primitive field
   */
  private fun ktqGraphQLDelegateKotlinpoetTypeName(): TypeName {

    fun FieldDefinition.configurationTypeClassName(): String = when {
      arguments.isEmpty() -> "Query"
      arguments.isNotEmpty() && arguments.find { it.nullable == false } == null -> "OptionalConfigQuery"
      else -> "ConfigurableQuery"
    }

    fun `type name for non-collection field`() = when (type) {
      is EnumDef -> EnumStub::class
      is InterfaceDef -> InterfaceStub::class
      is ScalarDef -> CustomScalarStub::class
      is InputDef -> QInputType::class
      is TypeDef -> TypeStub::class
      is UnionDef -> UnionStub::class
      is ScalarType -> ScalarPrimitives.named[type.name]!!.typeDef.stubClass
    }

    fun `type name for list field`() = when (type) {
      is EnumDef -> EnumListStub::class
      is InterfaceDef -> InterfaceListStub::class
      is ScalarDef -> CustomScalarListStub::class
      is InputDef -> QInputType::class
      is TypeDef -> TypeListStub::class
      is UnionDef -> UnionListStub::class
      is ScalarType -> ScalarPrimitives.named[type.name]!!.typeDef.arrayStubClass
    }
    // hell kotlinpoet why do you try to be so helpful with imports
    val baseTypeName = (if (isList) `type name for list field`() else `type name for non-collection field`())
        .asTypeName()
        .nestedClass(configurationTypeClassName())

    fun FieldDefinition.argBuilderTypeName(): TypeName {
      require(arguments.isNotEmpty())
      return ClassName.bestGuess(argBuilder!!.context.name)
          .nestedClass(argBuilder!!.name)
    }

    // When scalar/primitive type -> no type arg,
    val parameterizedTypeNames: List<TypeName> = mutableListOf<TypeName>().apply {
      if (type !is ScalarType) add(type.name.asTypeName())
      if (arguments.isNotEmpty()) add(argBuilderTypeName())
    }

    return if (type is ScalarType && arguments.isEmpty()) baseTypeName else ParameterizedTypeName.get(
        ClassName(
            baseTypeName.packageName(),
            baseTypeName.enclosingClassName()!!.simpleName(),
            baseTypeName.simpleName()
        ),
        *parameterizedTypeNames.toTypedArray()
    )
  }

}

data class ArgumentDefinition(override val context: GraphQLSchemaParser.ArgumentContext) : ScopedSymbol(), KotlinParameterElement {

  override val name: String get() = context.Name().text

  override val typeName: String get() = context.typeSpec().typeName().text

  override val nullable = context.typeSpec().nullable() == null

  override val isList = context.typeSpec().listType() != null

  override lateinit var type: SchemaType<*>

  override fun toKotlin(): ParameterSpec {
    val type = if (isList)
      ParameterizedTypeName.get(List::class.asClassName(), typeName.asTypeName())
    else typeName.asTypeName()

    return ParameterSpec.builder(name, type.apply {
      if (nullable) asNullable()
    }).build()
  }

  override fun equals(other: Any?): Boolean {
    return other === this || (other as? ArgumentDefinition)?.let {
      name == it.name
          && typeName == it.typeName
          && nullable == it.nullable
          && isList == it.isList
    } ?: return false
  }

  override fun hashCode(): Int {
    var result = context.hashCode()
    result = 31 * result + nullable.hashCode()
    result = 31 * result + isList.hashCode()
    return result
  }
}

