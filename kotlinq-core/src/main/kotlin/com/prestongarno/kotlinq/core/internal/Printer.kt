/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.prestongarno.kotlinq.core.internal

import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.Adapter
import com.prestongarno.kotlinq.core.api.Fragment
import com.prestongarno.kotlinq.core.api.FragmentContext
import com.prestongarno.kotlinq.core.api.ModelProvider
import java.util.*

private const val INDENT = "  "

internal
fun QModel<*>.pretty() = printNode()

/**
 * what even is a stack? mutual recursion is all I know
 */
private
fun QModel<*>.printNode(indentLevel: Int = 1): String {
  val indent = "\n${INDENT.repeat(indentLevel)}"
  return this.fields.entries.map(MutableMap.MutableEntry<String, Adapter>::value).map {
    it.qproperty.graphqlName + it.args.stringify() + it.printEdge(indentLevel + 1)
  }.joinToString(
      prefix = "{" + indent,
      separator = indent,
      postfix = "\n${INDENT.repeat(indentLevel - 1)}}"
  )
}

private
fun Adapter.printEdge(indentLevel: Int = 1): String {
  val whitespace = "\n${INDENT.repeat(indentLevel)}"
  return when (this) {

    is ModelProvider -> " " + value.printNode(indentLevel) // only fragments get indented + 1

    is FragmentContext -> fragments.joinToString(
        prefix = " {" + whitespace,
        postfix = "\n${INDENT.repeat(indentLevel - 1)}}",
        separator = whitespace) {
      "... on ${it.model.graphqlType} " + it.model.printNode(indentLevel + 1)
    }

    else -> ""
  }
}


internal
fun extractedPayload(root: QModel<*>): String {

  val enterModel = "{"
  val exitModel = "}"
  val enterField = ","

  val builder = StringBuilder()
  val stack = LinkedList<Any>()

  val pushField: (Any) -> Unit = stack::addFirst

  pushField(root)

  var curr: Any

  while (stack.isNotEmpty()) {


    curr = stack.remove()

    if (curr is Adapter) {

      builder.append(curr.qproperty.graphqlName)
      if (curr.args.isNotEmpty()) builder.append(curr.args.stringify())

      if (curr is ModelProvider) {
        pushField(curr.value)
        continue
      } else if (curr is FragmentContext) {
        builder.append(enterModel)
        curr.fragments.forEach { pushField(it) }
      }

    } else if (curr is Fragment) {
      builder.append("... on ")
      builder.append(curr.model.graphqlType)
      stack.addFirst(curr.model)
      continue
    } else if (curr is QModel<*>) {
      stack.addFirst(curr)
      curr.getFields().forEach { stack.addFirst(it) }
      builder.append(enterModel)
    }

    if (stack.isNotEmpty() && stack.first is QModel<*>) {
      while (stack.isNotEmpty() && stack.removeFirst() is QModel<*>) {
        builder.append(exitModel)
      }
      if (stack.isNotEmpty() && stack.first is Adapter) {
        builder.append(",")
      }
    }

  }
  return builder.toString()
}
