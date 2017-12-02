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

import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec

interface KotlinLangElement<T : Any> {
  fun toKotlin(): T
}

interface KotlinTypeElement : KotlinLangElement<TypeSpec>

interface KotlinPropertyElement : KotlinLangElement<PropertySpec>

interface KotlinParameterElement : KotlinLangElement<ParameterSpec>


interface NamedElement {
  val name: String
}

interface SymbolElement : NamedElement {
  val typeName: String
}
