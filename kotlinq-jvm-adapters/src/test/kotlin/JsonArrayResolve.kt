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

  @Test fun listOfJsonObjectsResolvesToInstanceField() {

    class SimpleData(input: GraphQlResult) : Data by input.toData() {
      val field0: String by result.string()
    }


    class Clazz(input: GraphQlResult) : Data by input.toData() {

      val listOfSimpleData: List<SimpleData>
          by result<SimpleData>().asList()
    }


    // JSON object, nested list of 100 objects with a "field0" field
    val input1 = json {
      Clazz::listOfSimpleData.name list {
        for (i in 0..100)
          add { "field0"("$i") }
      }
    }

    val frag = fragment(::Clazz) {
      Clazz::listOfSimpleData..{
        on(::SimpleData) // TODO simple frag spread op on list properties
      }
    }

    assertThat(Validation.canResolve(input1, frag)).isTrue()
    val clazzInstance = frag.resolveFrom(input1)!!
    assertThat(clazzInstance.listOfSimpleData.size).isEqualTo(101)

    // String.toInt() returns index
    clazzInstance.listOfSimpleData.withIndex().all { (index, simple) ->
      index - simple.field0.toInt() == 0
    }.let { shouldBeTrue ->

      assertThat(shouldBeTrue).isTrue()
    }
  }
}
