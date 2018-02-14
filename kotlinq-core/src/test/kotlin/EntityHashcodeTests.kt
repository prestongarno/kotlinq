import org.junit.Test
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.Kotlinq
import java.util.*
import java.util.concurrent.ThreadLocalRandom.current
import kotlin.coroutines.experimental.buildSequence

class EntityHashcodeTests {

  @Test fun `two fragments with identities matching return same hashcode`() {

    val name = ArgumentTypes.STRING.generator().toString()
    val arguments = ArgumentTypes.randomGraphQlArgumentMap()
    val instanceTypeName = ArgumentTypes.STRING.generator().toString()

    val generator: () -> FragmentAdapter = {
      Kotlinq.adapterService.fragmentProperty(
          name,
          mockType(Any::class),
          setOf({ MockContext(Kotlinq.createGraphQlInstance(instanceTypeName)) }),
          arguments)
    }

    val expected = generator().hashCode()

    buildSequence {
      for (i in 1..100) yield(generator())
    }.forEach {
      it.hashCode() eq expected
    }
  }

  private enum class ArgumentTypes(val generator: () -> Any) {
    STRING(UUID.randomUUID()::toString),
    INT({ randomInt(Int.MIN_VALUE, Int.MAX_VALUE) }),
    FLOAT({ current().nextDouble().toFloat() }),
    BOOLEAN({ (randomInt(0, 2) == 1) }),
    ENUM({ randomArgumentType() }),
    LIST({
      val type = randomArgumentType()
      buildSequence {
        for (i in 1..randomInt(1, 10)) yield(type.generator())
      }.toList()
    }); // so meta...

    companion object {

      fun randomArgumentType() =
          ArgumentTypes.values()[current().nextInt(0, ArgumentTypes.values().size)]

      fun randomArgument() = STRING.generator().toString() to randomArgumentType().generator()

      fun randomGraphQlArgumentMap(size: Int = randomInt(0, 1000)) = buildSequence {
        for (i in 1..size) yield(randomArgument())
      }.toMap()
    }
  }
}

private
fun randomInt(min: Int, max: Int) = current().nextInt(min, max)