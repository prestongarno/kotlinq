package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.stubs.EnumListStub
import kotlin.reflect.KProperty

@PublishedApi internal fun <T, A> newEnumListDelegate(
    qproperty: GraphQlProperty, arguments: A?
): EnumListStub<T, A> where T : Enum<*>, T : QEnumType, A : ArgBuilder =
    EnumListStubImpl(qproperty)

@PublishedApi internal fun <T> newEnumListField(
    qproperty: GraphQlProperty
): QField<List<T>> where T : Enum<*>, T : QEnumType =
    EnumListAdapterImpl(qproperty, emptyMap())

private data class EnumListStubImpl<T, out A>(
    val qproperty: GraphQlProperty,
    val arguments: A? = null
) : EnumListStub<T, A>
    where T : Enum<*>,
          T : QEnumType,
          A : ArgBuilder {

  override fun config(scope: A.() -> Unit) {
    arguments?.scope()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<T>> =
      EnumListAdapterImpl<T>(qproperty, arguments.toMap()).bind(inst)

  override var default: T? = null

}

private data class EnumListAdapterImpl<T>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any>
) : QField<List<T>>, Adapter
    where T : Enum<*>,
          T : QEnumType {

  private val value = mutableListOf<T>()

  override fun toRawPayload(): String = qproperty.graphqlName +
      if (args.isEmpty()) "" else args.entries.joinToString(
          prefix = "(", postfix = ")", separator = ","
      ) { "${it.key}: ${formatAs(it)}" }

  override fun accept(result: Any?): Boolean {
    TODO("Not Implemented")
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<T> = value

}

