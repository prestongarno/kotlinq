package org.kotlinq.api

import org.kotlinq.api.services.Configuration


/**
 * Factory which creates new [GraphQlInstance]
 */
interface GraphQlInstanceProvider {

  fun createNewInstance(): GraphQlInstance

  companion object : GraphQlInstanceProvider by Configuration.instance()
}