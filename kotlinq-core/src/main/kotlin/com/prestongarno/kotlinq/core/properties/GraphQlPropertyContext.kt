package com.prestongarno.kotlinq.core.properties

import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.adapters.PreDelegate
import com.prestongarno.kotlinq.core.api.Stub
import com.prestongarno.kotlinq.core.internal.empty
import kotlin.reflect.KProperty


internal
interface GraphQlPropertyContext<
    out X : GraphQlDelegateProvider<U, A, T>,
    out U : PreDelegate<T, A>,
    A : ArgumentSpec,
    out T : Any?> : Stub<X> {

  val qproperty: GraphQlProperty

  fun newDslBuilder(arguments: A?): U

  fun toField(
      block: U.() -> Unit = empty(),
      arguments: A? = null
  ): GraphqlPropertyDelegate<T> = newDslBuilder(arguments)
      .apply(block)
      .toDelegate(qproperty)


  companion object {

    internal
    class Builder<U : PreDelegate<T, A>, A : ArgumentSpec, T : Any?>(
        private val dslConstructor: ((A?) -> U)
    ) {
      /**
       * @throws NullPointerException if any of the following were not done:
       *   - DSL type constructor
       *   - Field delegate constructor
       */
      fun <S : GraphQlDelegateProvider<U, A, T>> build(
          property: GraphQlProperty,
          delegateProvider: S
      ): GraphQlPropertyContext<S, U, A, T> =
          GraphQlPropertyContextImpl(delegateProvider, dslConstructor, property)
    }
  }
}

private class GraphQlPropertyContextImpl<
    out X : GraphQlDelegateProvider<U, A, T>,
    out U : PreDelegate<T, A>,
    A : ArgumentSpec,
    out T : Any?>(
    private val provider: X,
    private val dslConstructor: (A?) -> U,
    override val qproperty: GraphQlProperty
) : GraphQlPropertyContext<X, U, A, T> {

  override fun newDslBuilder(arguments: A?): U = dslConstructor(arguments)

  override fun getValue(inst: QSchemaType, property: KProperty<*>): X = provider
}
