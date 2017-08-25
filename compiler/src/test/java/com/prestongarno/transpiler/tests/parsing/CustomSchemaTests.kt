package com.prestongarno.transpiler.tests.parsing

import org.junit.Test
import com.prestongarno.transpiler.QCompiler
import java.io.File

class CustomSchemaTests {
  @Test
  fun testConflictingOverridesPass() {
    val file = this::class.java.classLoader.getResource("sample.schema.graphqls")
    val qCompiler = QCompiler()
    val content = qCompiler.compile(File(file.path))
    qCompiler.generateKotlinTypes(content, "/Users/admin/IdeaProjects/ktq/runtime/src/test/java/", "com.prestongarno.ktq", "SampleCompile")
  }
}