package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.AbstractCollectionStub
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.InterfaceStub
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.hooks.Fragment

interface InterfaceFragment<T> : SchemaStub
    where T : QType,
          T : QInterface {
  operator fun invoke(context: FragmentScope<T, ArgBuilder>.() -> Unit): InterfaceStub<T>
}

interface InterfaceConfigFragment<T, A : ArgBuilder> : SchemaStub
    where T : QType,
          T : QInterface {
  operator fun invoke(arguments: A, context: FragmentScope<T, A>.() -> Unit): InterfaceStub<T>
}

interface CollectionFragment<T> : SchemaStub
    where T : QType,
          T : QInterface {
  operator fun invoke(context: FragmentScope<T, ArgBuilder>.() -> Unit): AbstractCollectionStub<T>
}

interface CollectionConfigFragment<T, A : ArgBuilder> : SchemaStub
    where T : QType,
          T : QInterface {

  operator fun invoke(
      arguments: A,
      context: FragmentScope<T, A>.() -> Unit
  ): AbstractCollectionStub<T>
}

/**
 * Scope of fragmenting on a GraphQL field
 * @param I : the interface type that this fragment represents*/
//@kotlin.Suppress("AddVarianceModifier")
interface FragmentScope<in I : QType, out A : ArgBuilder> {

  /**
   * Create a fragment on an field
   * @param T The concrete type. Bounded by [I] and [QType]
   */
  fun <T : I> on(initializer: () -> QModel<T>)

  /**
   * Configuration block for adding argBuilder to  the GraphQL query
   */
  fun config(scope: A.() -> Unit)
}

/**
 * Implemented by concrete field-backing delegates
 */
@kotlin.Suppress("AddVarianceModifier")
internal interface FragmentContext {
  val fragments: Set<Fragment>
}

