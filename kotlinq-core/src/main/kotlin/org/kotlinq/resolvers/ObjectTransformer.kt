package org.kotlinq.resolvers

import com.beust.klaxon.Parser
import org.kotlinq.Model
import org.kotlinq.adapters.GraphQlAdapter
import org.kotlinq.api.Resolver
import java.io.StringReader
import kotlin.reflect.jvm.jvmErasure

internal
class ObjectTransformerImpl : Resolver {


  override fun transform(value: String, target: GraphQlAdapter): Boolean {

    return when (target.type.jvmErasure) {

      Model::class -> {
        parseModel(value, target)
        (target.getValue() as? Model<*>)?.isResolved() == true
      }

      List::class -> TODO()

      else -> TODO()

    }
  }

  private
  fun parseModel(value: String, target: GraphQlAdapter) {
    Parser().parse(StringReader(value))?.let {

    }
  }

}

