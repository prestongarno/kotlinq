package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QUnionType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.adapters.newUnionField
import com.prestongarno.ktq.hooks.ConfiguredQuery
import com.prestongarno.ktq.hooks.OptionalConfiguration
import com.prestongarno.ktq.properties.GraphQlProperty

/**
 * TODO(Mon Nov 13 18:22:30 CST 2017) try to figure out a safe way to collect fragments *and* arguments
 * TODO(cont)  |  without type casting interface to internal type or some other hack
 */
interface UnionStub<T : QUnionType, out A : ArgBuilder> : DelegateProvider<QModel<*>?> {

  fun config(scope: A.() -> Unit)

  @kotlin.Suppress("AddVarianceModifier")
  interface Query<U : QUnionType> : SchemaStub {
    /**
     * Invoke function enforces a scoped block to encourage creating fragments for the field
     * If no fragments are specified, this field will not be included in the query
     * @param arguments : The Arguments to pass to the field. Not required for this type of query
     * @param scope : the block for resolving GraphQL fragments to [QModel] types
     */
    operator fun invoke(arguments: ArgBuilder? = null, scope: U.() -> Unit): QField<QModel<*>?>
  }

  interface OptionalConfigQuery<U : QUnionType, A : ArgBuilder> : OptionalConfiguration<UnionStub<U, A>, QModel<*>?, A>

  interface ConfigurableQuery<U : QUnionType, A : ArgBuilder> : ConfiguredQuery<UnionStub<U, A>, A>

  private class QueryImpl<U : QUnionType>(
      val qproperty: GraphQlProperty, val model: U
  ) : Query<U> {
    override fun invoke(arguments: ArgBuilder?, scope: U.() -> Unit): QField<QModel<*>?> =
        model.queue(model, scope) { newUnionField(qproperty, reset(), arguments?.arguments?.invoke()) }
  }

}