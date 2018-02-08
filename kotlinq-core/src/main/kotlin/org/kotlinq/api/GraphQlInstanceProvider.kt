package org.kotlinq.api

import com.github.salomonbrys.kodein.instance


interface GraphQlInstanceProvider {

  fun createNewInstance(typeName: String, context: Context): GraphQlInstance

  companion object : GraphQlInstanceProvider by Configuration.kodein.instance()
}