package org.kotlinq.entities.hashcodes

import org.junit.Test
import org.kotlinq.PrimitiveData
import org.kotlinq.api.Adapter
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.PropertyInfo
import org.kotlinq.introspection.Kind
import org.kotlinq.eq
import org.kotlinq.println
import kotlin.coroutines.experimental.buildSequence

class EntityHashcodeTests {

  @Test fun `fragments with identities matching return same hashcode`() {

    Kind.typeNamed("Hello").println()

    val name = PrimitiveData.STRING.generator().toString()
    val arguments = PrimitiveData.randomGraphQlArgumentMap()
    val instanceTypeName = PrimitiveData.STRING.generator().toString()

    val generator: () -> Adapter = {

      Kotlinq.adapterService.fragmentProperty(
          PropertyInfo
              .propertyNamed(name)
              .typeKind(Kind.typeNamed(instanceTypeName))
              .arguments(arguments)
              .build(),
              setOf (Kotlinq.newContextBuilder().build(instanceTypeName)))

    }

    val expected = generator().hashCode()

    buildSequence {
      for (i in 1..10) yield(generator())
    }.forEach {
      it.hashCode() eq expected
    }
  }

}
