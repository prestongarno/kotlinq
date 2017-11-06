package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.stubs.UnionConfigStub
import com.prestongarno.ktq.stubs.UnionInitStub
import com.prestongarno.ktq.UnionStub
import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.hooks.FragmentContext
import com.prestongarno.ktq.internal.ValueDelegate
import com.prestongarno.ktq.properties.FragmentProvider
import kotlin.reflect.KProperty

internal sealed class UnionConfigAdapter<out I : QSchemaUnion, A : ArgBuilder>(
    val qproperty: GraphQlProperty,
    objectModel: I,
    val arginit: (ArgBuilder) -> A,
    val config: (A.() -> Unit)? = null
) : QModel<I>(objectModel),
    UnionInitStub<I>,
    UnionConfigStub<I, A>,
    UnionStub,
    ArgBuilder,
    QSchemaUnion {

  open val fragments = mutableSetOf<Fragment>()

  /**
   * Recurse to the base model of the graph */
  override val queue: FragmentProvider get() = model.queue

  private var dispatcher: (I.() -> Unit)? = null

  protected val args: MutableMap<String, Any> = mutableMapOf()

  override fun fragment(what: I.() -> Unit): UnionStub = apply { dispatcher = what }

  override fun provideDelegate(
      inst: QModel<*>,
      property: KProperty<*>
  ): QField<QModel<*>?> = queue(model, dispatcher?: { /* nothing ... */ }, {
    UnionStubImpl(qproperty, reset().toSet(), args.toMap()) as QField<QModel<*>>
  })

  override fun on(init: () -> QModel<*>) {
    queue.addFragment(Fragment(init))
  }

  companion object {

    fun <I : QSchemaUnion> baseObject(objectModel: I): UnionConfigAdapter<I, ArgBuilder> =
        BaseUnionAdapter(objectModel)

    fun <I : QSchemaUnion> create(property: GraphQlProperty, objectModel: I)
        : UnionConfigAdapter<I, ArgBuilder> = UnionAdapterImpl(property, objectModel, { it })

    fun <I : QSchemaUnion, A : ArgBuilder> create(property: GraphQlProperty, objectModel: I, arginit: (ArgBuilder) -> A)
        : UnionConfigAdapter<I, A> = UnionAdapterImpl(property, objectModel, arginit)
  }
}

private class UnionAdapterImpl<out I : QSchemaUnion, A : ArgBuilder>(
    graphqlProperty: GraphQlProperty,
    objectModel: I,
    arginit: (ArgBuilder) -> A,
    config: (A.() -> Unit)? = null
) : UnionConfigAdapter<I, A>(graphqlProperty, objectModel, arginit, config) {

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value) }

  override fun config(on: A.() -> Unit): UnionInitStub<I> {
    return UnionAdapterImpl(qproperty, model, arginit, on)
  }
}

private class BaseUnionAdapter<out I : QSchemaUnion>(model: I)
  : UnionConfigAdapter<I, ArgBuilder>(GraphQlProperty.ROOT, model, { it }) {

  override val queue = FragmentProvider()

  override fun addArg(name: String, value: Any): ArgBuilder = this

  override fun config(on: ArgBuilder.() -> Unit): UnionInitStub<I> = this
}


@ValueDelegate(QModel::class)
private class UnionStubImpl(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<Fragment>,
    override val args: Map<String, Any> = emptyMap()
) : Adapter,
    QField<QModel<*>?>,
    FragmentContext {

  var value: QModel<*>? = null


  override fun accept(result: Any?): Boolean {
    return if (result is JsonObject) {
      value = result["__typename"]?.let { resultType ->
        fragments.find { it.model.graphqlType == resultType }?.model
      }
      return value?.accept(result) == true
    } else false
  }

  override fun toRawPayload(): String = fragments.joinToString( prefix = "{__typename,", postfix = "}") {
    it.model.run {
      "... on " + graphqlType + toGraphql(false)
    }
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): QModel<*>? = value
}

