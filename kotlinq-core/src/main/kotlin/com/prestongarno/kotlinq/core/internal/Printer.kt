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


/**
 * Stack-based algorithm to print GraphQL requests
 *
 * Rules:
 *   * Stack is type Any, but only [Adapter] & [QModel] ever added/removed
 *   * [QModel] & [FragmentContext] are critical points
 *
 *   * Algorithm:
 *      - If [curr] is an [GraphQlProperty], print field name and arguments
 *      - On encountering via an adapter class such as [ModelProvider] or [Fragment],
 *           push model(s) to stack & print open bracket (and spread operator if applicable)
 *      - Else if encountering [curr] is [QModel], push all [QModel.fields] (set of [Adapters]) to the stack, continue
 *      - at end of loop, check if [List.isNotEmpty] && [List.first] is [FragmentContext] or [QModel]
 *          - if above case, while true append a closing bracket, and pop the stack & repeat this step
 *          - else print a comma to separate with next field
 *
 *
 * **WARNING** this reverses the order of printing, need to fix tests before using this by default
 */
internal
fun extractedPayload(root: QModel<*>): String {

  val enterModel = "{"
  val exitModel = "}"

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
        pushField(curr)
        curr.fragments.forEach(pushField)
        builder.append(enterModel)
        continue
      }

    } else if (curr is Fragment) {
      builder.append("... on ")
      builder.append(curr.model.graphqlType)
      stack.addFirst(curr.model)
      continue
    } else if (curr is QModel<*>) {
      stack.addFirst(curr)
      curr.getFields().forEach(pushField)
      builder.append(enterModel)
      continue
    }

    if (stack.isNotEmpty() && (stack.first is QModel<*> || stack.first is FragmentContext)) {

      while (stack.isNotEmpty()
          && (stack.first is QModel<*>
          || stack.first is FragmentContext)) {

        stack.removeFirst()
        builder.append(exitModel)

        if (stack.isNotEmpty() && stack.first.let {
          it is Adapter && it !is FragmentContext
        }) builder.append(",")

      }
    } else if (stack.isNotEmpty() && (stack.first is Adapter)) {
      builder.append(",")
    }

  }

  return builder.toString()
}
