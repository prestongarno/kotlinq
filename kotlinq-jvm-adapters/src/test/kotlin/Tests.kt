import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.jvm.Data
import org.kotlinq.jvm.GraphQlResult
import org.kotlinq.jvm.TypedFragment.Companion.typedFragment
import org.kotlinq.jvm.not
import org.kotlinq.jvm.toData


class Tests {

  abstract class Super : Data

  class Bar(graphQlResult: GraphQlResult) : Data by graphQlResult.toData() {
    val baz by result.integer()
  }

  class Foo(result: GraphQlResult) : Data by result.toData() {
    val floatProp by result.bool()
    val fooProp by result<Bar>()
  }

  class Inner1(result: GraphQlResult) : Super(), Data by result.toData() {
    val innerProp1: String by !result
  }

  class Inner2(result: GraphQlResult) : Super(), Data by result.toData() {
    val innerProp1 by result.string()
    val innerobject by result<Inner1>()
  }

  class Root(result: GraphQlResult) : Data by result.toData() {
    val abstractSelect by result<Super>()
  }

  class UnionRoot(result: GraphQlResult) : Data by result.toData() {
    val whatever by result<Data?>()
  }




  @Test fun bar() {

    val jvmReflectFragment = typedFragment<Foo>()

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

    val fragment = typedFragment<Root> {
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

    assertThat(fragment.toGraphQl(pretty = true, idAndTypeName = false).also(::println))
        .isEqualTo(expect)
  }

  @Test fun fragmentSpreadUnionWorks() {
    val query = typedFragment<UnionRoot> {
      UnionRoot::whatever union {
        on(::Root)
        on(::Inner1)
        on(::Inner2)
      }
    }.toGraphQl(pretty = true, idAndTypeName = false)
        .let(::println)
  }
}


