package org.kotlinq.jvm

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.api.Kind
import org.kotlinq.jvm.annotations.Ignore
import kotlin.reflect.KClass


class ResolverTest {

  open class Root(value: GraphQlResult) : GraphQlData(value) {
    val foo by value.integer()
    val bar by value.string()
    open val nest by value<Nested>()
  }

  open class Nested(value: GraphQlResult) : GraphQlData(value) {
    open val baz by value.string()
  }

  open class SubNested0(value: GraphQlResult) : Nested(value) {
    val field0 by result.string()
    val field1 by result.integer()
  }

  class SubSubNestedDef(value: GraphQlResult) : SubNested0(value) {
    val field2 by result.integer()
  }

  class SubNested1(value: GraphQlResult) : Nested(value) {
    @Ignore override val baz = ""
    val child: SubNested0? by result<SubNested0>()
  }


  class RootWithNodeList(value: GraphQlResult) : Data by value.toData() {
    val listOfChildNodes: List<Nested> by result<Nested>().asList()
  }

  @Test fun singleNestededFragmentResolves() {

    class RootSub(value: GraphQlResult) : Root(value) {
      @Ignore
      override val nest = null
    }

    val frag = fragment(::RootSub)


    assertThat(frag.toGraphQl())
        .isEqualTo("{id,__typename,bar,foo}")

    val data = mapOf(
        "foo" to 1000,
        "bar" to "is this thing on")

    assertThat(frag.resolveFrom(data))
        .isNotNull()

    assertThat(frag.resolveFrom(data)!!.bar)
        .isEqualTo("is this thing on")

    assertThat(frag.resolveFrom(data)!!.foo)
        .isEqualTo(1000)
  }

  @Test fun resolveSubFragment() {

    val frag = fragment(::Root) {
      Root::nest on ::Nested
    }

    val result = json {
      "foo"(100)
      "bar"("Hello")
      "nest" {
        "baz"("World")
        "__typename"("Nested")
      }
    }

    assertThat(Validation.canResolve(result, frag)).isTrue()
    val reified = frag.resolveFrom(result)
    assertThat(reified).isNotNull()
    assertThat(reified!!.nest).isNotNull()
    assertThat(reified.nest!!.baz).isEqualTo("World")
  }

  @Test fun subclassFragmentSubstitutionChangesQuery() {

    val fragmentSpread = fragment(::RootWithNodeList) {
      RootWithNodeList::listOfChildNodes..{
        on(::SubNested0)
        on(::SubNested1)
      }
    }

    assertThat(fragmentSpread.toGraphQl(pretty = true, idAndTypeName = false))
        .isEqualTo(
            """
            {
              listOfChildNodes {
                ... on SubNested0 {
                  field0
                  field1
                  baz
                }
                ... on SubNested1 {
                  child {
                    field0
                    field1
                    baz
                  }
                }
              }
            }
            """.trimIndent())


    val subclassNestedSpread = fragment(::RootWithNodeList) {
      RootWithNodeList::listOfChildNodes..{
        on(::SubNested0)
        on(::SubNested1) {
          SubNested1::child on ::SubSubNestedDef
        }
      }
    }

    assertThat(subclassNestedSpread
        .toGraphQl(pretty = true, idAndTypeName = false))
        .isEqualTo(
            """
            {
              listOfChildNodes {
                ... on SubNested0 {
                  field0
                  field1
                  baz
                }
                ... on SubNested1 {
                  child {
                    field2
                    baz
                    field0
                    field1
                  }
                }
              }
            }
            """.trimIndent())
  }

  @Test fun subclassFragmentSubstitutionResolvesCorrectType() {

    val frag = fragment(::RootWithNodeList) {
      RootWithNodeList::listOfChildNodes..{
        on(::SubNested0)
        on(::SubNested1) {
          SubNested1::child on ::SubSubNestedDef
        }
      }
    }

    val info = frag.graphQlInstance.properties["listOfChildNodes"]!!
        .propertyInfo

    assertThat(info.graphQlType).isEqualTo("Nested")
    assertThat(info.kind).isEqualTo(Kind
        .typeNamed("Nested")
        .asList())

    val response = json {
      "__typename"(RootWithNodeList::class.name)

      "listOfChildNodes" list {
        add {
          "__typename"(SubNested0::class.name)
          "field0"("hello")
          "field1"(-77)
          "baz"("world")
        }
      }
    }

    assertThat(Validation.canResolve(response, frag)).isTrue()
    assertThat(frag.resolveFrom(response)).isNotNull()

    val result = frag.resolveFrom(response)!!

    assertThat(result.listOfChildNodes).hasSize(1)

    val firstElement = result.listOfChildNodes.first().let {
      assertThat(it::class).isEqualTo(SubNested0::class)
      it as SubNested0
    }

    assertThat(firstElement.baz)
        .isEqualTo("world")
    assertThat(firstElement.field0)
        .isEqualTo("hello")
    assertThat(firstElement.field1)
        .isEqualTo(-77)

    val multiResponse = json {
      "__typename"(RootWithNodeList::class.name)

      "listOfChildNodes" list {
        add {
          "__typename"(SubNested1::class.name)
          "field0"("hello")
          "field1"(-77)
          "child" {
            "field0"("childField0")
            "field1"(35)
            "baz"("")
          }
        }
        add {
          "__typename"(SubNested0::class.name)
          "field0"("hello")
          "field1"(-77)
          "baz"("world")
        }
      }
    }

    assertThat(Validation.canResolve(multiResponse, frag)).isTrue()

    val multiResult = frag.resolveFrom(multiResponse)!!

    assertThat(multiResult.listOfChildNodes).hasSize(2)
  }


}


val KClass<*>.name get() = simpleName!!
