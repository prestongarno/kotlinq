package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.FieldConfig
import com.prestongarno.ktq.ListArgBuilder
import com.prestongarno.ktq.ListConfig
import com.prestongarno.ktq.ListStub
import com.prestongarno.ktq.QProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.typedListValueFrom
import kotlin.reflect.KProperty

internal class ScalarListAdapter<I, B : ListArgBuilder>(
    property: QProperty,
    val builderInit: (ListArgBuilder) -> B,
    val config: (B.() -> Unit)? = null
) : FieldConfig(property),
    ListStub<I>,
    ListConfig<I, B>,
    ListArgBuilder {

  override fun config(provider: B.() -> Unit): ListStub<I> =
      ScalarListAdapter(graphqlProperty, builderInit, provider)

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Adapter<List<I>> =
      ScalarListStubImpl<I>(
          QProperty.from(property,
              this.graphqlProperty.graphqlType,
              this.graphqlProperty.isList,
              this.graphqlProperty.graphqlName),
          apply { config?.invoke(builderInit(this)) }.args.toMap()
      ).also {
        inst.fields.add(it)
      }

  @Suppress("UNCHECKED_CAST") override fun <T> build(): ListStub<T> = this as ListStub<T>

  override fun addArg(name: String, value: Any): ListArgBuilder = apply { args.put(name, value) }

  override fun toAdapter(): Adapter<*> = ScalarListStubImpl<I>(
      graphqlProperty,
      apply {
        config?.invoke(builderInit(this))
      }.args.toMap())
}

private data class ScalarListStubImpl<I>(
    override val graphqlProperty: QProperty,
    override val args: Map<String, Any>
) : Adapter<List<I>> {

  private val values = mutableListOf<I>()

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<I> = values

  @Suppress("UNCHECKED_CAST") override fun accept(result: Any?): Boolean {
    if (result != null) {
      if (result is List<*>)
        result.filterNotNull().run {
          values.addAll((graphqlProperty.kproperty.typedListValueFrom(this))
              .map { it as I })
        }
      else
        values.addAll(graphqlProperty.kproperty.typedListValueFrom(result).map { it as I })
    }
    return true
  }

  override fun toRawPayload(): String = graphqlProperty.graphqlName +
      if (args.isNotEmpty()) this.args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          } else ""

}
