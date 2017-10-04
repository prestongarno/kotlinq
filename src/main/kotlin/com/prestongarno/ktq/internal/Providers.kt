/**For organizing the utility objects/classes which provide stubs for schema types
 */
package com.prestongarno.ktq.internal

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType

internal interface ModelProvider {
  fun getModel() : QModel<*>
}

internal interface FragmentProvider<out T: QSchemaType> {
  val fragments: List<QModel<T>>
}