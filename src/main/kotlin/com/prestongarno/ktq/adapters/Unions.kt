package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QUnionType
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.stubs.UnionConfigStub
import com.prestongarno.ktq.stubs.UnionFragment
import com.prestongarno.ktq.UnionStub
import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.hooks.FragmentContext
import com.prestongarno.ktq.internal.ValueDelegate
import com.prestongarno.ktq.properties.FragmentProvider
import kotlin.reflect.KProperty

@PublishedApi internal sealed class UnionConfigAdapter<out I : QUnionType, A : ArgBuilder>(
    val qproperty: GraphQlProperty,
    objectModel: I,
    val config: (A.() -> Unit)? = null
) : QModel<I>(objectModel),
    UnionFragment<I>,
    UnionConfigStub<I, A>,
    UnionStub,
    QUnionType {

  open val fragments = mutableSetOf<Fragment>()

  /**
   * Recurse to the base model of the graph */
  override val queue: FragmentProvider get() = model.queue

  private var dispatcher: (I.() -> Unit)? = null

  protected val args: MutableMap<String, Any> = mutableMapOf()

  //override fun fragment(what: I.() -> Unit): UnionStub = apply { dispatcher = what }

  override fun provideDelegate(
      inst: QModel<*>,
      property: KProperty<*>
  ): QField<QModel<*>?> = queue(model, dispatcher ?: { /* nothing ... */ }, {
    UnionStubImpl(qproperty, reset().toSet(), args.toMap()) as QField<QModel<*>> // Bug?
  })

  override fun on(init: () -> QModel<QType>) {
    queue.addFragment(Fragment(init))
  }

  companion object {

    fun <I : QUnionType, A : ArgBuilder> baseObject(objectModel: I): UnionConfigAdapter<I, A> = TODO()
        //BaseUnionAdapter<I>(objectModel)

    fun <I : QUnionType, A : ArgBuilder> create(property: GraphQlProperty, objectModel: I)
        : UnionConfigAdapter<I, A> = UnionAdapterImpl(property, objectModel)
  }
}

private class UnionAdapterImpl<I : QUnionType, A : ArgBuilder>(
    graphqlProperty: GraphQlProperty,
    objectModel: I,
    val scope: (I.() -> Unit)? = null,
    val arguments: A? = null,
    config: (A.() -> Unit)? = null
) : UnionConfigAdapter<I, A>(graphqlProperty, objectModel, config) {

  override fun invoke(arguments: A, scope: I.() -> Unit): UnionStub {
    return UnionAdapterImpl(qproperty, model, scope, arguments, config)
  }

  override fun invoke(scope: I.() -> Unit): UnionStub {
    return UnionAdapterImpl(qproperty, model, scope, arguments, config)
  }
}

private class BaseUnionAdapter<out I : QUnionType>(model: I)
  : UnionConfigAdapter<I, ArgBuilder>(GraphQlProperty.ROOT, model) {

  override fun invoke(arguments: ArgBuilder, scope: I.() -> Unit): UnionStub {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun invoke(scope: I.() -> Unit): UnionStub {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override val queue = FragmentProvider()
}


@ValueDelegate(QModel::class) private class UnionStubImpl(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<Fragment>,
    override val args: Map<String, Any> = emptyMap()
) : Adapter,
    QField<QModel<QType>?>,
    FragmentContext {

  var value: QModel<QType>? = null


  override fun accept(result: Any?): Boolean {
    return if (result is JsonObject) {
      value = result["__typename"]?.let { resultType ->
        fragments.find {
          it.model.graphqlType == resultType
        }?.initializer?.invoke() as? QModel<QType>
      }
      return value?.accept(result) == true
    } else false
  }

  override fun toRawPayload(): String =
      fragments.joinToString(prefix = "{__typename,", postfix = "}") {
        it.model.run {
          "... on " + graphqlType + toGraphql(false)
        }
      }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): QModel<QType>? = value
}

