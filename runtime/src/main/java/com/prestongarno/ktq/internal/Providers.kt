/**For organizing the utility objects/classes which provide stubs for schema types
 */
package com.prestongarno.ktq.internal

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType

/** TODO K.I.S.S.
 */
internal interface QConfigProvider<in G, F, out E> {
  fun <T : G, A : F> configStub(arginit: (F) -> A): E
}

internal interface QScalarStubProvider<out E> {
  fun <T> stub(): E
}

internal interface QTypeStubProvider<out E> {
  fun <T : QSchemaType> stub(): E
}

internal interface ModelProvider {
  fun getModel() : QModel<*>
}