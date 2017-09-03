package com.prestongarno.ktq.internal

import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.SchemaStub
import kotlin.reflect.KProperty

/**
 * Grand Unified Bootloader for SchemaType definitions/stubs.
 * solves the problem of not knowing the field's GraphQL name on the generated type hierarchy
 * when generating queries/payloads
 *
 * Delegation inception : this property delegate is delegated to in order to get the name of the
 * schema field, then passes it off to the backing field, which is another delegate in order for the
 * actual delegation to happen on a schema model implementation
 *
 * This class does minimal work in order to reduce added complexity - it simply gets passed a function
 * which, when applied a string, produces the correct schemastub.  When delegated to by schemastub types,
 * on `getValue` for the schemastub it simply invokes the function with the name of the property that it's
 * delegating to. This way, the property name can be passed to the delegate/schemastub type without having
 * to resort to hard-wired  &/or needlessly complex metadata methods such as (god forbid) annotations
 *
 * TODO() maybe this should just pass the entire property to the stub? This isn't needed now, but might
 * be useful in the future
 */
class Grub<out T : SchemaStub>(private val toInit: (String) -> T) {
  operator fun getValue(inst: QSchemaType, property: KProperty<*>) : T = toInit(property.name)
}
