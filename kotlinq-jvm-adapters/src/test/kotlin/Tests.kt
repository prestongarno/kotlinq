package org.kotlinq.jvm

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.jvm.ClassFragment.Companion.fragment


class Tests {

  abstract class Super : Data

  class Bar(graphQlResult: GraphQlResult) : Data by graphQlResult.toData() {
    val baz by result.integer().asList()
  }

  class Foo(result: GraphQlResult) : Data by result.toData() {
    val floatProp by result.bool()
    val fooProp by result<Bar>().asList()
  }

  class Inner1(result: GraphQlResult) : Super(), Data by result.toData() {
    val innerProp1: String? by result.string()
  }

  class Inner2(result: GraphQlResult) : Super(), Data by result.toData() {
    val innerProp1 by result.string()
    val innerobject by result<Inner1>()
  }

  class Root(result: GraphQlResult) : Data by result.toData() {
    val abstractSelect by result<Super>().asList()
  }

  class UnionRoot(result: GraphQlResult) : Data by result.toData() {
    val whatever by result<Data?>().asList()
  }


  @Test fun bar() {

    val jvmReflectFragment = fragment(::Foo)

    val expect = """
      |{
      |  floatProp
      |  fooProp {
      |    baz
      |  }
      |}
      """.trimMargin("|")

    assertThat(jvmReflectFragment.toGraphQl(pretty = true, idAndTypeName = false))
        .isEqualTo(expect)
  }


  @Test fun fragmentSpreadSomehowWorks() {

    val fragment = fragment(::Root) {
      Root::abstractSelect..{
        on(::Inner1)
        on(::Inner2)
      }
    }

    val expect = """
      |{
      |  abstractSelect {
      |    ... on Inner1 {
      |      innerProp1
      |    }
      |    ... on Inner2 {
      |      innerProp1
      |      innerobject {
      |        innerProp1
      |      }
      |    }
      |  }
      |}
      """.trimMargin("|")

    assertThat(fragment.toGraphQl(pretty = true, idAndTypeName = false))
        .isEqualTo(expect)
  }

  @Test fun fragmentSpreadUnionWorks() {

    val inner1Frag = fragment(::Inner1)

    val query = fragment(::UnionRoot) {
      UnionRoot::whatever union {
        on(::Root) {
          Root::abstractSelect..{
            on(::Inner2)
            on(::Inner1)
          }
        }
        on(::Inner2)
        on(inner1Frag)
      }
    }

    val queryString = query.toGraphQl(
        pretty = true,
        idAndTypeName = false)


    val expect = """
      |{
      |  whatever {
      |    ... on Root {
      |      abstractSelect {
      |        ... on Inner2 {
      |          innerProp1
      |          innerobject {
      |            innerProp1
      |          }
      |        }
      |        ... on Inner1 {
      |          innerProp1
      |        }
      |      }
      |    }
      |    ... on Inner2 {
      |      innerProp1
      |      innerobject {
      |        innerProp1
      |      }
      |    }
      |    ... on Inner1 {
      |      innerProp1
      |    }
      |  }
      |}
      """.trimMargin("|")


    assertThat(queryString).isEqualTo(expect)
  }
}


