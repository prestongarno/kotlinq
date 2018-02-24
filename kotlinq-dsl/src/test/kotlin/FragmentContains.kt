import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.dsl.fragment
import org.kotlinq.dsl.query
import org.kotlinq.fragments.getFragments

class FragmentContains {

  val query = query {
    "first" on def("First") {
      "second" on def("Second") {
        "third" on def("Third") {
          "fourth" on def("Fourth") {
            "Hello"(!string)
          }
        }
      }
    }
  }

  @Test fun `nested deep contains`() {
    query.toGraphQl(pretty = true).let(::println)

    val inner = fragment("Fourth") {
      "Hello"(!string)
    }

    assertThat(inner in query)
        .isTrue()
  }


  @Test fun `nested deep different not contains`() {
    val shouldntBeInner = fragment("Foo") {
      "bar"(string)
    }

    assertThat(shouldntBeInner in query).isFalse()
  }


  @Test fun `nested deep different not contains 2`() {
    val shouldntBeInner = fragment("Fourth") {
      "Hello"(string) // <-- Not nullable
    }

    assertThat(shouldntBeInner in query).isFalse()
  }


  @Test fun `nested deep different not contains 3`() {
    val subFragment = fragment("Second") {
      "third" on def("Third") {
        "fourth" on def("Fourth") {
          "Hello"(!string)
        }
      }
    }

    val subSubFragment = fragment("Third") {
      "fourth" on def("Fourth") {
        "Hello"(!string)
      }
    }

    val bottomFragment = fragment("Fourth") {
      "Hello"(!string)
    }

    listOf(query, subFragment, subSubFragment, bottomFragment)
        .map {
          it.toGraphQl(pretty = true) + "\n" + "=".repeat(30)
        }.forEach(::println)

    query.getFragments()
        .forEach {
          println(it.typeName)
        }

    assertThat(bottomFragment in subSubFragment).isTrue()
    assertThat(subSubFragment in query).isTrue()
    assertThat(subFragment in query).isTrue()
  }
}

