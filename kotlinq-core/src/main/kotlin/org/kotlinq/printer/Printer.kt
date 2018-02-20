package org.kotlinq.printer

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.InstanceAdapter
import org.kotlinq.common.stringify
import org.kotlinq.fragments.getFragments
import java.util.*


// TODO Use the AbstractGraphVisitor

internal
class PrinterImpl : GraphQlFormatter {

  override fun printGraphQl(
      instance: GraphQlInstance,
      pretty: Boolean,
      inlineFragments: Boolean): String =

      when {
        pretty -> pretty(instance)
        else -> VisitingPrinter(instance).toString()
      }
}

private const val INDENT: String = "  "
private const val SPREAD: String = "..."
private const val ENTER_SCOPE: String = "{"
private const val EXIT_SCOPE: String = "}"


/**
 * Stack-based algorithm to print GraphQL requests
 *
 * Rules:
 *   * Stack is type Any, but only [Adapter] & [Context] ever added/removed
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
 *                  - if contains another model or another context, recurse on model, but pass the fragments and the [StringBuilder] graphQlInstance
 *                      - this way, we do not do any redundant work and always have [fragments] reference from the base scope
 *
 *
 * **WARNING** this reverses the order of printing, need to fix tests before using this by default
 */
internal
fun print(root: GraphQlInstance): String = print(root, null)

private
fun print(root: GraphQlInstance, frags: Map<Fragment, String>? = null, builder: StringBuilder = StringBuilder()): String {

  val stack = LinkedList<Any>()
  val fragments: Map<Fragment, String> = frags
      ?: root.getFragments().mapIndexed { index, fragment ->
        fragment to "frag${fragment.graphQlInstance}$index"
      }.toMap()


  val pushField: (Any) -> Unit = stack::addFirst

  pushField(root)

  var curr: Any

  while (stack.isNotEmpty()) {


    curr = stack.remove()

    if (curr is Adapter) {

      builder.append(curr.propertyInfo.graphQlName)
      if (curr.propertyInfo.arguments.isNotEmpty())
        builder.append(curr.propertyInfo.arguments.stringify())

      if (curr is InstanceAdapter) {
        pushField(curr.fragment.graphQlInstance)
        continue
      } else if (curr is FragmentAdapter) {
        pushField(curr)
        curr.fragments.asSequence().map { it.value.graphQlInstance }.forEach(pushField)
        builder.append(ENTER_SCOPE)
        builder.append("__typename,")
        continue
      }

    } else if (curr is Fragment) {
      builder.append("...")
      builder.append(fragments[curr]!!) // fail fast
    } else if (curr is GraphQlInstance) {
      stack.addFirst(curr)
      curr.properties.toList().reversed().forEach(pushField)
      builder.append(ENTER_SCOPE)
      continue
    }

    if (stack.isNotEmpty() && (stack.first is GraphQlInstance || stack.first is FragmentAdapter)) {

      while (stack.isNotEmpty()
          && (stack.first is GraphQlInstance
              || stack.first is FragmentAdapter)) {

        stack.removeFirst()
        builder.append(EXIT_SCOPE)

        if (stack.isNotEmpty() && stack.first.let {
              it is Adapter && it !is FragmentAdapter
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
        append(fragment.graphQlInstance)
        append(ENTER_SCOPE)
        val numOfFields = fragment.graphQlInstance.properties.size - 1

        fragment.graphQlInstance.properties.entries.toList().forEachIndexed { i, (_, value) ->
          append(value.propertyInfo.graphQlName)
          append(value.propertyInfo.arguments.stringify())
          when (value) {
          // recursive call, but only on one level deep since we pass the fragment set
            is InstanceAdapter -> print(value.fragment.graphQlInstance, frags, builder)
            is FragmentAdapter -> {
              value.fragments.values.joinTo(
                  builder,
                  separator = ",$SPREAD",
                  prefix = "{__typename,$SPREAD",
                  postfix = "}",
                  transform = { fragments[it]!! }
              )
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

internal
fun pretty(context: GraphQlInstance): String {
  val fragments = context.getFragments().mapIndexed { index, fragment ->
    fragment to "frag${fragment.graphQlInstance}$index"
  }.toMap()

  return context.printNode(fragments)/*.let {
    if (fragments.isEmpty()) it else it + fragments.entries.sortedBy(Map.Entry<Fragment, String>::value)
        .joinToString(prefix = "\n", separator = "\n", postfix = "") { (frag, name) ->
          "fragment $name on ${frag.prototype.graphQlInstance.graphQlTypeName} " + frag.prototype.graphQlInstance.printNode(fragments)
        }
  }*/
}

private
fun GraphQlInstance.printNode(fragments: Map<Fragment, String>, indentLevel: Int = 1): String {
  val indent = "\n${INDENT.repeat(indentLevel)}"
  return nodes.joinToString(prefix = "{" + indent, separator = indent, postfix = "\n${INDENT.repeat(indentLevel - 1)}}") {
        it.propertyInfo.graphQlName + it.propertyInfo.arguments.stringify() + it.printEdge(fragments, indentLevel + 1)
      }
}

private
fun Adapter.printEdge(fragments: Map<Fragment, String>, indentLevel: Int = 1): String {
  val whitespace = "\n${INDENT.repeat(indentLevel)}"
  return when (this) {

    is InstanceAdapter -> " " + fragment.graphQlInstance.printNode(fragments, indentLevel) // only fragments get indented + 1

    is FragmentAdapter -> {
      this@printEdge.fragments.asIterable().joinToString(
          prefix = " {" + whitespace + "__typename" + whitespace,
          postfix = "\n${INDENT.repeat(indentLevel - 1)}}", separator = whitespace) {
        val proto = it.value.graphQlInstance
        """... on ${it.value.typeName} ${proto.printNode(fragments, indentLevel + 1)}"""
      }
    }

    else -> ""
  }
}
