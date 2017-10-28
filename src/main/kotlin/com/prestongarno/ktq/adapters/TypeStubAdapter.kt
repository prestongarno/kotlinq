package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.BaseFieldAdapter
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.hooks.TypeConfiguration
import com.prestongarno.ktq.TypeStub
import com.prestongarno.ktq.hooks.ModelProvider
import com.prestongarno.ktq.hooks.nullPointer
import kotlin.reflect.KProperty

/**
 * This class represents a createStub for a non-leaf createTypeStub (aka an object) fragment a graph */
internal class TypeStubAdapter<I : QSchemaType, P : QModel<I>, B : ArgBuilder>(
    property: GraphQlProperty,
    val builderInit: (ArgBuilder) -> B,
    val init: () -> P = nullPointer(),
    val config: (B.() -> Unit)? = null
) : BaseFieldAdapter(property),
    TypeStub<P, I>,
    InitStub<I>,
    TypeConfiguration<I, B>,
    ArgBuilder {

  override fun config(provider: B.() -> Unit): InitStub<I> =
      TypeStubAdapter(graphqlProperty, builderInit, this.init, provider)

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<P> =
      TypeStubImpl(GraphQlProperty.from(property,
          this.graphqlProperty.graphqlType,
          this.graphqlProperty.isList,
          this.graphqlProperty.graphqlName),
          init,
          apply { config?.invoke(builderInit(this)) }.args.toMap()
      ).also {
        inst.fields.add(it)
      }

  override fun <U : QModel<I>> init(init: () -> U): TypeStub<U, I> =
      TypeStubAdapter(graphqlProperty, builderInit, init, config)

  override fun toAdapter(): Adapter = TypeStubImpl(this.graphqlProperty, init, args.toMap())

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value) }

}

private data class TypeStubImpl<out I : QSchemaType, out P : QModel<I>>(
    override val qproperty: GraphQlProperty,
    val init: () -> P,
    override val args: Map<String, Any> = emptyMap()
) : QField<P>,
    Adapter,
    ModelProvider {

  override val value by lazy(init)

  override fun getValue(inst: QModel<*>, property: KProperty<*>): P = value

  override fun accept(result: Any?): Boolean {
    value.resolved = true
    return result is JsonObject
        && value.fields.filterNot { f ->
      f.accept(result[f.qproperty.graphqlName])
    }.isEmpty() && value.resolved
  }

  override fun toRawPayload(): String = qproperty.graphqlName +
      if (args.isNotEmpty()) this.args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          } else "" + value.toGraphql(false)

}