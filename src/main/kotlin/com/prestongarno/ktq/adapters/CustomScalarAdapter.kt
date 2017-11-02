package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.CustomScalarArgBuilder
import com.prestongarno.ktq.CustomScalarConfigStub
import com.prestongarno.ktq.CustomScalarInitStub
import com.prestongarno.ktq.CustomStub
import com.prestongarno.ktq.PreDelegate
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.custom.InputStreamScalarMapper
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import com.prestongarno.ktq.adapters.custom.StringScalarMapper
import kotlin.reflect.KProperty

internal class CustomScalarAdapter<E : CustomScalar, P : QScalarMapper<Q>, Q, B : CustomScalarArgBuilder>(
    qproperty: GraphQlProperty,
    private val builderInit: (CustomScalarArgBuilder) -> B,
    private var default: Q? = null,
    val init: P? = null,
    val config: (B.() -> Unit)? = null
) : PreDelegate(qproperty),
    CustomScalarArgBuilder,
    CustomScalarConfigStub<E, B>,
    CustomScalarInitStub<E>,
    CustomStub<P, Q> {


  override fun withDefault(value: Q): CustomStub<P, Q> =
      CustomScalarAdapter<E, P, Q, B>(qproperty,builderInit, value, init, config)

  override fun config(provider: B.() -> Unit): CustomScalarInitStub<E> =
      CustomScalarAdapter(qproperty, builderInit, default, init, provider)

  private lateinit var adapter: QScalarMapper<Q>

  override fun addArg(
      name: String,
      value: Any
  ): ArgBuilder = apply { this.args.put(name, value) }

  override fun provideDelegate(
      inst: QModel<*>,
      property: KProperty<*>
  ): QField<Q> = CustomScalarStubImpl(qproperty, apply {
    config?.invoke(builderInit(this))
  }.args.toMap(), adapter, default).also {
    inst.fields.add(it) }

  @Suppress("UNCHECKED_CAST")
  override fun <U : QScalarMapper<A>, A> init(init: U): CustomStub<U, A> =
      CustomScalarAdapter<E, U, A, B>(qproperty,builderInit, default?.let {
        if (it != null && it as? A == true) it as A else null
      }, init, config)

  @Suppress("UNCHECKED_CAST") override fun <U : QScalarMapper<T>, T> build(init: U): CustomStub<U, T> =
      CustomScalarAdapter<E, U, T, B>(qproperty,builderInit, default?.let {
        if (it != null && it as? T == true) it as T else null
      }, init, config)

}

private data class CustomScalarStubImpl<out Q>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any> = emptyMap(),
    val adapter: QScalarMapper<Q>,
    val default: Q?
) : Adapter,
    QField<Q> {

  override fun getValue(inst: QModel<*>, property: KProperty<*>): Q = adapter.value ?: default!!

  override fun accept(result: Any?): Boolean {
    when (adapter) {
      is InputStreamScalarMapper<*> -> adapter.rawValue = "${result ?: ""}".byteInputStream()
      is StringScalarMapper<*> -> adapter.rawValue = "${result ?: ""}"
      else -> throw IllegalArgumentException("No such adapter supported")
    }
    return true
  }

  override fun toRawPayload(): String = qproperty.graphqlName +
      if (args.isNotEmpty()) this.args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          } else ""
}
