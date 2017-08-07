
# ktq

A kotlin type generator and runtime library for GraphQL schema definitions

The idea behind this is the concept that if a project extensively uses a graphql API, why not forget client-side ORM/model errors by essentially wrapping the entire graphql API and including it in the project? Kotlin's delegates and other constructs are used to allow for expression of a graphql "selection" of fields from a database type by exposing the generated supertype property in a class. Like this: 


```
class BasicUserInfo: User() {
	override public val name by field<String>()
	override public val bio by field<String>()
	override public val company by field<String>()
	override public val repositories by collection<Repository>()
}
```
Any input arguments declared in the schema are represented as builder classes, and allow for a flexible combination for querying/mutation. A field with arguments can be expressed like so:

```
	override public val nodes by NodesBuilder()
			.first(30)
			.startingAt("foo")
			.build(this)
			.collection<SubSearchItem>()
```

\*The runtime support is still a work in progress
