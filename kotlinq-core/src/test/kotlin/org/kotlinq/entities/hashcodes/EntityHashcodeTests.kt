package org.kotlinq.entities.hashcodes

import org.junit.Test
import org.kotlinq.MockContext
import org.kotlinq.PrimitiveData
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.Kotlinq
import org.kotlinq.eq
import org.kotlinq.mockType
import kotlin.coroutines.experimental.buildSequence

class EntityHashcodeTests {

  @Test fun `fragments with identities matching return same hashcode`() {

    val name = PrimitiveData.STRING.generator().toString()
    val arguments = PrimitiveData.randomGraphQlArgumentMap()
    val instanceTypeName = PrimitiveData.STRING.generator().toString()

    val generator: () -> FragmentAdapter = {

      Kotlinq.adapterService.fragmentProperty(
          name,
          mockType(Any::class),
          setOf({ MockContext(Kotlinq.createGraphQlInstance(instanceTypeName)) }),
          arguments)

    }

    val expected = generator().hashCode()

    buildSequence {
      for (i in 1..100) yield(generator())
    }.forEach {
      it.hashCode() eq expected
    }
  }

}
