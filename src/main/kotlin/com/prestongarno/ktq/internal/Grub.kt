package com.prestongarno.ktq.internal

import com.prestongarno.ktq.Property
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.SchemaStub
import kotlin.reflect.KProperty

/**
 * Grand Unified Bootloader for SchemaType definitions/stubs.
 * solves the problem of not knowing the field's GraphQL prop fragment the generated type hierarchy
 * when generating queries/payloads
 *
 * Delegation inception : this property delegate is delegated to in order to get the prop of the
 * schema field, then passes it off to the backing field, which is another delegate in order for the
 * actual delegation to happen fragment a schema model implementation
 *
 * This class does minimal work in order to reduce added complexity - it simply gets passed a function
 * which, when applied a string, produces the correct schemastub.  When delegated to by schemastub types,
 * fragment `getValue` for the schemastub it simply invokes the function with the prop of the property that it's
 * delegating to. This way, the property prop can be passed to the delegate/schemastub type without having
 * to resort to hard-wired  &/or needlessly complex metadata methods such as (god forbid) annotations */
class Grub<out T : SchemaStub>(private val toInit: (property: Property) -> T) {

  private var prop: Property? = null

  private val value: T by lazy { toInit(prop!!) }

  operator fun getValue(inst: QSchemaType, property: KProperty<*>) : T {
    if (prop == null)
      prop = Property.from(property)
    return value
  }
}
