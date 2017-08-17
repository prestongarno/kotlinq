ackage com.prestongarno.ktq.runtime

import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URI

object GraphQL {

	internal val client = OkHttpClient()
	internal var config: Request.Builder? = null

	fun initialize(endpoint: String): QConfiguration {
		val qConfig = QConfig(URI(endpoint))

		config = Request.Builder()
				.url(endpoint)

		return qConfig
	}

	fun send(of: () -> GraphType) {
		val message = of.invoke()
		message.values?.forEach { v -> println("${v.key}::${v.value}") }
		println(message)
	}
}

interface ResponseMapper {
	fun create() : GraphType
}

interface QConfiguration {
	fun authorization(token: String): QConfiguration

	fun header(key: String, value: String): QConfiguration
}

internal class QConfig(val endpoint: URI): QConfiguration {

	override fun authorization(token: String): QConfiguration {
		GraphQL.config?.addHeader("Authorization", token)
		return this
	}

	override fun header(key: String, value: String): QConfiguration {
		GraphQL.config?.addHeader(key, value)
		return this
	}

}