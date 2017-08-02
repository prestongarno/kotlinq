package com.prestongarno.transpiler.experimental

import com.prestongarno.ktq.runtime.Payload
import com.prestongarno.ktq.runtime.QueryData
import com.prestongarno.ktq.runtime.annotations.WithArgs
import java.util.*

/**
 * Sample setup for what should be built for easiest/most dynamic queries and fragmented queries
 */

class Query<E>(val onSuccess: (E) -> Unit, val onError: (Int, String) -> Unit = { i, m -> }) {

	companion object builder {

		val queue = LinkedList<Any>()

		fun exec(): Nothing = TODO()

		fun <T : SearchResultItemConnection> search(builder: Search.builder<T>): Query.builder {
			println("Created object '$builder'")
			queue.add(builder.search)
			return this
		}
	}

}

/**
 * Example class generated from the GraphQl schema:
 *   - Each field is a lazy-eval failure on access by default
 *   - Open class for subclassing
 *   - Using properties, because methods/interfaces/builders just dont make sense
 *   - Bounded type parameters on all types and restrict to use QueryData companion object
 *       field/method to be able to get delegate properties
 *   - Fields requiring arguments (not shown below) will probably require abstract method/iface
 *      to generically get all arguments for a field before querying
 */
abstract class SearchResultItemConnection : QueryData() {
	open val codeCount: Int by stub
	open val issueCount: Int by stub
	open val repositoryCount: Int by stub
	open val userCount: Int by stub
	open val wikiCount: Int by stub
	open val pageInfo: PageInfo by stub
	open val edges: List<out SearchResultItemEdge> by stub
	open val nodes: List<out SearchResultItem> by stub
}

/**
 * Sample sub-class of a SearchResultItemConnection
 *   - may even want to make open superclass fields "protected open"
 *   - Fields are either primitives, another QueryData subclass, or list of any of <-
 *   - delegates are simply a wrapper around the standard kotlin "by map" delegate,
 *       but adding a subclass type argument for mimicking contravariance
 *   - Seems like the simplest solution without a long list of generics and "outs" and "ins" at class signature
 *   TODO: Input arguments/parameters for fields while maintaining type safety. Probably easiest to:
 *     -> tag fields requiring input args with an annotation (e.g. "@InputArg(out ArgBundle::class)")
 *     -> generate simple inner class, constructor with required params, builder methods for optional args
 *     -> delegate object parameter add optional/overloaded parameter to pass "ArgBundle" to
 *         |-> Extremely convenient, not only easy to check with anno proc, but delegate method can directly check the
 *         |    input argument annotation against value at runtime
 */
class FragmentedQuery : SearchResultItemConnection() {
	override val codeCount: Int by primitives
	override val issueCount: Int by primitives
	override val edges by collection<SubFragment>()
	@WithArgs(NodePayload::class) // <- todo move this to the "generated" type definition
	override val nodes by collection<SubSearchItem>(/**TODO Add optional parameter here to hack on query/mutation args*/)
	override val pageInfo by nested<CustomPageInfo>()

	class NodePayload(val limitTo: Int): Payload {
		override fun get(): List<Pair<String, String>> = TODO()
	}

}

class SubSearchItem : SearchResultItem()
class SubFragment : SearchResultItemEdge()

class Search<out E : SearchResultItemConnection> internal constructor(val query: String, val type: SearchType, val handler: Query<out E>) {

	private var first: Int = Int.MIN_VALUE
	private var last: Int = Int.MIN_VALUE
	private var after: String = ""
	private var before: String = ""


	companion object {
		fun <T : SearchResultItemConnection> create(query: String, type: SearchType, handler: Query<out T>): builder<T> {
			return builder<T>(Search(query, type, handler))
		}
	}

	class builder<out E : SearchResultItemConnection> internal constructor(internal val search: Search<E>) {

		fun first(value: Int): builder<E> {
			search.first = value
			return this
		}

		fun after(value: String): builder<E> {
			search.after = value
			return this
		}

		fun last(value: Int): builder<E> {
			search.last = value
			return this
		}

		fun before(value: String): builder<E> {
			search.before = value
			return this
		}
	}
}

enum class SearchType {
	USER,
	ISSUE,
	REPOSITORY
}

/***********************************************************************************
 *  Supporting types
 ***********************************************************************************/

open class SearchResultItemEdge

open class PageInfo : QueryData()

open class SearchResultItem

class CustomPageInfo : PageInfo()

