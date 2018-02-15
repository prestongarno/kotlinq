package org.kotlinq.printer

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.ModelAdapter
import org.kotlinq.api.Printer
import org.kotlinq.common.stringify
import java.util.*

internal
class PrinterImpl(
    override val prettyPrinter: Printer = defaultOptimizedPrettyPrinter,
    override val prettyOptimizedPrinter: Printer = defaultOptimizedPrettyPrinter,
    override val printer: Printer = defaultOptimizedPrinter,
    override val optimizedPrinter: Printer = defaultOptimizedPrinter
) : GraphQlFormatter

val defaultOptimizedPrinter: Printer get() = ::print
val defaultOptimizedPrettyPrinter: Printer get() = ::pretty

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
 *                  - if contains another model or another context, recurse on model, but pass the fragments and the [StringBuilder] instance
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
        fragment to "frag${fragment.prototype.graphQlInstance.graphQlTypeName}$index"
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

      if (curr is ModelAdapter) {
        pushField(curr.fragment.prototype.graphQlInstance)
        continue
      } else if (curr is FragmentAdapter) {
        pushField(curr)
        curr.fragments.asSequence().map { it.value.prototype.graphQlInstance }.forEach(pushField)
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
        append(fragment.prototype.graphQlInstance.graphQlTypeName)
        append(ENTER_SCOPE)
        val numOfFields = fragment.prototype.graphQlInstance.properties.size - 1

        fragment.prototype.graphQlInstance.properties.entries.toList().forEachIndexed { i, (_, value) ->
          append(value.propertyInfo.graphQlName)
          append(value.propertyInfo.arguments.stringify())
          when (value) {
          // recursive call, but only on one level deep since we pass the fragment set
            is ModelAdapter -> print(value.fragment.prototype.graphQlInstance, frags, builder)
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

private
fun GraphQlInstance.getFragments(): Set<Fragment> = getFragments(this, hashSetOf(this))

private
fun getFragments(root: GraphQlInstance, collector: Set<GraphQlInstance>): Set<Fragment> {
  val fragmentEdges = root.properties
      .asSequence()
      .map { it.value }
      .filterIsInstance<FragmentAdapter>()
      .flatMap { it.fragments.asSequence() }
      .filterNot { collector.contains(it.value.prototype.graphQlInstance) }
      .toSet()

  return fragmentEdges.map { it.value.prototype.graphQlInstance }.map {
    getFragments(it, collector + it)
  }.flatten().toSet()

}

internal
fun pretty(context: GraphQlInstance): String {
  val fragments = context.getFragments().mapIndexed { index, fragment ->
    fragment to "frag${fragment.prototype.graphQlInstance.graphQlTypeName}$index"
  }.toMap()

  return context.printNode(fragments).let {
    if (fragments.isEmpty()) it else it + fragments.entries.sortedBy(Map.Entry<Fragment, String>::value)
        .joinToString(prefix = "\n", separator = "\n", postfix = "") { (frag, name) ->
          "fragment $name on ${frag.prototype.graphQlInstance.graphQlTypeName} " + frag.prototype.graphQlInstance.printNode(fragments)
        }
  }
}

private
fun GraphQlInstance.printNode(fragments: Map<Fragment, String>, indentLevel: Int = 1): String {
  val indent = "\n${INDENT.repeat(indentLevel)}"
  return this.properties.entries.map { it.value }
      .joinToString(prefix = "{" + indent, separator = indent, postfix = "\n${INDENT.repeat(indentLevel - 1)}}") {
        it.propertyInfo.graphQlName + it.propertyInfo.arguments.stringify() + it.printEdge(fragments, indentLevel + 1)
      }
}

private
fun Adapter.printEdge(fragments: Map<Fragment, String>, indentLevel: Int = 1): String {
  val whitespace = "\n${INDENT.repeat(indentLevel)}"
  return when (this) {

    is ModelAdapter -> " " + fragment.prototype.graphQlInstance.printNode(fragments, indentLevel) // only fragments get indented + 1

    is FragmentAdapter -> this@printEdge.fragments.asIterable().joinToString(
        prefix = " {" + whitespace + "__typename" + whitespace,
        postfix = "\n${INDENT.repeat(indentLevel - 1)}}",
        separator = whitespace) { (_, fragment) ->
      "...${fragments[fragment]}"// + it.model.printNode(fragments, indentLevel + 1)
    }

    else -> ""
  }
}
