package org.kotlinq.api

import org.kotlinq.api.services.Configuration


/**
 * Factory which creates new [GraphQlInstance]
 */
interface GraphQlInstanceProvider {

  fun newContextBuilder(): BindableContext

  companion object : GraphQlInstanceProvider by Configuration.instance()
}