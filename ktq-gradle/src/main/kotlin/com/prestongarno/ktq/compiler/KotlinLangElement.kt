package com.prestongarno.ktq.compiler

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
