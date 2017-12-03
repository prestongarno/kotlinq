/*
 * Copyright (C) 2017 Preston Garno
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
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec


internal class ArgBuilderDef(val field: FieldDefinition, val context: ScopedDeclarationType<*>) : KotlinTypeElement {

  val isInterface = field.isAbstract

  val name = (field.name[0].toUpperCase() + (if (field.name.length > 1) field.name.substring(1) else "") + "Args").let {
    if (isInterface) it.prepend("Base") else it
  }

  // spaghetti
  override fun toKotlin(): TypeSpec {
    return TypeSpec.classBuilder(name).apply {
      superclass(ArgBuilder::class)
      addSuperinterfaces(field.inheritsFrom.map {
        it.symtab[field.name]!!.name
        ClassName("", it.name).nestedClass("Base" + name)
      })
      // add constructor for non-nullable input arg arguments
      if (field.arguments.find { !it.nullable } != null) primaryConstructor(FunSpec.constructorBuilder()
          .addParameters(field.arguments.filterNot {
            it.nullable
          }.map {
            it.toKotlin()
          }).addCode(
          CodeBlock.of(field.arguments.filterNot {
            it.nullable
          }.joinToString("\n") { """"${it.name}" with ${it.name}""" }))
          .build())

      field.arguments.filter { it.nullable }.map {
        // add nullable properties for config { } dsl block
        PropertySpec.builder(it.name,
            it.typeName
                .asTypeName()
                .asNullable())
            .mutable(true)
            .delegate("arguments")
            .build()
      }.let(this::addProperties)

    }.build()
  }
}
