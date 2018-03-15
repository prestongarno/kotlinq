# <b><span style="color:#f442c2">Kotlinq :  <small>a kotlin library for easy, type-safe GraphQL DSLs</small></span></b>

## <span style="color:#f442c2">About</span>

This project is a type-safe Kotlin DSL for GraphQL.

## <span style="color:#f442c2">Project goals</span>

Kotlinq has the goal of making GraphQL easy to integrate in an existing project in a scalable manner 
while taking advantage of the both pragmatic and null/type-safe nature of Kotlin. 

## <span style="color:#f442c2">What is GraphQL?</span>

GraphQL is a thoroughly defined [specification][2] which defines
its architecture as structured data conforming to a simple, declarative type system.

GraphQL is a natural way to describe ***data types*** and their ***relationships to other types***
(also known as a graph, where nodes and edges describe a confined set of data). You can learn more
about it [here][3]

The GraphQL type system provides null-safety which is quite convenient.

## <span style="color:#f442c2">What does the name *kotlinq* even mean?</span>

\[Kot\]lin \[L\]anguage \[I\]ntegrated \[Q\]ueries

## <span style="color:#f442c2"><a href="https://github.com/prestongarno/kotlinq"/>Development & issue tracker</a></span>


## <span style="color:#f442c2">Getting started</span>

There are currently 2 DSLs:

1. `kotlinq-dsl`: Un-typed DSL for simply fetching data. Does not support converting to JVM types.
2. `kotlinq-jvm`: Somewhat type-safe DSL, experimental. Supports defining fragments based on kotlin classes, validating a GraphQL response, and also resolving the response to native JVM class instances. It is unfortunately restrictive and requires tight coupling, so use with caution.

The next section contains a run-down of shared concepts between the DSL modules.


  [1]: http://graphql.org
  [2]: http://facebook.github.io/graphql
  [3]: http://graphql.org/learn/
  [4]: http://github.com/prestongarno/kotlinq
  [5]: http://github.com/prestongarno/kotlinq/kotlinq-gradle
