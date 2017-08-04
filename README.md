
###ktq

A kotlin type generator for GraphQL schema definitions

The idea behind this is the concept that if you're going to be heavily relying on a graphql API, why not just get rid of any client-side ORM/model errors from the get-go and essentially wrap the entire graphql API in one go? Kotlin's delegates and other constructs are used to allow for expression of a graphql "selection" of fields from a ql type by exposing the supertype kotlin property in a class, delegating it to an interface. Like this: 


```
class BasicUserInfo: User() {
	override public val name by field<String>()
	override public val bio by field<String>()
	override public val company by field<String>()
	override public val repositories by collection<Repository>()
}
```
It even allows for complex arguments/inputs for selection/querying/mutating by generating builders. A field with arguments can be expressed like so:

```
	override public val nodes by NodesBuilder()
			.first(30)
			.limitTo(5)
			.build(this)
			.collection<SubSearchItem>()
```

\*The runtime support is still a work in progress
