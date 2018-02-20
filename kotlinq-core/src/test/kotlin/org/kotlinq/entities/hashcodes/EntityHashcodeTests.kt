package org.kotlinq.entities.hashcodes

import org.junit.Test
import org.kotlinq.MockDefinition
import org.kotlinq.PrimitiveData
import org.kotlinq.api.Adapter
import org.kotlinq.api.Kotlinq
import org.kotlinq.eq
import org.kotlinq.info
import kotlin.coroutines.experimental.buildSequence

class EntityHashcodeTests {

  @Test fun `fragments with identities matching return same hashcode`() {

    val name = PrimitiveData.STRING.generator().toString()
    val arguments = PrimitiveData.randomGraphQlArgumentMap()
    val instanceTypeName = PrimitiveData.STRING.generator().toString()

    val generator: () -> Adapter = {

      Kotlinq.adapterService.fragmentProperty(
          info(name, "GraphQlAny", arguments, Any::class),
          setOf({ MockDefinition(Kotlinq.createGraphQlInstance(), "GraphQlAny") }))

    }

    val expected = generator().hashCode()

    buildSequence {
      for (i in 1..100) yield(generator())
    }.forEach {
      it.hashCode() eq expected
    }
  }

}
