package org.kotlinq.api.services.wrappers

import org.kotlinq.api.Context
import org.kotlinq.api.DeserializingAdapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.ModelAdapter
import org.kotlinq.api.ParsingAdapter
import org.kotlinq.api.Resolver
import org.kotlinq.api.services.Wrapper

internal
class ResolverWrapper(default: Resolver)
  : Wrapper<Resolver>(default, Resolver::class),
    Resolver {

  override fun resolve(value: Map<String, Any?>, target: Context): Boolean = instance().resolve(value, target)
  override fun visit(target: DeserializingAdapter) = instance().visit(target)
  override fun visit(target: Fragment) = instance().visit(target)
  override fun visit(target: FragmentAdapter) = instance().visit(target)
  override fun visit(target: ModelAdapter) = instance().visit(target)
  override fun visit(target: ParsingAdapter) = instance().visit(target)
  override fun equals(other: Any?) = instance() == other
  override fun hashCode() = instance().hashCode()
  override fun toString() = instance().toString()
}