package org.kotlinq.entities.print

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.api.Kind
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
        PropertyInfo.named("hello")
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

    println(query.toGraphQl(pretty = true))

  }

}

