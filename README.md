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
* **No boilerplate** adapter classes or intermediate objects

The [ gradle plugin ](kotlinq-gradle/README.md) generates an equivalent kotlin type hierarchy which 
lets you auto-complete your way to safe, reliable queries and mutations

## Documentation

The documentation is moving (slowly) to a dedicated site. [Check it out](http://kotlinq.org)

## Modules

* `kotlinq-core`: Runtime API
* `kotlinq-gradle`: Gradle code-generating compiler
* `kotlinq-http`: HTTP Utilities using [http4k](http://http4k.org) as a dependency
* `kotlinq-test-api`: Unless you are contributing, ignore this. Contains generated test APIs for `kotlinq-core` unit tests

## Known Issues

0. Basically everything. This was neat cool idea at first but requires a complete overhaul to be even remotely maintainable going forward. Do not use

## Github query example

```
class ViewerQuery : QModel<Query>(Query) {

  val me by model.viewer.querying(::UserModel)

}

class UserModel : QModel<User>(User) {
  val name by model.name
}

val myFirstQuery = GraphQL.initialize("https://api.github.com/graphql").apply {
  authorization = TokenAuth(System.getenv("GITHUB_OAUTH_TOKEN"))
}.query(::ViewerQuery)
    .send()

println("Hello ${myFirstQuery.me.name}")
```


The last code block will print "Hello, \<your name here\>"

