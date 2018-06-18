package org.kotlinq.entities.print

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.introspection.Kind
import org.kotlinq.api.Printer
import org.kotlinq.api.PrintingConfiguration
import org.kotlinq.api.PropertyInfo
import org.kotlinq.entities.TestFragmentBuilder.Companion.fragment
import org.kotlinq.eq

class PrinterConfigurationTests {

  val query = fragment("Query") {
    scalar("hello")
  }

  @Test fun foo() {

    query.typeName eq "Query"
    query.graphQlInstance.properties["hello"]!!.propertyInfo eq
        PropertyInfo.propertyNamed("hello")
            .typeKind(Kind.string)
            .build()

    assertThat(query.toGraphQl(pretty = false))
        .isEqualTo("{id,__typename,hello}")
  }

  @Test fun bar() {


    Printer.fromConfiguration(PrintingConfiguration.PRETTY)
        .toBuilder()
        .metaStrategy(
            Printer.MetaStrategy.builder()
                .include("TODO")
                .includeId()
                .includeTypename()
                .build()
        ).build()
        .printFragmentToString(query)

        .let {

          val expect = """
                |{
                |  TODO
                |  id
                |  __typename
                |  hello
                |}
                """.trimMargin("|")

          assertThat(it).isEqualTo(expect)
        }

  }

  @Test fun baz() {

    val standard = Printer.transformationBuilder()
        .build()

    val printer = standard.toBuilder()
        .evalFieldName { standard.fieldNameEval(it) + "!!!" }
        .build()

    val query = fragment("Query") {
      scalar("foo")
      scalar("bar")
      scalar("baz")
    }

    // interrestingg
    assertThat(printer.printFragmentToString(query))
        .isEqualTo("{id!!!,__typename!!!,foo!!!,bar!!!,baz!!!}")
  }

  @Test fun nestedFragmentTest() {
    val query = fragment {
      scalar("foo")
      scalar("bar")
      "baz" on {
        scalar("bazbar")
      }
    }

    val configuration = PrintingConfiguration.builder()
        .pretty(true)
        .indent("++++")
        .lcurlyChar('<')
        .rcurlyChar('>')
        .metaStrategy(Printer.MetaStrategy.NONE)
        .build()
    val prettyPrinter = configuration.let(Printer.Companion::fromConfiguration)

    val unPrettyPrinter = configuration.toBuilder()
        .pretty(false)
        .build()
        .let(Printer.Companion::fromConfiguration)

    val expect = """
      <
      ++++foo
      ++++bar
      ++++baz <
      ++++++++bazbar
      ++++>
      >
      """.trimIndent()

    assertThat(prettyPrinter.printFragmentToString(query))
        .isEqualTo(expect)

    assertThat(unPrettyPrinter.printFragmentToString(query))
        .isEqualTo("<foo,bar,baz<bazbar>>")
  }

}

