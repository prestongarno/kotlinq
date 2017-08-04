package com.prestongarno.transpiler.experimental

import com.prestongarno.ktq.runtime.*
import com.prestongarno.transpiler.experimental.generated.*
import java.util.*

/**
 * Sample setup for what should be built for easiest/most dynamic queries and fragmented queries
 */

class Query<E: GraphType>(val onSuccess: (E) -> Unit, val onError: (Int, String) -> Unit = { i, m -> }) {

	companion object builder {

		val queue = LinkedList<Any>() // caching is for tryhards

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
 *   - Delegate properties used in order to store the arguments for fields internally & check types at object creation
 *   - Possible to even allow an anonymous closure this way
 */
abstract class SearchResultItemConnection : GraphType() {
	protected open val edges: List<SearchResultItemEdge> by lazy { throw SchemaStub() }
	protected open val codeCount: Int by lazy { throw SchemaStub() }
	protected open val issueCount: Int by lazy { throw SchemaStub() }
	protected open val repositoryCount: Int by lazy { throw SchemaStub() }
	protected open val userCount: Int by lazy { throw SchemaStub() }
	protected open val wikiCount: Int by lazy { throw SchemaStub() }
	protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }
	protected open val nodes: List<SearchResultItem> by lazy { throw SchemaStub() }

	// delegate creation is single-threaded so it's safe to put args into a queue and pair with delegate/property when created
	class NodesBuilder(builder: ArgBuilder = ArgBuilder.create()): ArgBuilder by builder {
		fun limitTo(value: Int): NodesBuilder { addArg("limitTo", value); return this; }
		fun first(value: Int): NodesBuilder { addArg("first", value); return this; }
	}
}

class FragmentedQuery() : SearchResultItemConnection() {
	override public val codeCount by field<Int>()
	override public val issueCount by field<Int>()
	override public val pageInfo by field<CustomPageInfo>()
	override public val edges by collection<SubFragment>()

	override public val nodes by NodesBuilder()
			.first(30)
			.limitTo(5)
			.build(this)
			.collection<SubSearchItem>()
}

class BasicUserInfo: User() {
	override public val name by field<String>()
	override public val bio by field<String>()
	override public val company by field<String>()
	//override val repositories by collection<Repository>()
}

fun scratch() {
}

/**
 * Sample sub-class of a SearchResultItemConnection
 *   - may even want to make open superclass fields "protected open"
 *   - Fields are either PropertyMapper, another QueryData subclass, or list of any of <-()
 *   - delegates are simply a wrapper around the standard kotlin "by payloadMap" delegate,
 *       but adding a subclass type argument for mimicking contravariance
 *   - Seems like the simplest solution without a long list of generics and "outs" and "ins" at class signature
 */

class SubSearchItem : SearchResultItem()
class SubFragment : SearchResultItemEdge()

// simpler method above, probably don't even need this
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

open class PageInfo : GraphType()

open class SearchResultItem: GraphType()

class CustomPageInfo : PageInfo()
