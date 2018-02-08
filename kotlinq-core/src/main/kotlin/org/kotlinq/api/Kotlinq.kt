package org.kotlinq.api

import com.github.salomonbrys.kodein.instance


interface Kotlinq {

  val adapterService: AdapterService

  fun createGraphQlInstance(
      typeName: String,
      context: Context
  ): GraphQlInstance


  companion object : Kotlinq {

    override
    val adapterService: AdapterService
      get() = Configuration.kodein.instance()

    override fun createGraphQlInstance(typeName: String, context: Context): GraphQlInstance =
        GraphQlInstanceProvider.createNewInstance(typeName, context)
  }
}

