package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ListConfigType
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.TypeListArgBuilder
import com.prestongarno.ktq.TypeListStub
import com.prestongarno.ktq.internal.ModelProvider
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties

internal class TypeListAdapter< I : QSchemaType, P : QModel<I>, out B : TypeListArgBuilder>(
    fieldName : String,
    val builderInit: (TypeListArgBuilder) -> B
) : FieldAdapter(fieldName),
    ListInitStub<I>,
    TypeListStub<P, I>,
    ListConfigType<I, B>,
    TypeListArgBuilder,
    ModelProvider {

  val results = mutableListOf<P>()
  lateinit var init: () -> P

  override fun config(): B = builderInit(TypeListAdapter<I, P, B>(fieldName, builderInit))

  override fun getModel(): QModel<*> = init()

  @Suppress("UNCHECKED_CAST") override fun <U : QModel<T>, T : QSchemaType> build(init: () -> U): TypeListStub<U, T>
      = apply { this.init = init as () -> P } as TypeListStub<U, T>

  override fun addArg(name: String, value: Any): TypeListArgBuilder = apply { args.put(name, value) }

  @Suppress("UNCHECKED_CAST") override fun <U : QModel<I>> init(of: () -> U): TypeListStub<U, I>
    = apply { this.init = of as () -> P } as TypeListStub<U, I>

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<P> {
    return results
  }

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeListStub<P, I> {
    this.property = property
    return apply { super.onProvideDelegate(inst) }
  }

}