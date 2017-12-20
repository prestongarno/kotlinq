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

1. **Nullable fields**: Everything is assumed to be a non-null (see [this issue](https://github.com/prestongarno/kotlinq/issues/91), implementation is in the works)
2. **Kotlin language keywords as identifiers** are not supported (i.e. 'object' is not a valid GraphQL field name)
3. **'ID' scalar**: Is now part of the GraphQL specification, but you need to manually add it to your schema

## Basic tutorial (how to query your github username):

1.  **Curl the GitHub public GraphQL API schema** (requires a github.com OAuth token):


```
curl -H "Authorization: bearer $YOUR_GITHUB_OAUTH_TOKEN_HERE" \
  -H "Accept: application/vnd.github.v4.idl" \
  https://api.github.com/graphql | \
  sed -e 's/^{\|^.\{11\}\|^}//g' | \
  sed -e 's/\\n/\n/g' > \
  src/main/resources/github.graphqls; echo 'scalar ID' >> src/main/resources/github.graphqls
```


2.  **Apply the Gradle plugin**:


```
  buildscript {

    repositories {
      jcenter()
    }

    dependencies {
      classpath 'com.prestongarno.kotlinq:kotlinq-gradle:0.3.0'
    }

  }

  apply plugin: 'com.prestongarno.kotlinq'

```

3.  **Configure gradle the plugin to generate a Kotlin API from the GitHub schema**:


```
kotlinq {
  schema {
    target = "src/main/resources/github.graphqls"
    kotlinFileName = "GitHubGraphQl.kt"
    packageName = "com.github.graphql"
    outputDir = 'src/generated/kotlin'
  }
}
```

4.  **Add the generated source directory to include in compilation**:

`sourceSets.main.java.srcDirs +=  'src/generated/kotlin'`


5.  **Create a query and execute**:

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

