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

open class SearchResultItemConnection(testMap: Map<String, Any>) : QueryData(testMap) {
	 open val codeCount: Int by lazy { throw notDeclaredErr() }
	 open val issueCount: Int by lazy { throw notDeclaredErr() }
	 open val repositoryCount: Int by lazy { throw notDeclaredErr() }
	 open val userCount: Int by lazy { throw notDeclaredErr() }
	 open val wikiCount: Int by lazy { throw notDeclaredErr() }
	 open val pageInfo: PageInfo by lazy { throw notDeclaredErr() }
	 open val edges: List<out SearchResultItemEdge> by lazy { throw notDeclaredErr() }
	 open val nodes: List<out SearchResultItem> by lazy { throw notDeclaredErr() }

	 fun <T : SearchResultItemEdge> edges(): List<T> = get<List<T>>("edges")
	 fun <T : SearchResultItem> nodes(): List<T> = get<List<T>>("nodes")

	@Suppress("UNCHECKED_CAST") protected fun <T : PageInfo> pageInfo(): (T) = get<PageInfo>("pageInfo") as T

}

class FragmentedQuery(testMap: Map<String, Any>) : SearchResultItemConnection(testMap) {
	 override val codeCount: Int by primitives
	 override val issueCount: Int by primitives
	 override val edges by super.edges<SubFragment>()
	 override val nodes by super.nodes<SubSearchItem>()
	 override val pageInfo by super.pageInfo<CustomPageInfo>()
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

