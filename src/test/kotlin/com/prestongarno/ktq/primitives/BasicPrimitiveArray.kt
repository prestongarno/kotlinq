package com.prestongarno.ktq.primitives

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.stubs.BooleanArrayDelegate
import com.prestongarno.ktq.stubs.FloatArrayDelegate
import com.prestongarno.ktq.stubs.IntArrayDelegate
import com.prestongarno.ktq.stubs.StringArrayDelegate
import org.junit.Test

object AnonymousClassroom : QType {

  val studentNames: StringArrayDelegate.Query
      by QScalarArray.String.stub()

  val studentAges: IntArrayDelegate.Query
      by QScalarArray.Int.stub()

  val studentGpa: FloatArrayDelegate.Query
      by QScalarArray.Float.stub()

  val studentPassing: BooleanArrayDelegate.Query
      by QScalarArray.Boolean.stub()
}

class BasicPrimitiveArray {

  @Test fun `string array is possible`() {

    val query = object : QModel<AnonymousClassroom>(AnonymousClassroom) {
      val names by model.studentNames
    }

    query::names.returnType.classifier eq Array<String>::class
    query.toGraphql(false) eq "{studentNames}"
  }

  @Test fun `integer array is possible`() {

    val query = object : QModel<AnonymousClassroom>(AnonymousClassroom) {
      val ages by model.studentAges
    }
    query::ages.returnType.classifier eq IntArray::class
    query.toGraphql(false) eq "{studentAges}"
  }

  @Test fun `float array is possible`() {

    val query = object : QModel<AnonymousClassroom>(AnonymousClassroom) {
      val gpas by model.studentGpa
    }
    query::gpas.returnType.classifier eq FloatArray::class
    query.toGraphql(false) eq "{studentGpa}"
  }

  @Test fun `boolean array is possible`() {
    val query = object : QModel<AnonymousClassroom>(AnonymousClassroom) {
      val passing by model.studentPassing
    }
    query::passing.returnType.classifier eq BooleanArray::class
    query.toGraphql(false) eq "{studentPassing}"
  }

  @Test fun `arrays of all types with empty invocation blocks is possible`() {

    val query = object : QModel<AnonymousClassroom>(AnonymousClassroom) {

      val names by model.studentNames { /* nothing */ }

      val ages by model.studentAges { /* nothing */ }

      val gpas by model.studentGpa { /* nothing */ }

      val passing by model.studentPassing { /* nothing */ }

    }

    query.toGraphql(false) eq "{studentNames,studentAges,studentGpa,studentPassing}"
  }

  @Test fun `arrays of all types with dynamic arguments is possible`() {

    val query = object : QModel<AnonymousClassroom>(AnonymousClassroom) {

      val names by model.studentNames {
        config {
          "Hello" with "World"
        }
      }

      val ages by model.studentAges {
        config { "NumberArgument" with 9000 }
      }

      val gpas by model.studentGpa {
        config { "BooleanArgument" with true }
      }

      val passing by model.studentPassing {
        config { "FloatArgument" with 5.005f }
      }

    }

    query.toGraphql(false) eq
        """{studentNames(Hello: \"World\"),studentAges(NumberArgument: 9000),""" +
          """studentGpa(BooleanArgument: true),studentPassing(FloatArgument: 5.005f)}"""
    println(query.toGraphql())
  }
}