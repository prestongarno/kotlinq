package org.kotlinq.dsl.extensions

import org.kotlinq.dsl.LeafBinding
import org.kotlinq.dsl.fields.FreeProperty

/**
 * Unfortunately, extenstion functions as a member reference are not allowed
 */

fun String.string(): LeafBinding = {
  FreeProperty(this).string().invoke(it)
}

fun String.integer(): LeafBinding = {
  FreeProperty(this).integer().invoke(it)
}

fun String.boolean(): LeafBinding = {
  FreeProperty(this).boolean().invoke(it)
}

fun String.float(): LeafBinding = {
  FreeProperty(this).float().invoke(it)
}
