/*
 * Copyright (C) 2018 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.prestongarno.kotlinq.compiler

import com.prestongarno.kotlinq.org.antlr4.definitions.GraphQLSchemaParser
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asClassName
import org.antlr.v4.runtime.ParserRuleContext

sealed class ScopedSymbol : SymbolElement {
  abstract val nullable: Boolean
  abstract val isList: Boolean
  abstract var type: SchemaType
  abstract val context: ParserRuleContext
}

data class FieldDefinition(override val context: GraphQLSchemaParser.FieldDefContext) : ScopedSymbol(), KotlinPropertyElement {


  override val name = context.fieldName().Name().text!!

  override val typeName = getTypeNameOrError(context)
  // TODO actually use the parse trees so don't have to exception on ctor^

  override val nullable = context.typeSpec().nullable() == null

  override val isList = context.typeSpec().listType() != null

  override lateinit var type: SchemaType

  var isAbstract: Boolean = false

  lateinit var inheritsFrom: Set<InterfaceDef>

  /** has to be done from an outside context. Created in [GraphQLCompiler.attrInheritance]*/
  internal var argBuilder: com.prestongarno.kotlinq.compiler.ArgumentSpecDef? = null

  val arguments: List<ArgumentDefinition> by lazy {
    context.fieldArgs()
        ?.argument()
        ?.map { ArgumentDefinition(this, it) }
        ?: emptyList()
  }

  val requiresConfiguration: Boolean
    get() = arguments.isNotEmpty() && __requiresConfiguration
        || arguments.find { !it.nullable } != null

  @Suppress("PrivatePropertyName") private var __requiresConfiguration = false

  fun flagAsRequiringConfiguration() {
    __requiresConfiguration = true
  }


  override fun toKotlin(): PropertySpec =
      PropertySpec.builder(name, getAliasName()).apply {
        if (!isAbstract)
          delegate(type.getStubDelegationCall(this@FieldDefinition))
        if (inheritsFrom.isNotEmpty())
          addModifiers(KModifier.OVERRIDE)
      }.build()

  private
  fun getAliasName(): TypeName {
    val typeAlias = type.getPropertyStubTypeAlias(this).asTypeName()

    return (typeAlias as? ParameterizedTypeName)?.let {
      if (isAbstract && argBuilder != null)
        ParameterizedTypeName.get(it.rawType, it.typeArguments.first(), it.typeArguments.last().annotated(
            AnnotationSpec.builder(Nothing::class).build()
        )) else it
    } ?: typeAlias
  }

  companion object {
    // not exactly sure how to do 'out' variance on parameterized types
    const val OUT_VARIANCE_MARKER = "@java.lang.Void"

    // TODO support multi-dimensional arrays
    private fun getTypeNameOrError(context: GraphQLSchemaParser.FieldDefContext): String =
        context.typeSpec().text.let { value ->
          val brackets = value.takeWhile { it -> it == '[' }.count()
          require(brackets == value.takeLastWhile { it == '!' || it == ']' }.replace("!", "").count()) {
            "Unmatched bracket on field ${context.fieldName()}"
          }
          return@let value.substring(brackets, value.indexOfFirst { it == ']' || it == '!' }.let { if (it < 1) value.length else it })
        }
  }

}

data class ArgumentDefinition(
    private val field: FieldDefinition,
    override val context: GraphQLSchemaParser.ArgumentContext
) : ScopedSymbol(),
    KotlinPropertyElement {

  override val name: String = context.Name().text

  override val typeName: String = getTypeNameOrError(context)

  override val nullable = context.typeSpec().nullable() == null

  override val isList = context.typeSpec().listType() != null

  override lateinit var type: SchemaType

  val isAbstract = field.isAbstract

  val asParameter = AsParameter()

  override fun toKotlin(): PropertySpec =
      PropertySpec.builder(
          // name
          name,
          // Type name
          type.name.asTypeName().let {
            if (isList) ParameterizedTypeName.get(ClassName("kotlin.collections", "List"), it) else it
          }.let {
                if (this@ArgumentDefinition.nullable) it.asNullable() else it
              }, // modifiers
          *(if (!isAbstract && field.inheritsFrom.find { superiface ->
                superiface.symtab[field.name]?.arguments
                    ?.find { arg -> arg.name == name } != null
              } != null)
            arrayOf(KModifier.OVERRIDE)
          else if (isAbstract) arrayOf(KModifier.ABSTRACT)
          else emptyArray())
      ).apply {
        // assignment
        if (!isAbstract)
          delegate(if (!nullable) notNullDelegateCode(this@ArgumentDefinition) else CodeBlock.of("arguments"))
        mutable(nullable)
      }.build()

  inner class AsParameter : KotlinParameterElement {

    override fun toKotlin(): ParameterSpec {
      val type = if (isList)
        ParameterizedTypeName.get(List::class.asClassName(), typeName.asTypeName())
      else typeName.asTypeName()

      return ParameterSpec.builder(name, type.apply {
        if (nullable) asNullable()
      }).build()
    }
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

  companion object {

    // TODO support multi-dimensional arrays
    private fun getTypeNameOrError(context: GraphQLSchemaParser.ArgumentContext): String =
        context.typeSpec().text.let { value ->
          val brackets = value.takeWhile { it -> it == '[' }.count()
          require(brackets == value.takeLastWhile { it == '!' || it == ']' }.replace("!", "").count()) {
            "Unmatched bracket on field ${context.Name().text}"
          }
          return@let value.substring(brackets, value.indexOfFirst { it == ']' || it == '!' }.let { if (it < 1) value.length else it })
        }
  }
}


