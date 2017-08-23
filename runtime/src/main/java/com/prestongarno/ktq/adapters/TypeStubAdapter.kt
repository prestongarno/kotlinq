package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import kotlin.reflect.KProperty

/**
 * These generics might as well not exist, just another set of generics to remember
 */
internal class TypeStubAdapter<I : QType, P : QModel<I>, out A : TypeArgBuilder>(

		private var argBuilder: A? = null)

	: TypeStub<P, I>,
		InitStub<I>,
		ConfigType<I, A>,
		TypeArgBuilder {

	override fun getValue(inst: QModel<*>, property: KProperty<*>): P {
		return this.result!!
	}

	override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeStub<P, I> = this

	override fun <U : QModel<I>> init(of: () -> U): TypeStub<U, I> {
		result = of.invoke() as P
		@Suppress("UNCHECKED_CAST")
		return this as TypeStub<U, I>
	}

	override fun <U, T> build(init: () -> U): TypeStub<U, T>
			where U : QModel<T>,
				  T : QType {
		this.result = init.invoke() as P
		return this as TypeStub<U, T>
	}


	val args: MutableMap<in String, Any> = HashMap()

	var result: P? = null

	override fun config(): A {
		@Suppress("UNCHECKED_CAST")
		return if (argBuilder == null)
			this as A
		else argBuilder as A
	}

	override fun addArg(name: String, value: Any): TypeArgBuilder
			= apply { args.put(name, value) }

}