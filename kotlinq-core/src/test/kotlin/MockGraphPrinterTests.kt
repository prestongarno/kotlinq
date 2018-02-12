import org.junit.Before
import org.junit.Test
import org.kotlinq.api.Configuration
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.Printer
import org.kotlinq.printer.VisitingPrinter

class MockGraphPrinterTests {

  @Before fun injectPrinter() {
    Configuration.configure(Configuration.Companion.Builder(printer = VisitingPrinterFormatter))
  }

  @Test fun simpleScalarQueryTest() {

    val context = createGraph {
      scalar("version")
    }.graphQlInstance

    "{version}" eq context.toGraphQl()
  }


  @Test fun nestedContextTest() {

    val graph = createGraph {

      "query" ofType "Query" definedAs {
        scalar("hello")

        "nested" ofType "Def" definedAs {
          arguments = mapOf(
              "limitTo" to 100,
              "first" to 10)
          scalar("world")
        }

      }
    }.graphQlInstance

    "{query{hello,nested(limitTo: 100,first: 10){world}}}" eq graph.toGraphQl()
  }


  @Test fun fragmentPropertyDefinitionTest() {

    val graph = createGraph {
      "query" ofType "Query" spread {

        arguments = mapOf("first" to 100)
        isNullable = false

        "Type1" fragmentDef {
          scalar("hello")
        }
        "Type2" fragmentDef {
          scalar("world")
        }
      }
    }.graphQlInstance

    "{query(first: 100){... on Type1{hello}... on Type2{world}}}" eq graph.toGraphQl()
  }
}

object VisitingPrinterFormatter : GraphQlFormatter {

  private val standardPrinter: Printer = { VisitingPrinter(it).toString() }

  override val prettyOptimizedPrinter: Printer get() = standardPrinter
  override val printer: Printer get() = standardPrinter
  override val optimizedPrinter: Printer get() = standardPrinter
  override val prettyPrinter: Printer get() = standardPrinter
}