package org.kotlinq.entities.hashcodes

import org.junit.Test
import org.kotlinq.PrimitiveData
import org.kotlinq.api.Adapter
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.PropertyInfo
import org.kotlinq.api.Kind
import org.kotlinq.eq
import org.kotlinq.println
import kotlin.coroutines.experimental.buildSequence

class EntityHashcodeTests {

  @Test fun `fragments with identities matching return same hashcode`() {

    Kind.named("Hello").println()

    val name = PrimitiveData.STRING.generator().toString()
    val arguments = PrimitiveData.randomGraphQlArgumentMap()
    val instanceTypeName = PrimitiveData.STRING.generator().toString()

    val generator: () -> Adapter = {

      Kotlinq.adapterService.fragmentProperty(
          PropertyInfo
              .named(name)
              .typeKind(Kind.named(instanceTypeName))
              .arguments(arguments)
              .build(),
              setOf (Kotlinq.newContextBuilder().build(instanceTypeName)))

    }

    val expected = generator().hashCode()

    buildSequence {
      for (i in 1..100) yield(generator())
    }.forEach {
      it.hashCode() eq expected
    }
  }

}
