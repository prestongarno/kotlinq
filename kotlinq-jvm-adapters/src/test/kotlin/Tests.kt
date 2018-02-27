import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.jvm.Data
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
}


