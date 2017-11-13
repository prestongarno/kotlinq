package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.EnumStub
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.hooks.Configurable
import com.prestongarno.ktq.hooks.NoArgConfig
import com.prestongarno.ktq.hooks.OptionalConfig
import com.prestongarno.ktq.internal.ValueDelegate
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
// Kotlin/Intellij bug??? can't move the `where` clause up to this line!
@PublishedApi internal class EnumConfigStubImpl<T, A>(
    private val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>
) : Configurable<EnumStub<T, A>, A> by Configurable.new({
  EnumAdapterImpl(qproperty, enumClass, it)
})
    where T : Enum<*>, T : QEnumType, A : ArgBuilder

@PublishedApi internal class EnumOptionalArgStub<T, A>(
    private val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>
) : OptionalConfig<EnumStub<T, A>, T, A> by OptionalConfig.new({
  EnumAdapterImpl(qproperty, enumClass, it)
})
    where T : Enum<*>, T : QEnumType, A : ArgBuilder

@PublishedApi internal class EnumNoArgStub<T>(
    private val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>
) : NoArgConfig<EnumStub<T, ArgBuilder>, T> by NoArgConfig.new({
  EnumAdapterImpl(qproperty, enumClass, it ?: ArgBuilder())
})
    where T : Enum<*>, T : QEnumType

private class EnumAdapterImpl<T, out A>(
    qproperty: GraphQlProperty,
    private val enumClass: KClass<T>,
    private val argBuilder: A?
) : PreDelegate(qproperty),
    EnumStub<T, A>

    where T : Enum<*>,
          T : QEnumType,
          A : ArgBuilder {

  override var default: T? = null

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
      EnumFieldImpl(qproperty, enumClass, argBuilder?.arguments?.invoke()?: emptyMap(), default).bind(inst)

  /**
   * TODO:: currently if no [ArgBuilder] is passed in, then the config() block is empty
   * Easy way to do this is create one instance of the argclass reflectively (since [OptionalConfig]
   * delegate should have a no-arg constructor
   */
  override fun config(argumentScope: A.() -> Unit) = argBuilder?.argumentScope()?: Unit
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
