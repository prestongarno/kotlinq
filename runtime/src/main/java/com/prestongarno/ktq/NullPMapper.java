package com.prestongarno.ktq;

import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a nullable backing field
 *
 * @param <T> The erased type which this field represents
 */
class NullPMapper<T> extends Mapper<T> implements NullableStub<T> {

	static <T> NullPMapper<T> create(KProperty<?> prop) {
		return new NullPMapper<>(prop);
	}
	static <T> NullPMapper<T> create(T provided, KProperty<?> prop) {
		return new NullPMapper<>(provided, prop);
	}

	private NullPMapper(KProperty<?> prop) {
		super(prop);
	}

	private NullPMapper(T provided, KProperty<?> prop) {
		super(provided, prop);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getValue(@NotNull QType foo, @NotNull KProperty<?> property) {
		if(value == null)
			value = (T) Tracker.INSTANCE.getResult(foo, this);
		return value;
	}

	@NotNull
	@Override
	public <R extends QType> NullableStub<T> provideDelegate(@NotNull R inst, @NotNull KProperty<?> property) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "NullablePMapper :: " + super.toString();
	}

	@NotNull
	@Override
	public <T1 extends QType, U> Stub<U> mapDirect(@NotNull Function1<? super T1, ? extends Stub<U>> function) {
		throw new UnsupportedOperationException("");
	}
}
