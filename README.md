
###ktq

A kotlin type generator for GraphQL schema definitions

The idea behind this is the concept that if you're going to be heavily relying on a graphql API, why not just get rid of any client-side ORM/model errors from the get-go and essentially wrap the entire graphql API in one go? Kotlin's delegates and other constructs are used to allow for expression of a graphql "selection" of fields from a ql type by exposing the supertype kotlin property in a class, delegating it to an interface. Like this: 


```
class BasicUserInfo: User() {
	override val name by field<String>()
	override val bio by field<String>()
	override val company by field<String>()
	override val repositories by collection<Repository>()
}
```

\*The runtime support is still a work in progress
