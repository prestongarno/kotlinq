import org.junit.Test
import kotlin.reflect.KFunction0


class TestContext {
  var latest: String? = null

  val superSecretHashMap = mutableMapOf<String, String>()

  operator fun KFunction0<(key: TestContext) -> Pair<String, String>>.not() {
    this.call().invoke(this@TestContext).also {
      superSecretHashMap[it.first] = it.second
    }
  }

  operator fun KFunction0<(key: TestContext) -> Pair<String, String>>.unaryMinus() {
    call().invoke(this@TestContext).also {
      superSecretHashMap[it.first] = it.second
    }
  }
}

fun String.extensionFunction(): (key: TestContext) -> Pair<String, String> = {
  it.latest = this
  this to this::extensionFunction.name
}

fun dslFoo(block: TestContext.() -> Unit): TestContext {
  return TestContext().apply(block)
}

class Exp {

  @Test fun testExtAndExtProperties() {
    val call: KFunction0<(key: TestContext) -> Pair<String, String>> = ("Hello"::extensionFunction)
    println(call())
    "Hello"::extensionFunction.call().invoke(TestContext())
    println(TestContext().latest)

    dslFoo {
      !"Hello"::extensionFunction
      -"World"::extensionFunction // !! TODO this is how to express nullability with symbols, higher order extension functions:)
    }
        .let {
          val x = it.superSecretHashMap["Hello"]
          val y = it.superSecretHashMap["World"]
          require(x != null)
          require(y != null)
          println(x)
          println(y)
        }
  }

}
