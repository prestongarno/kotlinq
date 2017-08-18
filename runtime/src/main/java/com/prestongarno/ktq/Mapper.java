package com.prestongarno.ktq;

import kotlin.reflect.KProperty;

abstract class Mapper<T> {

	KProperty<?> property;

	Mapper(KProperty<?> property) {
		this.property = property;
	}

	Mapper(T provided, KProperty<?> property) {
		this.property = property;
		this.value = provided;
	}

	T value;

}
