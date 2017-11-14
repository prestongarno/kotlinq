package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.adapters.newUnionField
import com.prestongarno.ktq.hooks.Configurable
import com.prestongarno.ktq.hooks.OptionalConfig
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

  interface OptionalConfigQuery<U : QUnionType, A : ArgBuilder> : OptionalConfig<UnionStub<U, A>, QModel<*>?, A>

  interface ConfigurableQuery<U : QUnionType, A : ArgBuilder> : Configurable<UnionStub<U, A>, A>

  private class QueryImpl<U : QUnionType>(
      val qproperty: GraphQlProperty, val model: U
  ) : Query<U> {
    override fun invoke(arguments: ArgBuilder?, scope: U.() -> Unit): QField<QModel<*>?> =
        model.queue(model, scope) { newUnionField(qproperty, reset(), arguments?.arguments?.invoke()) }
  }

}