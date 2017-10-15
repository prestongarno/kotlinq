package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.CustomScalarArgBuilder
import com.prestongarno.ktq.CustomScalarConfigStub
import com.prestongarno.ktq.CustomScalarInitStub
import com.prestongarno.ktq.CustomStub
import com.prestongarno.ktq.FieldConfig
import com.prestongarno.ktq.Payload
import com.prestongarno.ktq.QProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.custom.InputStreamScalarMapper
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import com.prestongarno.ktq.adapters.custom.StringScalarMapper
import com.prestongarno.ktq.formatAs
import kotlin.reflect.KProperty

internal class CustomScalarAdapter<E : CustomScalar, P : QScalarMapper<Q>, Q, out B : CustomScalarArgBuilder>(
    property: QProperty,
    val builderInit: (CustomScalarArgBuilder) -> B
) : FieldConfig(property),
    CustomScalarArgBuilder,
    CustomScalarConfigStub<E, B>,
    CustomScalarInitStub<E>,
    CustomStub<P, Q> {

  override fun config(): B = builderInit(CustomScalarAdapter<E, P, Q, B>(graphqlProperty, builderInit))

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
  ): CustomStub<P, Q> = CustomScalarStubImpl(QProperty.from(property,
      this.graphqlProperty.graphqlType,
      this.graphqlProperty.isList,
      this.graphqlProperty.graphqlName),
      args.toMap(),
      adapter)

  override fun <U : QScalarMapper<A>, A> init(of: U): CustomStub<U, A> = this.build(of)

  @Suppress("UNCHECKED_CAST") override fun <U : QScalarMapper<T>, T> build(init: U): CustomStub<U, T> {
    this.adapter = init as QScalarMapper<Q>
    return this as CustomStub<U, T>
  }
}

private data class CustomScalarStubImpl<P : QScalarMapper<Q>, Q>(
    override val graphqlProperty: QProperty,
    override val args: Map<String, Any> = emptyMap(),
    val adapter: QScalarMapper<Q>
) : Adapter,
    CustomStub<P, Q> {

  override fun getValue(inst: QModel<*>, property: KProperty<*>): Q = adapter.value

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): CustomStub<P, Q> {
    throw IllegalStateException()
  }

  override fun accept(result: Any?): Boolean {
    when (adapter) {
      is InputStreamScalarMapper<*> -> adapter.rawValue = "${result ?: ""}".byteInputStream()
      is StringScalarMapper<*> -> adapter.rawValue = "${result ?: ""}"
      else -> throw IllegalArgumentException("No such adapter supported")
    }
    return true
  }

  override fun toRawPayload(): String = graphqlProperty.graphqlName +
      if (args.isNotEmpty()) this.args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          } else ""
}
