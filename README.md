***a Kotlin GraphQL client: type-safe DSL generation & runtime library***
-----------------------------

[ ![Download](https://api.bintray.com/packages/prestongarno/kotlinq/kotlinq-gradle/images/download.svg?version=0.3.0) ](https://bintray.com/prestongarno/kotlinq/kotlinq-gradle/0.3.0/link)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.ktq/ktq-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.ktq/ktq-client)
[![Build Status](https://travis-ci.org/prestongarno/kotlinq.svg?branch=master)](https://travis-ci.org/prestongarno/kotlinq)


## About

* **type-safe DSLs** for querying and mutating your data
* **dynamic queries/mutations** evaluated at runtime
* **custom scalar deserialization** to any native type
* **100% native** code - zero config files, zero old-school DSLs

The [ gradle plugin ](kotlinq-gradle/README.md) generates an equivalent kotlin type hierarchy which 
lets you auto-complete your way to safe, reliable queries and mutations

## Known Issues

0. Basically everything. This was neat idea at first but requires a complete overhaul to be even remotely maintainable going forward. Do not use

## Github query example

```
class ViewerQuery : Model<Query>(Query) {

  val me by model.viewer.querying(::UserModel)

}

class UserModel : Model<User>(User) {
  val name by model.name
}

val myFirstQuery = GraphQL.initialize("https://api.github.com/graphql").apply {
  authorization = TokenAuth(System.getenv("GITHUB_OAUTH_TOKEN"))
}.query(::ViewerQuery)
    .send()

println("Hello ${myFirstQuery.me.name}")
```

The last code block will print "Hello, \<your name here\>"



## More in-depth advanced example

For this github query in the schema, here is how to query repositories with parameters:

```
type Query {
  search(first: Int, after: String, last: Int, before: String, query: String!, type: SearchType!): SearchResultItemConnection!
}
```

Here is the relevant part of the search result definition in the schema:

```
union SearchResultItem = Issue | PullRequest | Repository | User | Organization

type SearchResultItemConnection {
  nodes: [SearchResultItem]
  repositoryCount: Int!
}

scalar URL

type Repository {
  name: String
  description: String
  homepageUrl: URL
}
```

First define a `SearchResultItem` definition,
and since we're only searching for repositories we only need to include the relevant properties:

```
class RepositoryImpl : Model<Repository>(model = Repository) {

  val name: String by model.name

  val description: String by model.description

  // map the custom scalar "URL" to native/platform URL
  val homepageUrl: java.net.URL by model.homepageUrl
      .fromString { url -> java.net.URI(url).toURL() }
}
```

Next, define a `SearchResultConnection` which includes the fragment definition from the above `SearchResultItem` implementation:

```
class SearchResultConnectionImpl : Model<SearchResultItemConnection>(SearchResultItemConnection) {

  val userCount: Int by model.repositoryCount

  // Unfortunately union type queries become erased,
  // but convenience methods are generated for type-safety in the DSL
  val nodes: List<Model<*>> by model.nodes {
    fragment { onRepository(RepositoryImpl) }
  }
}
```


Query definition:

```
class ViewerQuery(queryString: String) : Model<Query>(Query) {

  val searchResults: List<SearchResultConnectionImpl> by model.search
      .withArguments(SearchArgs(type = SearchType.REPO, query = queryString))
      .querying(::SearchResultConnectionImpl) {
        config {
          // max amount of search results github allows, so set this value here
          first = 100 
        }
      }

}
```


