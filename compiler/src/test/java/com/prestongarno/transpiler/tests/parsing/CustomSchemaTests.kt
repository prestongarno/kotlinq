package com.prestongarno.transpiler.tests.parsing


import com.prestongarno.transpiler.QCompiler
import java.io.File
import org.junit.Assert
import org.junit.Test
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import java.lang.management.ManagementFactory
import java.util.concurrent.TimeUnit
import javax.script.*

class CustomSchemaTests {
  @Test
  fun testConflictingOverridesPass() {
    val file = this::class.java.classLoader.getResource("sample.schema.graphqls")
    QCompiler.initialize("SampleOne")
        .packageName("com.prestongarno.ktq")
        .compile(File(file.path))
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

