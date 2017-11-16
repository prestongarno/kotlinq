# <b><span style="color:#f442c2">KTQ :  <small>a kotlin library for type-safe GraphQL DSL</small></span></b>

## <span style="color:#f442c2">About</span>

This project is a [compiler][5] and [runtime][4] for type-safe DSLs modeling a [GraphQL][1] API.

GraphQL is a rigorously tested and thoroughly defined [specification][2] which defines
its architecture as structured data which conforms to a simple type system.  
GraphQL is a natural way to describe ***data types*** and their ***relationships to other types***
(also known as a graph, where nodes and edges describe a confined set of data). You can read more
about it [here][3]

Ktq has the goal of making GraphQL easy to setup and use while taking advantage of
the both pragmatic and type-safe nature of Kotlin. 

## <span style="color:#f442c2">Hello, World</span>

The following example uses the following graphql schema

```
type Query {
  hello: String
}
```

After using the [gradle plugin][5] to create the kotlin types, you can now describe a model

```
val helloQuery = object : QModel(Query) {
  val hello by model.hello
}
```

You now have a GraphQL Query ready to go!

```
helloQuery.toGraphQL()
```

Prints:
```
  {
    hello
  }
```

[Resolve the model][6], by fetching from your endpoint, and then get the results:

```
println(helloQuery.hello)
```

Simple, right? It gets even better!

  [1]: http://graphql.org
  [2]: http://facebook.github.io/graphql
  [3]: http://graphql.org/learn/
  [4]: http://github.com/prestongarno/ktq
  [5]: http://github.com/prestongarno/ktq-gradle
  [6]: 
