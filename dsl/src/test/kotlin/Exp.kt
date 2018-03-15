import org.junit.Test
import kotlin.reflect.KFunction0


class TestContext {

  val superSecretHashMap = mutableMapOf<String, String>()

  operator fun KFunction0<(key: TestContext) -> Pair<String, String>>.not() {
    this.call().invoke(this@TestContext).also {
      superSecretHashMap[it.first] = it.second
    }
  }

  operator fun KFunction0<(key: TestContext) -> Pair<String, String>>.unaryMinus() {
    this().invoke(this@TestContext).also {
      superSecretHashMap[it.first] = it.second
    }
  }
}

fun String.extensionFunction(): (key: TestContext) -> Pair<String, String> = {
  this to this::extensionFunction.name
}

fun dslFoo(block: TestContext.() -> Unit): TestContext {
  return TestContext().apply(block)
}

class Exp {

  // TODO this is how to express nullability with symbols, higher order extension functions:)
  @Test fun testExtAndExtProperties() {
    dslFoo {
      !"Hello"::extensionFunction
      -"World"::extensionFunction
    }
        .let {
          require(it.superSecretHashMap["Hello"] == "extensionFunction")
          require(it.superSecretHashMap["World"] == "extensionFunction")
        }
  }

}
