/*
 * Copyright (C) 2018 Preston Garno
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
import com.prestongarno.kotlinq.core.adapters.WrapperDelegate
import com.prestongarno.kotlinq.core.api.Fragment
import com.prestongarno.kotlinq.core.api.FragmentContext
import com.prestongarno.kotlinq.core.api.ModelProvider
import com.prestongarno.kotlinq.core.getFragments
import java.util.*

private const val INDENT: String = "  "
private const val SPREAD: String = "..."
private const val ENTER_SCOPE: String = "{"
private const val EXIT_SCOPE: String = "}"

internal
fun QModel<*>.pretty(): String {
  val fragments = getFragments().mapIndexed { index, fragment -> fragment to "frag${fragment.model.graphqlType}$index" }.toMap()
  return printNode(fragments).let {
    if (fragments.isEmpty()) it else it + fragments.entries.sortedBy(Map.Entry<Fragment, String>::value)
        .joinToString(prefix = "\n", separator = "\n", postfix = "") { (frag, name) ->
          "fragment $name on ${frag.model.graphqlType} " + frag.model.printNode(fragments)
        }
  }
}

/**
 * what even is a stack? mutual recursion is all I know
 */
private
fun QModel<*>.printNode(fragments: Map<Fragment, String>, indentLevel: Int = 1): String {
  val indent = "\n${INDENT.repeat(indentLevel)}"
  return this.fields.entries.map(MutableMap.MutableEntry<String, Adapter>::value)
      .joinToString(prefix = "{" + indent, separator = indent, postfix = "\n${INDENT.repeat(indentLevel - 1)}}") {
        it.qproperty.graphqlName + it.args.stringify() + it.printEdge(fragments, indentLevel + 1)
      }
}

private
fun Adapter.printEdge(fragments: Map<Fragment, String>, indentLevel: Int = 1): String {
  val whitespace = "\n${INDENT.repeat(indentLevel)}"
  return when (this) {

    is ModelProvider -> " " + value.printNode(fragments, indentLevel) // only fragments get indented + 1

    is FragmentContext -> this@printEdge.fragments.joinToString(
        prefix = " {" + whitespace + "__typename" + whitespace,
        postfix = "\n${INDENT.repeat(indentLevel - 1)}}",
        separator = whitespace) {
      "...${fragments[it]}"// + it.model.printNode(fragments, indentLevel + 1)
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
 *      - if [fragments] is null ([Map] joining [Fragment] to a unique [String] identifier
 *          - Means that we are in the root context, so we can print the fragments at end
 *          - For all in [fragments]
 *              - print fragment definition
 *              - for all model fields
 *                  - print fields like usual
 *                  - if contains another model or another context, recurse on model, but pass the fragments and the [StringBuilder] instance
 *                      - this way, we do not do any redundant work and always have [fragments] reference from the base scope
 *
 *
 * **WARNING** this reverses the order of printing, need to fix tests before using this by default
 */
internal fun extractedPayload(root: QModel<*>): String = extractedPayload(root, null)

private
fun extractedPayload(root: QModel<*>, frags: Map<Fragment, String>? = null, builder: StringBuilder = StringBuilder()): String {

  val stack = LinkedList<Any>()
  val fragments = frags ?: root.getFragments().mapIndexed { index, fragment ->
    fragment to "frag${fragment.model.graphqlType}$index"
  }.toMap()


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
        curr.fragments.reversed().forEach(pushField)
        builder.append(ENTER_SCOPE)
        builder.append("__typename,")
        continue
      }

    } else if (curr is Fragment) {
      builder.append("...")
      builder.append(fragments[curr]!!) // fail fast
    } else if (curr is QModel<*>) {
      stack.addFirst(curr)
      curr.getFields().asIterable().reversed().forEach(pushField)
      builder.append(ENTER_SCOPE)
      continue
    }

    if (stack.isNotEmpty() && (stack.first is QModel<*> || stack.first is FragmentContext)) {

      while (stack.isNotEmpty()
          && (stack.first is QModel<*>
              || stack.first is FragmentContext)) {

        stack.removeFirst()
        builder.append(EXIT_SCOPE)

        if (stack.isNotEmpty() && stack.first.let {
              it is Adapter && it !is FragmentContext
            }) builder.append(",")

      }
    } else if (stack.isNotEmpty() && (stack.first is Adapter)) {
      builder.append(",")
    }

  }

  if (frags == null) {

    val numFragments = fragments.size - 1

    fragments.entries.sortedBy(Map.Entry<Fragment, String>::value).forEachIndexed { x, (fragment, name) ->
      builder.apply {
        append("fragment ")
        append(name)
        append(" on ")
        append(fragment.model.graphqlType)
        append(ENTER_SCOPE)
        val numOfFields = fragment.model.fields.size - 1

        fragment.model.getFields().forEachIndexed { i, field ->
          append(field.qproperty.graphqlName)
          append(field.args.stringify())
          when (field) {
          // recursive call, but only on one level deep since we pass the fragment set
            is ModelProvider -> extractedPayload(field.value, frags, builder)
            is FragmentContext -> {
              field.fragments.joinTo(builder, separator = ",$SPREAD", prefix = "{__typename,$SPREAD", postfix = "}") {
                fragments[it]!!
              }
            }
          }
          if (i < numOfFields) {
            append(",")
          }
        }
      }
      builder.append(EXIT_SCOPE)
      if (x < numFragments)
        builder.append(",")
    }
  }

  return builder.toString().trim()
}

