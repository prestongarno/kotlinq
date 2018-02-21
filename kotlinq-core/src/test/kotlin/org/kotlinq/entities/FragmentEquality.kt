package org.kotlinq.entities

import org.junit.Test
import org.kotlinq.api.Fragment
import org.kotlinq.api.Kind
import org.kotlinq.entities.TestFragmentBuilder.Companion.fragment
import org.kotlinq.eq
import org.kotlinq.notEq


class FragmentEquality {

  @Test fun `fragments are equal 1`() {
    fragment {
      scalar("foo")
      scalar("bar")
    } eq fragment {
      scalar("foo")
      scalar("bar")
    }
  }


  @Test fun `fragments are not equal 1`() {
    fragment {
      scalar("foo", kind = Kind.float)
    } notEq fragment {
      scalar("foo", kind = Kind.bool)
    }
  }

  @Test fun `fragment fragment are not equal`() {
    fragment {
      "frag1"(Kind.named("One")) on {
        scalar("foo", Kind.integer)
        scalar("bar", Kind.bool)
      }
    } notEq fragment {
      "frag1"(Kind.named("Two")) on {
        scalar("foo", Kind.integer)
        scalar("bar", Kind.bool)
      }
    }.apply {
      graphQlInstance.edges.firstOrNull()!!.typeName eq "Two"
    }
  }

  @Test fun `fragments are equal 2`() {

    val frag1 = fragment {
      "frag1" on {
        scalar("hello")
        scalar("world", Kind.integer)
        scalar("foo")
        "frag2" on {
          scalar("hello")
          scalar("world", Kind.integer)
        }
        scalar("foo")
      }
    }
    val frag2 = fragment {
      "frag1" on {
        scalar("hello")
        scalar("world", Kind.integer)
        scalar("foo")
        "frag2" on {
          scalar("hello")
          scalar("world", Kind.integer)
        }
        scalar("foo")
      }
    }


    frag1.graphQlInstance.toGraphQl(pretty = true) eq
        frag2.graphQlInstance.toGraphQl(pretty = true)
    frag1.graphQlInstance.toGraphQl(pretty = false) eq
        frag2.graphQlInstance.toGraphQl(pretty = false)
    // failing
    //frag1 eq frag2
  }

}
