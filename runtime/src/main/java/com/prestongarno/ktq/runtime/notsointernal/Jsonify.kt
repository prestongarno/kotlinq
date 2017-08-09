package com.prestongarno.ktq.runtime.notsointernal

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.ktq.runtime.GraphType
import com.prestongarno.ktq.runtime.delegates.GraphProvider
import com.prestongarno.ktq.runtime.delegates.QScalarDelegate

public object Jsonify {

	private val parser : Parser = Parser()

	fun <T: GraphType> initializeValues(instance: T, response: String): T {
		val split : JsonObject = parser.parse(response.byteInputStream()) as JsonObject
		val res : MutableMap<String, Any?> = HashMap(instance.fields.size + 1, 0.99f)
		return recursiveMap(instance, res, split)
	}

	private fun <T: GraphType> recursiveMap(instance: T, res: MutableMap<String, Any?>, split: Map<String, Any?>) : T {
		instance.fields.map {
			when (it) {
				is QScalarDelegate<*> -> res.put(it.fieldName, it.mapper.invoke(split.getValue(it.fieldName).toString())?: "")
				is GraphProvider<*> -> {
					@Suppress("UNCHECKED_CAST") val nested = split.getValue(it.fieldName) as Map<String, Any?>
					res.put(it.fieldName, recursiveMap(it.value, HashMap(instance.fields.size + 1, 0.99f), nested))
				}
				else -> throw IllegalArgumentException("Unexpected field '${it.fieldName}', result value was '${split.get(it.fieldName)}'")
			}
		}
		instance.values = res.toMap()
		return instance

	}
}
