package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.EnumStub
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.hooks.Configurable
import com.prestongarno.ktq.internal.ValueDelegate
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
// Kotlin/Intellij bug??? can't move the `where` clause up to this line!
@PublishedApi internal class EnumConfigStubImpl<T, A>(
    private val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>
) : Configurable<EnumStub<T, A>, A> by Configurable.new({ arguments, scope ->
  EnumAdapterImpl(qproperty, enumClass, arguments, null).applyNotNull(scope)
})
    where T : Enum<*>, T : QEnumType, A : ArgBuilder

@PublishedApi internal class EnumNoArgStub<T>(
    private val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>
) : EnumStub<T, ArgBuilder>
    where T : Enum<*>,
          T : QEnumType {

  private var arguments: ArgBuilder? = null

  override fun config(argumentScope: ArgBuilder.() -> Unit) {
    arguments = arguments?.apply(argumentScope)?: ArgBuilder().apply(argumentScope)
  }

  override var default: T? = null

  operator fun invoke(
      arguments: ArgBuilder = ArgBuilder(),
      scope: (EnumAdapterImpl<T, ArgBuilder>.() -> Unit)?
  ): EnumAdapterImpl<T, ArgBuilder> =
      EnumAdapterImpl(qproperty, enumClass, arguments, default).applyNotNull(scope)

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
      EnumFieldImpl(qproperty, enumClass, arguments?.arguments?.invoke()?: emptyMap())
}

@PublishedApi internal class EnumAdapterImpl<T, out A>(
    qproperty: GraphQlProperty,
    private val enumClass: KClass<T>,
    private val argBuilder: A,
    override var default: T?
) : PreDelegate(qproperty),
    EnumStub<T, A>

    where T : Enum<*>,
          T : QEnumType,
          A : ArgBuilder {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
      EnumFieldImpl(qproperty, enumClass, argBuilder.arguments(), default)

  override fun config(argumentScope: A.() -> Unit) = argBuilder.argumentScope()
}

@ValueDelegate(Enum::class)
private data class EnumFieldImpl<T>(
    override val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>,
    override val args: Map<String, Any> = emptyMap(),
    private val default: T? = null
) : QField<T>, Adapter where T : Enum<*>, T : QEnumType {

  var value: T? = null

  override fun getValue(inst: QModel<*>, property: KProperty<*>) = value!!

  override fun accept(result: Any?): Boolean {
    // TODO don't call the java reflection type - use kotlin enums only
    value = enumClass.java.enumConstants?.find { it.name == "$result" } ?: default
    return value != null
  }

  override fun toRawPayload(): String {
    return qproperty.graphqlName +
        if (args.isNotEmpty())
          args.entries.joinToString(",", "(", ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          }
        else ""
  }
}
