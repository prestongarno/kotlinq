import org.junit.Before
import org.junit.Test
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.Printer
import org.kotlinq.printer.VisitingPrinter

class MockGraphPrinterTests {

  @Before fun injectPrinter() {
    //Configuration.configure(Configuration.Companion.Builder(printer = VisitingPrinterFormatter))
  }

  @Test fun simpleScalarQueryTest() {

    val context = createGraph {
      scalar("version")
    }

    "{version}" eq context.graphQlInstance.toGraphQl()
  }


  @Test fun nestedContextTest() {

    val graph = createGraph {
      "query" ofType "Query" definedAs {
        scalar("hello")

        "nested" ofType "Def" definedAs {
          arguments = mapOf(
              "limitTo" to 100,
              "first" to 10
          )
          scalar("hello")
          scalar("world")
        }
      }
    }

    println(graph.graphQlInstance.toGraphQl(pretty = true, extractFragments = false))
  }


}

object VisitingPrinterFormatter : GraphQlFormatter {

  private val standardPrinter: Printer = { VisitingPrinter(it).toString() }

  override val prettyOptimizedPrinter: Printer get() = standardPrinter
  override val printer: Printer get() = standardPrinter
  override val optimizedPrinter: Printer get() = standardPrinter
  override val prettyPrinter: Printer get() = standardPrinter
}