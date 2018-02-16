package org.kotlinq.api

import org.kotlinq.services.Configuration


/**
 * Factory which creates new [GraphQlInstance]
 */
interface GraphQlInstanceProvider {

  fun createNewInstance(typeName: String): GraphQlInstance

  companion object : GraphQlInstanceProvider by Configuration.instance()
}