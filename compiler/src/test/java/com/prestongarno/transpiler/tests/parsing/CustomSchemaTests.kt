package com.prestongarno.transpiler.tests.parsing


import com.prestongarno.transpiler.QCompiler
import java.io.File
import org.junit.Test

class CustomSchemaTests {
  @Test
  fun testConflictingOverridesPass() {
    val file = this::class.java.classLoader.getResource("sample.schema.graphqls")
    QCompiler.initialize("SampleOne")
        .packageName("com.prestongarno.ktq")
        .compile(File(file.path))
        .writeToFile("/Users/admin/IdeaProjects/ktq/runtime/src/test/java/")
  }

  @Test
  fun testYelp() {
    val file = this::class.java.classLoader.getResource("yelp.graphqls")
    QCompiler.initialize("YelpGraphql")
        .packageName("com.prestongarno.ktq.yelp")
        .compile(File(file.path))
        .result {}
        .writeToFile("/Users/admin/IdeaProjects/ktq/runtime/src/test/java/")
  }
}

