package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.*
import kotlin.reflect.*

interface QType {

	fun <T> stub(): Stub<T> = ScalarStub()

	fun <T> nullableStub(): Stub<T> = ScalarStub()

	fun <U : QModel<T>, T : QType> stub(of: () -> T): TypeStub<U, T> = TypeStubAdapter<T, U, TypeArgBuilder>() as TypeStub<U, T>

	/****************************************
	 *         Nested types fields          *
	 ****************************************/

	/** Stub with no input for nested types, requiring only an initializer
	 */
	fun <T : QType> typeStub(): InitStub<T> = TypeStubAdapter<T, QModel<T>, TypeArgBuilder>()

	/** Configurable stub for primitives
	 */
	fun <A : ArgBuilder<T>, T> configStub(argBuilder: A): Config<A, T> = ScalarStub(argBuilder)

	/** Configurable stubs for types
	 */
	fun <T : QType, A : TypeArgBuilder> typeConfigStub(argBuilder: A)
			: ConfigType<A, T> = TypeStubAdapter(argBuilder)
}

interface InitStub<T : QType> {
	fun <U : QModel<T>> init(of: () -> U): TypeStub<U, T>
}

interface Config<out A : ArgBuilder<T>, T> {
	fun config(): A
}

interface ConfigType<out A : TypeArgBuilder, T : QType> {
	fun config(): A
}

interface Stub<T> {
	operator fun getValue(inst: QModel<*>, property: KProperty<*>): T

	operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T>
}

interface NullableStub<T> {
	operator fun getValue(inst: QModel<*>, property: KProperty<*>): T?

	operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T>
}

interface TypeStub<U, T> where  U : QModel<T>, T : QType {
	operator fun getValue(inst: QModel<*>, property: KProperty<*>): U

	operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeStub<U, T>
}












