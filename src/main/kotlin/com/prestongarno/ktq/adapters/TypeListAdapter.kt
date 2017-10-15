package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.FieldConfig
import com.prestongarno.ktq.ListConfigType
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.QProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.TypeListArgBuilder
import com.prestongarno.ktq.TypeListStub
import com.prestongarno.ktq.internal.ModelProvider
import kotlin.reflect.KProperty

internal class TypeListAdapter<I : QSchemaType, P : QModel<I>, out B : TypeListArgBuilder>(
    property: QProperty,
    val builderInit: (TypeListArgBuilder) -> B
) : FieldConfig(property),
    ListInitStub<I>,
    TypeListStub<P, I>,
    ListConfigType<I, B>,
    TypeListArgBuilder,
    ModelProvider {

  override fun accept(result: Any?): Boolean {
    return if (result is JsonArray<*>) {
      var resolvedOkay = true
      result.filterIsInstance<JsonObject>().forEach { element ->
        this.results.add(init().apply {
          if(!this.accept(element))
            resolvedOkay = false
        })
      }
      resolvedOkay
    } else false
  }

  val results = mutableListOf<P>()
  lateinit var init: () -> P
  override val value: QModel<*> by lazy { init() }

  override fun config(): B = builderInit(TypeListAdapter<I, P, B>(graphqlProperty, builderInit))

  @Suppress("UNCHECKED_CAST") override fun <U : QModel<T>, T : QSchemaType> build(init: () -> U): TypeListStub<U, T>
      = apply { this.init = init as () -> P } as TypeListStub<U, T>

  override fun addArg(name: String, value: Any): TypeListArgBuilder = apply { args.put(name, value) }

  @Suppress("UNCHECKED_CAST") override fun <U : QModel<I>> init(of: () -> U): TypeListStub<U, I>
      = apply { this.init = of as () -> P } as TypeListStub<U, I>

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<P> {
    return results
  }

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeListStub<P, I> =
      apply { super.onDelegate(inst, property) }

}