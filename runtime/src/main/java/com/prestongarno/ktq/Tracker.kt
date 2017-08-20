package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.Mapper
import java.util.*
import kotlin.collections.LinkedHashMap

/**
 * This class stores all of the information about types
 * and which ones are delegating their properties to the superinterface
 */
internal object Tracker {
	internal val global : MutableMap<QType, LinkedHashMap<Mapper<*>, Any?>> = WeakHashMap()

	fun <T> putProperty(inst: QType, property: Mapper<*>, value: T? = null) {
		var values = global[inst]
		if(values == null) {
			values = LinkedHashMap()
			global.put(inst, values)
		}
		values.put(property, value)
	}


	fun onResult(inst: QType, raw: String) {
		println("On Result received: $raw")
		Jsonify.initializeValues(inst,
								 raw,
								 global.get(inst)?: emptyMap<Mapper<*>, Any?>()
										 as MutableMap<Mapper<*>, Any?>)
	}

	fun getResult(inst: QType, mapper: Mapper<*>) = global[inst]?.get(mapper)
}

