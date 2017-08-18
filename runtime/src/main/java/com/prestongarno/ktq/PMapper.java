package com.prestongarno.ktq;

import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

class PMapper<T> extends Mapper<T> implements Stub<T> {

	@NotNull
	static <T> PMapper<T> create(KProperty<?> prop) {
		return new PMapper<>(prop);
	}

	@NotNull
	public static <T> PMapper<T> create(T provided, @NotNull KProperty<?> property) {
		return new PMapper<>(provided, property);
	}

	private PMapper(KProperty<?> prop) {
		super(prop);
	}

	private PMapper(T value, KProperty<?> prop) {
		super(value, prop);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getValue(@NotNull QType foo, @NotNull KProperty<?> property) {
		if (value == null)
			value = (T) Tracker.INSTANCE.getResult(foo, this);
		return value;
	}

	@NotNull
	@Override
	public <R extends QType> PMapper<T> provideDelegate(@NotNull R inst, @NotNull KProperty<?> property) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "PMapper :: " + super.toString();
	}

	@NotNull
	@Override
	public <T1 extends QType, U> Stub<U> mapDirect(@NotNull Function1<? super T1, ? extends Stub<U>> function) {
		throw new IllegalStateException("null");
	}

}


