package com.prestongarno.ktq.runtime

import kotlin.reflect.KProperty

/**
 * The root class for all types, trying to simplify the delegates process
 */

open class QueryData(protected var map: Map<in String, Any?>)
