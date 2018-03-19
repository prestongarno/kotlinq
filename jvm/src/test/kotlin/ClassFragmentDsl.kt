package org.kotlinq.jvm

import org.junit.Test


class ClassFragmentDsl {

  @Test fun syntaxIsReadable() {

    class N2Entity(result: GraphQlResult)
      : Data by result.toData() {
      val x by result.integer()
    }

    class NestedEntity(result: GraphQlResult)
      : Data by result.toData() {
      val integerField by result.integer()
      val floatingPointArrayField by result.floatingPoint().asList()
      val foo by result<N2Entity>().asList()
    }
    class MyEntity(result: GraphQlResult)
      : Data by result.toData() {
      val stringField by result.string()
      val nestedEntity by result<NestedEntity>()
    }

    val entityFragment = ::MyEntity {
      MyEntity::nestedEntity on ::NestedEntity {
        NestedEntity::foo spread ::N2Entity
      }
    }

    val actual = entityFragment.toGraphQl(
        pretty = true,
        idAndTypeName = false)
    val expect = """
      |{
      |  nestedEntity {
      |    floatingPointArrayField
      |    foo {
      |      x
      |    }
      |    integerField
      |  }
      |  stringField
      |}
      """.trimMargin("|")
    require(actual == expect) { "Not equal: <$expect> != <$actual>"}
  }
}