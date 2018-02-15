package org.kotlinq.api

import com.github.salomonbrys.kodein.instance


/**
 * Factory which creates new [GraphQlInstance]
 */
interface GraphQlInstanceProvider {

  fun createNewInstance(typeName: String): GraphQlInstance

  companion object : GraphQlInstanceProvider by Configuration.instance()
}