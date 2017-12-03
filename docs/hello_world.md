# <span style="color:#f442c2">'Hello, world!' example</span>

The following code conforms to the following graphql schema

```
type Query {
  hello: String
}
```

After using the [gradle plugin][1] to create the kotlin types, you can write a query in kotlin:

```
val helloQuery = object : QModel(Query) {
  val hello by model.hello
}
```

You now have a GraphQL Query ready to go!

```
helloQuery.toGraphQL(pretty = true)
```

Prints:
```
  {
    hello
  }
```

[Resolve the model][2], by fetching from your endpoint, and then get the results:

```
println(helloQuery.hello)
```

Simple, right? It gets even better!

  [1]: http://github.com/prestongarno/kotlinq/kotlinq-gradle
