package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.internal.FragmentGenerator
import com.prestongarno.ktq.internal.FragmentProvider
import com.prestongarno.ktq.internal.FragmentProviderImpl
import kotlin.reflect.KProperty

interface UnionInitStub<out T : QSchemaUnion> : SchemaStub {
  fun fragment(what: T.() -> Unit): UnionStub
}

internal class UnionAdapter<I: QSchemaUnion>(
    override val graphqlName: String,
    objectModel: I,
    private val fragmentCollector: MutableList<() -> QModel<*>> = mutableListOf()
) : QModel<I>(objectModel),
    Adapter,
    UnionInitStub<I>,
    UnionStub,
    QSchemaUnion {

  private val fragments: Set<FragmentGenerator> = try {
    model.toImmutableStub().fragments.toHashSet()
  } catch (ex: Exception) {
    emptySet()
  }

  init {
    fragmentCollector.clear()
  }

  var dispatcher: (I.() -> Unit)? = null

  override val args: Map<String, Any> by lazy { mapOf<String, Any>() }

  internal var value: QModel<*>? = null

  override fun accept(result: Any?): Boolean {
    return if (result is JsonObject) {
      value = result["__typename"]?.let { resultType ->
        fragments.find { it.model.graphqlType == resultType }?.model
      }
      resolved = value != null
      return value?.accept(result) == true
    }
    else false
  }

  override fun fragment(what: I.() -> Unit): UnionStub {
    dispatcher = what
    return this
  }

  override fun toRawPayload(): String = fragments.joinToString(prefix = "__typename,") {
    "... on ${it.model.graphqlType}${it.model.fields}"//${it.model.toGraphql(false)}"
  }

  override fun getValue(
      inst: QModel<*>,
      property: KProperty<*>
  ): QModel<QSchemaType> { return value?: throw IllegalStateException("null") }

  override fun toImmutableStub(): FragmentProvider =
      FragmentProviderImpl(fragmentCollector.map { FragmentGenerator(it) }.toHashSet())

  override fun <R : QModel<*>> provideDelegate(
      inst: R,
      property: KProperty<*>
  ): UnionStub {
    return synchronized(this) {
      dispatcher?.invoke(model)
      UnionAdapter(property.name, model, fragmentCollector)
          .also { it.onProvideDelegate(inst) }
    }
  }

  override fun on(init: () -> QModel<*>) {
    this.fragmentCollector.add(init)
  }
}

