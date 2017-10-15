package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.FieldAdapter
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.QProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QTypeConfigStub
import com.prestongarno.ktq.TypeArgBuilder
import com.prestongarno.ktq.TypeStub
import com.prestongarno.ktq.formatAs
import com.prestongarno.ktq.internal.ModelProvider
import kotlin.reflect.KProperty

/**
 * This class represents a stub for a non-leaf type (aka an object) fragment a graph */
internal class TypeStubAdapter<I : QSchemaType, P : QModel<I>, out B : TypeArgBuilder>(
    property: QProperty,
    val builderInit: (TypeArgBuilder) -> B
) : FieldAdapter(property),
    TypeStub<P, I>,
    InitStub<I>,
    QTypeConfigStub<I, B>,
    TypeArgBuilder,
    ModelProvider {

  override fun accept(result: Any?): Boolean {
    value.resolved = true
    return result is JsonObject
        && value.fields.filterNot { f ->
      f.accept(result[f.graphqlProperty.graphqlName])
    }.isEmpty() && value.resolved
  }

  override lateinit var value: P

  override fun config(): B = builderInit(TypeStubAdapter<I, P, B>(graphqlProperty, builderInit))

  override fun getValue(inst: QModel<*>, property: KProperty<*>): P = this.value

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeStub<P, I> =
      apply { super.onDelegate(inst, property) }

  @Suppress("UNCHECKED_CAST") override fun <U : QModel<I>> init(of: () -> U): TypeStub<U, I>
      = apply { this.value = of() as P } as TypeStub<U, I>

  @Suppress("UNCHECKED_CAST") override fun <U : QModel<T>, T : QSchemaType> build(init: () -> U): TypeStub<U, T>
      = apply { this.value = init() as P } as TypeStub<U, T>

  override fun addArg(name: String, value: Any): TypeArgBuilder = apply { args.put(name, value) }

}

internal data class TypeStub<out I : QSchemaType, out P : QModel<I>>(
    override val graphqlProperty: QProperty,
    override val value: P,
    override val args: Map<String, Any> = emptyMap()) : Adapter, ModelProvider {

  override fun accept(result: Any?): Boolean {
    value.resolved = true
    return result is JsonObject
        && value.fields.filterNot { f ->
      f.accept(result[f.graphqlProperty.graphqlName])
    }.isEmpty() && value.resolved
  }

  override fun toRawPayload(): String = graphqlProperty.graphqlName +
      if (args.isNotEmpty()) this.args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          } else "" + value.toGraphql(false)

  override fun onDelegate(inst: QModel<*>, property: KProperty<*>) {
    inst.fields.add(this)
  }
}