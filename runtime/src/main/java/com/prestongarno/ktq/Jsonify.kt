package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser

internal object Jsonify {

	private val parser: Parser = Parser()

	fun <T : QType> initializeValues(instance: T, response: String, fields: MutableMap<Mapper<*>, Any?>): T {
		val split: JsonObject = parser.parse(response.byteInputStream()) as JsonObject
		return recursiveMap(instance, fields, split)
	}

	private fun <T : QType> recursiveMap(instance: T,
										 fields: MutableMap<Mapper<*>, Any?>,
										 split: Map<String, Any?>): T {
		fields.map { entry ->
			when (entry.value) {
				is QType -> Pair(entry.key,
								 recursiveMap<QType>(
										 entry.value as QType,
										 Tracker.global.get(entry.value as QType) ?: HashMap<Mapper<*>, Any?>(0, 0f),
										 split[entry.key.property?.name] as Map<String, Any?>? ?: emptyMap<String, Any?>()))
				else     -> Pair(entry.key, split.get(entry.key.property?.name))
			}
		}.forEach { fields.put(it.first, it.second) }
		return instance
	}
}