package com.prestongarno.ktq.runtime

abstract class CustomScalar : GraphType() {
	protected val rawValue: String by lazy { throw SchemaStub() }
}