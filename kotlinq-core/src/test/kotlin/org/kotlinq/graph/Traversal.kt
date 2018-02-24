package org.kotlinq.graph

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.api.GraphVisitor
import org.kotlinq.entities.TestFragmentBuilder.Companion.fragment
import org.kotlinq.println
import kotlin.coroutines.experimental.buildSequence

class Traversal {

  @Test fun `every scalar in simple fragment is touched in traversal`() {

    val levelOneNames = mutableListOf<String>()
        .and("one")
        .and("two")
        .and("three")
        .and("four")
        .and("five")

    val frag = fragment("Foo") {
      for (name in levelOneNames) scalar(name)
    }

    assertThat(frag.typeName).isEqualTo("Foo")

    assertThat(frag.graphQlInstance.properties.values.map {
      it.propertyInfo.graphQlName
    }).containsExactlyElementsIn(levelOneNames)

    GraphVisitor.builder()
        .onVisitField { field ->
          levelOneNames.removeAll { field.propertyInfo.graphQlName == it }
        }.build()
        .let(frag::traverse)

    assertThat(levelOneNames).isEmpty()
  }


  @Test fun `every fragment on enter is notified`() {

    val propertyNames = listOf("one", "two", "three")

    val fragments = buildSequence {
      for (i in 0..4) {
        fragment("Fragment$i") {
          propertyNames.forEach { scalar(it) }
        }.let { yield(it) }
      }
    }.withIndex().toList()

    val topLevelFragment = fragment {
      fragments.forEach { (i, fragment) ->
        bindFragment("fragment$i", fragment)
      }
    }

    val expectedTotalScalarCount = fragments.size * propertyNames.size + fragments.size
    var visitFieldCounter = 0
    var notifyFragmentCounter = 0
    var visitContextCounter = 0

    GraphVisitor.builder()
        .onVisitField { visitFieldCounter++ }
        .onVisitContext { visitContextCounter++ }
        .onNotifyEnter { notifyFragmentCounter++ >= 0 }
        .build()
        .let(topLevelFragment::traverse)

    assertThat(visitFieldCounter)
        .isEqualTo(expectedTotalScalarCount)
    assertThat(visitContextCounter)
        .isEqualTo(fragments.size + 1)
    assertThat(notifyFragmentCounter)
        .isEqualTo(fragments.size + 1)

    for ((_, fragment) in fragments) {
      assertThat(fragment in topLevelFragment).isTrue()
    }
  }


}

fun <T> MutableList<T>.and(element: T) =
    apply { add(element) }
