package com.prestongarno.transpiler.experimental

import com.prestongarno.ktq.runtime.QueryData
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
open class SearchResultItemConnection : QueryData() {
	 open val codeCount: Int by lazy { throw notDeclaredErr() }
	 open val issueCount: Int by lazy { throw notDeclaredErr() }
	 open val repositoryCount: Int by lazy { throw notDeclaredErr() }
	 open val userCount: Int by lazy { throw notDeclaredErr() }
	 open val wikiCount: Int by lazy { throw notDeclaredErr() }
	 open val pageInfo: PageInfo by lazy { throw notDeclaredErr() }
	 open val edges: List<out SearchResultItemEdge> by lazy { throw notDeclaredErr() }
	 open val nodes: List<out SearchResultItem> by lazy { throw notDeclaredErr() }
}

/**
 * Sample sub-class of a SearchResultItemConnection
 *   - may even want to make open superclass fields "protected open"
 *   - Fields are either primitives, another QueryData subclass, or list of any of <-
 *   - delegates are simply a wrapper around the standard kotlin "by map" delegate,
 *       but adding a subclass type argument for mimicking contravariance
 *   - Seems like the simplest solution without a long list of generics and "outs" and "ins" at class signature
 */
class FragmentedQuery : SearchResultItemConnection() {
	 override val codeCount: Int by primitives
	 override val issueCount: Int by primitives
	 override val edges by collection<SubFragment>()
	 override val nodes by collection<SubSearchItem>()
	 override val pageInfo by nested<CustomPageInfo>()
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

