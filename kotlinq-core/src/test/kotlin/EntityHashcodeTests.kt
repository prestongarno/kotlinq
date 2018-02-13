import org.junit.Test
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.Kotlinq

class EntityHashcodeTests {

  @Test fun `two fragments with identities matching return same hashcode`() {
    val generator: () -> FragmentAdapter = {
      Kotlinq.adapterService.fragmentProperty(
          "Hello",
          mockType(Any::class),
          setOf({ MockContext(Kotlinq.createGraphQlInstance("World")) }),
          emptyMap())
    }

    val expected = generator().hashCode()

    var i = 0

    generateSequence {
      if (i++ < 100) generator() else null

    }.forEach { fragmentAdapter ->
      fragmentAdapter.hashCode() eq expected
    }

  }
}