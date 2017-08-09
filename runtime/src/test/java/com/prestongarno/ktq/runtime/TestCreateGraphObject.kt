package com.prestongarno.ktq.runtime

import com.prestongarno.ktq.runtime.notsointernal.Jsonify
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


/**
 * Sample concrete GraphTypes for unit tests
 */
open class Sample : Repository() {
	public override val name by string()
	public override val description by string()
	public override val isFork by bool()
}

class SampleTwo : Sample() {
	val SubUbi by field { NestedTest() }
}

class NestedTest : GraphType() {
	val nestedName by string()
	val nestedInt by int()
}


class TestCreateGraphObject {
	@Test
	fun testGenerateSampleRepositoryObject() {
		val instance = Sample()
		val response =
				"{" +
						"\"name\" : \"TestObject\"" +
						"\"description\" : \"A sample test object parsed from json & mapped to fields\"" +
						"\"isFork\" : false" +
						"}"
		println(response)
		val result = Jsonify.initializeValues(instance, response)
		println(result.name)
		assertTrue(result.name == "TestObject")
		assertTrue(result.description == "A sample test object parsed from json & mapped to fields")
		assertFalse(result.isFork)
	}

	@Test
	fun testNestedObjectMapping() {
		val instance = SampleTwo()
		val response = """{
		  "name" : "TestObject",
		  "description" : "A sample test object parsed from json and mapped to fields",
		  "isFork" : false,
		  "SubUbi" : {
			"nestedName" : "Success",
			"nestedInt" : 69
		  }
		}"""
		println(response)
		val result = Jsonify.initializeValues(instance, response)
		result.fields.map { println(result.values?.get(it.fieldName)) }
		assertNotNull(result.SubUbi)
		assertEquals(result.SubUbi.nestedInt, 69)
		assertEquals(result.SubUbi.nestedName, "Success")
	}
}

