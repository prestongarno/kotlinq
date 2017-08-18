
# ktq<sup>*</sup>

An experimental kotlin type generator for GraphQL schema definitions and runtime library for interacting with endpoints

### compiler
The `compiler` module acts as a code generation library. This (soon) will be isolated as a Gradle plugin which will allow for configuration from gradle build scripts and run as a task like:

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
interface User : QType() {
  fun name: Stub<String> = stub()
  fun email: Stub<String> = stub()
}
```

If schema types were represented as a data class, things would get messy with nulls or worse - because at runtime you'd have to keep track of which queries resulted in which instances to avoid `NullPointerException`s
The class above allows implementations to choose from a selection types and fields in the schema type definitions, which allows for safe collections/bounded type parameters. 
Root types subclass `QType`, which provides utility methods to supply delegates for fields.

 ```
 class BasicUser : User {
   val name by name()
 }
 ```
 
A concrete `User` type example shows how to specify that you want to include <i>only</i> the field as part of a query or mutation response message.

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

class SomeModelWithDateField : GraphType() {
  protected open val dateCreated : DateTime<Any> by lazy { throw SchemaStub() }
}
```

which can be implemented, for example to map to `java.util.Date` type like this:

```
class WithDateFieldImpl : GraphType() {
      public override val dateCreated by scalar<Date> { Date.of(Instant.exact(it)) }
}
```

## nested types

This works just like above except you need to use declare the field by this delegate:

`val sample by stub({ Type(args) })` 

where the parameter of `GraphType#field` is `() -> T`  -- which ensures the correct lower bounds, allows for constructor arguments/dependency injectors, etc

To query a nested object field from a root without specifying a new type, call the `mapDirect` function on the supplied stub:

`val followerName: String by follower().mapDirect(User::username)` 

## input on graphql fields

Any input arguments declared in the schema are represented as builder classes, and allow for a flexible combination for querying/mutation. A field with arguments can be expressed like so:

```
    class QueryFooBar : User() {
    
        val name by string()
        
        val repositories by RepositoriesArgs()
                .affiliations(listOf( OWNER, COLLABORATOR ))
                .orderBy(object : RepositoryOrder(field = CREATED_AT, order = ASCENDING)
                .first(100)
                .privacy(PUBLIC)
                .build().repository { RepoConnection() }
    }
```
Some types in this example are missing, but the builder configures the arguments for the field at the time the delegate is created, and then after `.build()` it returns this instance from which the field type can be specified ( in this case `repositories` : `RepoConnection` )

## collections
Functions exactly like nested types, `someList({ elementInitializer() })`

## queries and mutations 
The queries from classes like shown above are generated on-the-fly and submitted, providing a `Query<Result>` for a callback. Example query generated from the above class:

<sup>* syntax errors: the payload generation is still in progress </sup>

```
  {
    getUser(
      QueryFooBar {
        name
        repositories(
            affiliations: [ OWNER, COLLABORATOR ],
            orderBy: RepositoryOrder {
                direction = ASC
                field = CREATED_AT
            },
            privacy: PUBLIC,
            first: 100
        )
    }
  }
```

\* NOTE: this is experimental and at the time of writing is <b>not</b> tested thoroughly enough to be trusted as anything reliable
