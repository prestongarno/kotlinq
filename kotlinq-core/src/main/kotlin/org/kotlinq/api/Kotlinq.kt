package org.kotlinq.api

import com.github.salomonbrys.kodein.instance


/**
 * God-object exposed to kotlinq-schema module
 * for creating kotlin platform delegate properties
 */
interface Kotlinq {

  val adapterService: AdapterService

  fun createGraphQlInstance(
      typeName: String
  ): GraphQlInstance


  companion object : Kotlinq {

    override
    val adapterService: AdapterService
      get() = Configuration.kodein.instance()

    override fun createGraphQlInstance(typeName: String): GraphQlInstance =
        GraphQlInstanceProvider.createNewInstance(typeName)
  }
}

