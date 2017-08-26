
# ktq<sup>*</sup>

An experimental kotlin type generator for GraphQL schema definitions and runtime library for interacting with endpoints

Supports concise, type-safe queries and models. Compiled and tested against open API endpoints such as Github and Yelp. An example model written from the yelp test package (`com.prestongarno.ktq.yelp`):

```
  class BusinessQuery(searchTerm: String) : QModel<Query>() {
    val result: List<BusinessNodesModel> by model.search.config()
        .term(searchTerm)
        .limit(10)
        .build { BusinessesNodesModel() }
  }

  class BusinessesNodesModel : QModel<Businesses>() {
    val resultCount: Int by model.total
    val resultsNodes: List<BusinessBasic> by model.business
        .init { BusinessBasic() }
  }

  class BusinessBasic : QModel<Business>() {
    val name: String by model.name
    val phoneNumber: Int by model.display_phone
    val directUrl: String by model.url
  }
  
```

### compiler
The `compiler` module acts as a kotlin code generation library. This (soon) will be isolated as a Gradle plugin which will allow for configuration from gradle build scripts and run as a task like:

```
ktq {
    schema = flatDir { '/path/to/graphql/schema/definition' }
    targetModule = ':graphql-models'
    destination = 'com.prestongarno.example.GraphQl'
}
```
Where `schema` is the URI or File which the transpiler will parse in order to generate the type hierarchy, and `destination` as the descriptor for the generated kotlin file in the module/subproject defined in `targetModule`

## base api

Allowing for a strict selection of subfields from a type while both supporting type-safe queries and also null safety that kotlin provides is challenging. The generated classes from the GraphQL schema types, interfaces, and unions are represented as interfaces with methods for each property declared in the schema, which return a type `Stub`, which delegates the property initialization to

Using this, consider the following GraphQL schema definition:

```
type User {
  name: String!
  email: String
}
```

which when targeted will allow the compiler to generate the following class:

```
object User : QType {
  fun name: Stub<String> = stub()
  fun email: Stub<String> = stub()
}
```

If schema types were represented as a data class, things would get messy with nulls or worse - because at runtime you'd have to keep track of which queries resulted in which instances to avoid `NullPointerException`s
The class above allows implementations to choose from a selection types and fields in the schema type definitions, which allows for safe collections/bounded type parameters. 
Root types subclass `QModel<out T: QType>`, which provides a field to represent `QType` type argument to delegate the initialization of the value to

 ```
 class BasicUser : QModel<User> {
   val name by model.name
 }
 ```
 
A concrete `BasicUser` type example shows how to specify that you want to include <i>only</i> the field as part of a query or mutation response message.

## custom scalars

Fields which are of a custom scalar type are of type `CustomScalar` by default, an interface mapping to your desired type by use of the `GraphType` utility methods:

```
scalar DateTime

type SomeModelWithDateField {
  dateCreated : DateTime
}

```

generates kotlin types (a simplified version):

```
class DateTime<T: Any> : CustomScalar<T> {
  val value: T by lazy { adapter().invoke( rawData ) }
}

object SomeModelWithDateField : QType {
  val dateCreated : DateTime<Any> = scalarStub<DateTime>()
}
```

which can be implemented, for example to map to `java.util.Date` type like this:

```
class WithDateFieldImpl : QModel<SomeModelWithDateField> {
      myDate by model.date.to { Date.of(Instant.exact(it)) }
}
```

## nested types

This works just like above except you need to use declare the field by this delegate:

`val sample by stub({ Type(args) })` 

where the parameter of `GraphType#field` is `() -> T`  -- which ensures the correct lower bounds, allows for constructor arguments/dependency injectors, etc

To query a nested object field from a root without specifying a new type, call the `mapDirect` function on the supplied stub:

`val followerName: String by follower().mapDirect(User::username)` 

## input on graphql fields

Any input arguments declared in the schema are represented as builder classes, and allow for a flexible combination for querying/mutation. See the yelp model at the top of the README for an example

## collections
Functions exactly like nested types, `init({ elementInitializer() })`

## queries and mutations 

The queries from classes like shown above are generated on-the-fly and submitted
