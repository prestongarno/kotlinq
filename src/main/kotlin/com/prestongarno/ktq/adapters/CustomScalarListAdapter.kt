package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.CustomScalarListArgBuilder
import com.prestongarno.ktq.CustomScalarListConfigStub
import com.prestongarno.ktq.CustomScalarListInitStub
import com.prestongarno.ktq.CustomScalarListStub
import com.prestongarno.ktq.FieldConfig
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.custom.InputStreamScalarListMapper
import com.prestongarno.ktq.adapters.custom.QScalarListMapper
import com.prestongarno.ktq.adapters.custom.StringScalarListMapper
import kotlin.reflect.KProperty

internal class CustomScalarListAdapter<E : CustomScalar, P : QScalarListMapper<Q>, Q, B : CustomScalarListArgBuilder>(
    property: GraphQlProperty,
    val builderInit: (CustomScalarListArgBuilder) -> B,
    val adapter: P? = null,
    val default: Q? = null,
    val config: (B.() -> Unit)? = null
) : FieldConfig(property),
    CustomScalarListArgBuilder,
    CustomScalarListConfigStub<E, B>,
    CustomScalarListInitStub<E>,
    CustomScalarListStub<P, Q> {

  override fun config(provider: B.() -> Unit): CustomScalarListInitStub<E> =
      CustomScalarListAdapter(graphqlProperty, builderInit, adapter, default, provider)

  override fun addArg(name: String, value: Any): ArgBuilder = apply { this.args.put(name, value) }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<Q>> =
      CustomScalarListStubImpl(GraphQlProperty.from(property,
          this.graphqlProperty.graphqlType,
          this.graphqlProperty.isList,
          this.graphqlProperty.graphqlName),
          apply { config?.invoke(builderInit(this)) }.args.toMap(),
          adapter!!
      ).also {
        inst.fields.add(it)
      }

  @Suppress("UNCHECKED_CAST") override fun <U : QScalarListMapper<A>, A> init(of: U): CustomScalarListStub<U, A> =
      CustomScalarListAdapter<E, U, A, B>(graphqlProperty, builderInit, of, default as A?, config)

  @Suppress("UNCHECKED_CAST") override fun <U : QScalarListMapper<T>, T> build(init: U): CustomScalarListStub<U, T> =
      CustomScalarListAdapter<E, U, T, B>(graphqlProperty, builderInit, init, default as T?, config)

  override fun toAdapter(): Adapter = CustomScalarListStubImpl(graphqlProperty, args.toMap(), adapter!!)
}

private data class CustomScalarListStubImpl<out Q>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any> = emptyMap(),
    val adapter: QScalarListMapper<Q>
) : QField<List<Q>>,
    Adapter {

  override fun toRawPayload(): String = qproperty.graphqlName +
      if (args.isNotEmpty()) this.args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          } else ""

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<Q> {
    return adapter.value
  }

  override fun accept(result: Any?): Boolean {
    val values = (result as? List<*> ?: listOf(result)).filterNotNull()
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
}
