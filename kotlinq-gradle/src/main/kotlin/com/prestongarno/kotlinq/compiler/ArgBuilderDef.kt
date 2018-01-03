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

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.TypeSpec


fun notNullDelegateCode(arg: ScopedSymbol, targetName: String = "arguments"): CodeBlock {
  val paramType = arg.type.name.asTypeName().let {
    if (arg.isList) ParameterizedTypeName.get(ClassName("kotlin.collections", "List"), it) else it
  }
  return CodeBlock.of("$targetName.notNull<%T>(\"${arg.name}\", ${arg.name})", paramType)
}

internal class ArgumentSpecDef(val field: FieldDefinition, val context: ScopedDeclarationType) : KotlinTypeElement {

  val isInterface = field.isAbstract

  val name = (field.name[0].toUpperCase() + (if (field.name.length > 1) field.name.substring(1) else "") + "Args").let {
    if (isInterface) it.prepend("Base") else it
  }

  override fun toKotlin(): TypeSpec = if (isInterface) toInterface() else toClass()
}

private fun ArgumentSpecDef.toInterface(): TypeSpec =
    TypeSpec.interfaceBuilder(name)
        .addSuperinterface(ArgumentSpec::class)
        .addProperties(
            field.arguments.map(ArgumentDefinition::toKotlin)
        ).build()

private fun ArgumentSpecDef.toClass(): TypeSpec = TypeSpec.classBuilder(name).apply {
  superclass(ArgBuilder::class)
  addSuperinterfaces(field.inheritsFrom.map {
    it.symtab[field.name]!!.name
    ClassName("", it.name).nestedClass("Base" + name)
  }).addProperties(field.arguments.map(ArgumentDefinition::toKotlin))
      .build()
  // add constructor for non-nullable input arg arguments
  if (field.arguments.find { !it.nullable } != null) primaryConstructor(FunSpec.constructorBuilder()
      .addParameters(field.arguments.filterNot(ArgumentDefinition::nullable)
          .map { it.asParameter.toKotlin() }).build())

}.build()
