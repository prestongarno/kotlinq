package org.kotlinq.api

import org.kotlinq.api.services.Configuration


/**
 * God-object exposed to consumers and specifically kotlinq-schema
 * for creating kotlin platform delegate properties
 */
interface Kotlinq {

  val adapterService: AdapterService

  fun createFragment(initializer: () -> Definition): Fragment

  fun createGraphQlInstance(): GraphQlInstance

  companion object : Kotlinq {

    override
    val adapterService: AdapterService
      get() = Configuration.instance()

    override fun createGraphQlInstance(): GraphQlInstance {
      return GraphQlInstanceProvider.createNewInstance()
    }

    override fun createFragment(initializer: () -> Definition): Fragment =
        FragmentImpl(initializer)

  }
}

