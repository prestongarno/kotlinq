package org.kotlinq.jvm

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.util.*
import kotlin.coroutines.experimental.buildSequence


class JsonArrayResolve {

  @Test fun emptyListOfDifferentParamTypeIsStillValid() {
    val notIntList = listOf<Nothing?>()

    class Clazz(result: GraphQlResult) : GraphQlData(result) {
      val intList by result.integer().asList().asList()
    }

    assertThat(Validation.canResolve(
        mapOf("intList" to notIntList),
        fragment(::Clazz))
    ).isTrue()

    assertThat(
        fragment(::Clazz)
            .resolveFrom(mapOf("intList" to notIntList))!!
            .intList
    ).isEmpty()
  }

  @Test fun simpleScalarArrayResolves() {

    class Clazz(result: GraphQlResult) : Data by result.toData() {
      val integers: List<Int> by result.integer().asList()
    }

    val values = json { "integers"(0, 1, 2, 3, 4, 5, 6, 7, 8) }

    val shouldBeTrue = fragment(::Clazz)
        .resolveFrom(values)!!
        .integers
        .withIndex()
        .all { (index, value) -> index == value }

    assertThat(shouldBeTrue)
        .isTrue()
  }

  @Test fun twoDimensionalStringArrayResolves() {

    class Clazz(values: GraphQlResult) : GraphQlData(values) {

      val stringMatrix: List<List<String>> by result
          .string()
          .asList()
          .asList()
    }

    fun randomListOfStrings(length: Int): Sequence<String> = buildSequence {
      for (i in 0..length) yield(UUID.randomUUID().toString())
    }

    val listOfListOfStrings = buildSequence {
      for (i in 0..9) yield(randomListOfStrings(i).toList())
    }.toList()

    val input = mapOf("stringMatrix" to listOfListOfStrings)
    val frag = fragment(::Clazz)
    assertThat(Validation.canResolve(input, frag)).isTrue()

    frag.resolveFrom(input)!!.let { result ->

      assertThat(result.stringMatrix.size).isEqualTo(listOfListOfStrings.size)
      // 2 dimensional arrays with differing lengths
      result.stringMatrix.withIndex().forEach { (index, uuidList) ->
        assertThat(uuidList.size - 1).isEqualTo(index)
      }

      // Make sure that the order & values of the input has not changed when resolving
      listOfListOfStrings.withIndex().forEach { (index, inputList) ->

        val instanceList = result.stringMatrix[index]

        assertThat(instanceList)
            .containsAllIn(inputList)
            .inOrder()
      }
    }
  }
}