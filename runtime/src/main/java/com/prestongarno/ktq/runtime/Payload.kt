package com.prestongarno.ktq.runtime

/**
 * Represents all field parameterization/mutation payload data
 *
 * Only needed by codegen -> type implementation:
 * 1. each method/ctor arg adds pair of <NameOfArg, ValueOfArg> to list
 * 2. returns list as Payload#get() method override
 */
interface Payload {
	fun get(): List<Pair<String, String>>
}