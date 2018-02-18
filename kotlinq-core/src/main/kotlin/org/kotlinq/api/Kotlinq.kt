package org.kotlinq.api

import org.kotlinq.api.services.Configuration


/**
 * God-object exposed to consumers and specifically kotlinq-schema
 * for creating kotlin platform delegate properties
 */
interface Kotlinq {

  val adapterService: AdapterService

  fun createFragment(initializer: () -> Context): Fragment

  fun createGraphQlInstance(
      typeName: String = "Object"
  ): GraphQlInstance

  companion object : Kotlinq {

    override
    val adapterService: AdapterService
      get() = Configuration.instance()

    override fun createGraphQlInstance(typeName: String): GraphQlInstance {
      return GraphQlInstanceProvider.createNewInstance(typeName)
    }

    override fun createFragment(initializer: () -> Context): Fragment =
        FragmentImpl(initializer)

  }
}

