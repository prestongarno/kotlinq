package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.CustomScalarListArgBuilder
import com.prestongarno.ktq.CustomScalarListConfigStub
import com.prestongarno.ktq.CustomScalarListInitStub
import com.prestongarno.ktq.CustomScalarListStub
import com.prestongarno.ktq.FieldConfig
import com.prestongarno.ktq.Payload
import com.prestongarno.ktq.QProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.custom.InputStreamScalarListMapper
import com.prestongarno.ktq.adapters.custom.QScalarListMapper
import com.prestongarno.ktq.adapters.custom.StringScalarListMapper
import kotlin.reflect.KProperty

internal class CustomScalarListAdapter<E : CustomScalar, P : QScalarListMapper<Q>, Q, out B : CustomScalarListArgBuilder>(
    property: QProperty,
    val builderInit: (CustomScalarListArgBuilder) -> B
) : FieldConfig(property),
    CustomScalarListArgBuilder,
    CustomScalarListConfigStub<E, B>,
    CustomScalarListInitStub<E>,
    CustomScalarListStub<P, Q> {

  override fun accept(result: Any?): Boolean {
    val values = (if (result is List<*>) result else listOf(result)).filterNotNull()
    when (adapter) {
      is InputStreamScalarListMapper<*> -> {
        (adapter as InputStreamScalarListMapper<*>).rawValue =
            values.map { "$it".byteInputStream() }
      }
      is StringScalarListMapper<*> -> {
        (adapter as StringScalarListMapper<*>).rawValue =
            values.map { "$it" }
      }
    }
    return true
  }

  override fun config(): B = builderInit(CustomScalarListAdapter<E, P, Q, B>(graphqlProperty, builderInit))

  internal lateinit var adapter: P

  override fun addArg(name: String, value: Any): Payload = apply { this.args.put(name, value) }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<Q> = adapter.value

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): CustomScalarListStub<P, Q> {
    super.onDelegate(inst, property)
    return this
  }

  override fun <U : QScalarListMapper<A>, A> init(of: U): CustomScalarListStub<U, A> = this.build(of)

  @Suppress("UNCHECKED_CAST") override fun <U : QScalarListMapper<T>, T> build(init: U): CustomScalarListStub<U, T> {
    this.adapter = init as P
    return this as CustomScalarListStub<U, T>
  }
}

