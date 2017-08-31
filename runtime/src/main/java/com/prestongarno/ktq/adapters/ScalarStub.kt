package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import kotlin.reflect.KProperty

internal class ScalarStub<T, out A: ArgBuilder>(argBuilder: A? = null) :
    Stub<T>,
    QConfigStub<T, A>,
    ArgBuilder {

  @Suppress("UNCHECKED_CAST") private val argBuilder: A = argBuilder ?: this as A

  /** TODO I think this needs to `= ArgBuilder.create<T, A>()`?
   */
  @Suppress("UNCHECKED_CAST") override fun config(): A = argBuilder

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T> = this

  override fun getValue(inst: QModel<*>, property: KProperty<*>): T = value!!

  val value : T? = null
  val args: MutableMap<in String, Any> = HashMap()

  @Suppress("UNCHECKED_CAST") override fun <T> build() : Stub<T> = ScalarStub<T, A>()

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value)  }

  override fun toString(): String = args.map { "${it.key} : ${formatAs(it.value)}" }.joinToString { "\n" }

}

private fun formatAs(value: Any): String {
  return when (value) {
    is Int, is Boolean, Float -> "$value"
    is String -> "\"$value\""
    is QInput -> value.toPayloadString()
    is Enum<*> -> value.name
    is List<*> -> value
        .map { formatAs(it ?: "") }
        .filter { it.isNotBlank() }
        .joinToString(", ", "[ ", " ]")
    else -> throw UnsupportedOperationException()
  }
}
