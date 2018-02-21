***a Kotlin GraphQL client: type-safe DSL generation & runtime library***
-----------------------------

[ ![Download](https://api.bintray.com/packages/prestongarno/kotlinq/kotlinq-core/images/download.svg?version=0.4.0) ](https://bintray.com/prestongarno/kotlinq/kotlinq-core/0.4.0/link)
[![Build Status](https://travis-ci.org/prestongarno/kotlinq.svg?branch=master)](https://travis-ci.org/prestongarno/kotlinq)


## About

* Quickly fetch from a GraphQl server using **dynamic queries/mutations** evaluated at runtime

## Un-typed GraphQL query DSL (version 0.4.0) (new)

Version 0.4.0 supports [**ad-hoc, untyped**](https://github.com/prestongarno/kotlinq/blob/query-dsl/query-dsl/src/main/kotlin/org/kotlinq/dsl/extensions/FreePropertyExtensionScope.kt) but natively expressed queries and mutations!


A powerful feature is the ability to **compose** and **reuse** graphql queries easily.

### GraphQL Star Wars example

Define a "Human" type fragment:

```
    fun humanDef() = fragment("Human") {
      "name"(string)
      "nicknames" listOf !string
    }
```

Define a "Droid" type fragment:

```
    fun droidDef() = fragment("Droid") {
      "modelNumber"(string)
      "owner" on humanDef()
    }
```

Now, define a query for Star Wars characters:

```
    fun charactersQuery(fragments: List<Fragment>) = query {
      "characters"("first" to 10)..listOf {
        on..fragments
      }
    }
```

Query characters from Star Wars that are humans:

```
    val query = charactersQuery(listOf(humanDef())
    println(query.toGraphQl(pretty = true))
```

prints: 

```
{
  characters(first: 100) {
    __typename
    ... on Human {
      name
      nicknames
    }
  }
}
```

But if you want to query both humans and droids, to do so it's as simple as:

```
    val query = charactersQuery(listOf(humanDef(), droidDef()))
    println(query.toGraphQl(pretty = true))
```


which results in:

```
{
  characters(first: 100) {
    __typename
    ... on Human {
      name
      nicknames
    }
    ... on Droid {
      modelNumber
      maker {
        name
        nicknames
      }
    }
  }
}
```
