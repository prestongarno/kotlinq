import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.jvm.Data
import org.kotlinq.jvm.GraphQlResult
import org.kotlinq.jvm.TypedFragment.Companion.typedFragment
import org.kotlinq.jvm.invoke

class Tests {

  @Test fun bar() {

    class Bar(map: Map<String, Any>) : Data by map() {
      val baz: Int by map
    }
    class Foo(map: Map<String, Any>) : Data by map() {
      val floatProp = 0.1f // can't do anything about this
      val fooProp: Bar by result
    }

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
    abstract class Super : Data

    class Inner1(map: GraphQlResult): Super(), Data by map() {
      val innerProp1: String by map
    }
    class Inner2(map: GraphQlResult): Super(), Data by map() {
      val innerProp1: String by map
      val innerobject: Inner1 by map
    }

    class Root(map: Map<String, Any>): Data by map() {
      val abstractSelect: Super by map
    }


    val fragment = typedFragment<Root> {
      Root::abstractSelect..{
        on<Inner1>()
        on<Inner2>()
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
}


