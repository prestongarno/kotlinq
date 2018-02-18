package org.kotlinq.dsl.extensions

import org.kotlinq.dsl.fields.FreeProperty
import org.kotlinq.dsl.fields.LeafBinding

/**
 * Unfortunately, extenstion functions as a member reference are not allowed
 */

fun String.string(): LeafBinding = { inst, nullable ->
  FreeProperty(this).string().invoke(inst, nullable)
}

fun String.integer(): LeafBinding = { inst, nullable ->
  FreeProperty(this).integer().invoke(inst, nullable)
}

fun String.boolean(): LeafBinding = { inst, nullable ->
  FreeProperty(this).integer().invoke(inst, nullable)
}

fun String.float(): LeafBinding = { inst, nullable ->
  FreeProperty(this).float().invoke(inst, nullable)
}
