package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.CustomScalarArgBuilder
import com.prestongarno.ktq.CustomScalarConfigStub
import com.prestongarno.ktq.CustomScalarInitStub
import com.prestongarno.ktq.CustomStub
import com.prestongarno.ktq.Payload
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.custom.InputStreamScalarMapper
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import com.prestongarno.ktq.adapters.custom.StringScalarMapper
import kotlin.reflect.KProperty

internal class CustomScalarAdapter<E : CustomScalar, P : QScalarMapper<Q>, Q, out B : CustomScalarArgBuilder>(
    fieldName: String, val builderInit: (CustomScalarArgBuilder) -> B
) : FieldAdapter(fieldName),
    CustomScalarArgBuilder,
    CustomScalarConfigStub<E, B>,
    CustomScalarInitStub<E>,
    CustomStub<P, Q> {

  override fun accept(result: Any?): Boolean {
    when (adapter) {
      is InputStreamScalarMapper<*> -> (adapter as InputStreamScalarMapper)
          .rawValue = "${result ?: ""}".byteInputStream()
      is StringScalarMapper<*> -> (adapter as StringScalarMapper)
          .rawValue = "${result ?: ""}"
    }
    return true
  }

  override fun config(): B = builderInit(CustomScalarAdapter<E, P, Q, B>(fieldName, builderInit))

  private lateinit var adapter: QScalarMapper<Q>

  override fun addArg(
      name: String,
      value: Any): Payload = apply { this.args.put(name, value) }

  override fun getValue(
      inst: QModel<*>,
      property: KProperty<*>
  ): Q = adapter.value

  override fun <R : QModel<*>> provideDelegate(
      inst: R,
      property: KProperty<*>
  ): CustomStub<P, Q> = apply { inst.fields.add(this) }

  override fun <U : QScalarMapper<A>, A> init(of: U): CustomStub<U, A> = this.build(of)

  @Suppress("UNCHECKED_CAST") override fun <U : QScalarMapper<T>, T> build(init: U): CustomStub<U, T> {
    this.adapter = init as QScalarMapper<Q>
    return this as CustomStub<U, T>
  }
}
