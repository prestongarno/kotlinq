package org.kotlinq.graphql

import org.kotlinq.introspection.Kind

/**
 * Interface for a GraphQL schema
 * TODO to contribute to and use KGraphQL or not...
 * @since 0.4.2
 */
interface Schema {

  val queries: List<Property>

  val mutations: List<Property>

  val subscriptions: List<Property>

  val fields: List<Property>

}

// "Property" is a field or method in OO language
// depending on whether it requires arguments
data class Property(
    val name: String,
    val type: Kind,
    val arguments: List<Argument>)

data class Argument(
    val name: String,
    val type: Kind)



// Put this in the network client module
interface RemoteSchema : Schema {
  val endpoint: java.net.URI
  // Should be like "com.github.graphql.repositoryManager" for repository stuff,
  // "com.github.graphql.analytics" for analytics, etc...
  // will correspond to a collection of Kafka topics which will be used for streaming subscriptions,
  // and Kafka tables which correspond to queries and mutations TODO use pure kafka first though
  val namespace: String

  // implementation:
  // private val Executor

  interface Executor {
    // network client should depend on kotlinq-core, so something like:
    // fun canExecuteRequest(org.kotlinq.api.Fragment): Boolean
    // fun subscribe(handler: (Event) => Any): Subscription
    // fun query(): Query
    // fun mutate(): Mutation
  }
}

// Refactor the graphql-compiler module to parse
// remote schemas for dynamic host registration for safe queries at runtime, and implement this
// interface to be used to create a client registering with an remote endpoint
interface SchemaParser {
  fun parseFromInterfaceDefinitionLanguage(schema: String): Schema?
}

// Put this in another module which joins kotlinq-core & the server abstraction core module, remote schemas/endpoints should be
// decoupled entirely from the client in theory even if they are running within the same JVM process
interface GraphQlClient {
  companion object {
    fun createClient(block: GraphQlClientDslScope.() -> Unit) =
        GraphQlClientDslScope(TODO(), block).toClient()
  }
}

// DSL builder for a graphql client
class GraphQlClientDslScope(/* @Inject */ schemaGenerator: SchemaParser, block: GraphQlClientDslScope.() -> Unit) {

  init { this.block() }

  // TODO add methods to configure the client here

  fun toClient(): GraphQlClient? = TODO()
}

// TODO should this be a "broker" or a "registry"? Because broker is encapsulated in
// Kafka and should be just a service location service for remote graphql microservices
interface GraphQlBroker {

  val endpoint: java.net.URL

  fun connectToRemoteSchema(namespace: String): RemoteSchema
}