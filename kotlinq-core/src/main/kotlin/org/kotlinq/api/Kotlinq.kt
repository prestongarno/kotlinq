package org.kotlinq.api

import org.kotlinq.api.services.Configuration


/**
 * God-object exposed to consumers and specifically kotlinq-schema
 * for creating kotlin platform delegate properties
 */
interface Kotlinq {

  val adapterService: AdapterService

  fun newContextBuilder(): BindableContext



  companion object : Kotlinq {

    override
    val adapterService: AdapterService
      get() = Configuration.instance()

    override fun newContextBuilder() =
        GraphQlInstanceProvider.newContextBuilder()

  }
}

