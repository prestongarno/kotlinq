package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import kotlin.reflect.KProperty

internal class TypeStubAdapter<I : QSchemaType, P : QModel<I>, out B : TypeArgBuilder>(val builderInit: ( (TypeArgBuilder) -> B )?)
	: TypeStub<P, I>,
		InitStub<I>,
		QTypeConfigStub<I, B>,
		TypeArgBuilder {

  val args: MutableMap<in String, Any> = HashMap()
  var result: P? = null
  lateinit var property: KProperty<*>

  @Suppress("UNCHECKED_CAST")
  override fun config(): B = builderInit?.invoke(this)?: this as B

	override fun getValue(inst: QModel<*>, property: KProperty<*>): P {
		return this.result!!
	}

	override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeStub<P, I> {
    this.property = property
    return this
  }

	override fun <U : QModel<I>> init(of: () -> U): TypeStub<U, I> {
    @Suppress("UNCHECKED_CAST")
    result = of() as P
    @Suppress("UNCHECKED_CAST")
		return this as TypeStub<U, I>
	}

	override fun <U, T> build(init: () -> U): TypeStub<U, T>
			where U : QModel<T>,
				  T : QSchemaType {
    @Suppress("UNCHECKED_CAST")
    this.result = init() as P
    @Suppress("UNCHECKED_CAST")
    return this as TypeStub<U, T>
	}

	override fun addArg(name: String, value: Any): TypeArgBuilder
			= apply { args.put(name, value) }

}