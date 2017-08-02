package com.prestongarno.transpiler.experimental

import com.prestongarno.ktq.runtime.QueryData
import java.util.*
import kotlin.reflect.KProperty

/**
 * Sample setup for what should be built for easiest/most dynamic queries and fragmented queries
 */

class Query<E>(val onSuccess: (E) -> Unit, val onError: (Int, String) -> Unit = { i, m -> }) {

	companion object builder {

		val queue = LinkedList<Any>()

		fun exec(): Nothing = TODO()

		fun <T : SearchResultItemConnection<*,*,*>> search(builder: Search.builder<T>): Query.builder {
			println("Created object '$builder'")
			queue.add(builder.search)
			return this
		}
	}

}


abstract class SearchResultItemConnection<out T: SearchResultItemEdge, out K: SearchResultItem, out I: PageInfo>(map: Map<String, Any> = HashMap(9)) : QueryData(map) {
	open protected val codeCount: Int by map
	open protected val edges: List<T> by map
	open protected val issueCount: Int by map
	open protected val nodes: List<K> by map
	open protected val pageInfo: I by map
	open protected val repositoryCount: Int by map
	open protected val userCount: Int by map
	open protected val wikiCount: Int by map
}

class FragmentedQuery: SearchResultItemConnection<SubFragment, SubSearchItem, PageInfo>() {
	override val codeCount: Int by map
	override val edges: List<SubFragment> by map
}

class SubSearchItem: SearchResultItem()
class SubFragment: SearchResultItemEdge()

class Search<out E : SearchResultItemConnection<*,*,*>> internal constructor(val query: String, val type: SearchType, val handler: Query<out E>) {

	private var first: Int = Int.MIN_VALUE
	private var last: Int = Int.MIN_VALUE
	private var after: String = ""
	private var before: String = ""


	companion object {
		fun <T : SearchResultItemConnection<*,*,*>> create(query: String, type: SearchType, handler: Query<out T>): builder<T> {
			return builder<T>(Search(query, type, handler))
		}
	}

	class builder<out E : SearchResultItemConnection<*,*,*>> internal constructor(internal val search: Search<E>) {

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

class PageInfo

open class SearchResultItem
