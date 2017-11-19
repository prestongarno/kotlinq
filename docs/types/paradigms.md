
## Creating a GraphQL Object

The compiler creates the structure of the GraphQL schema with an object for each type. 
Each field on a type is created as a variable which, depending on the type of GraphQL field, 
is able to provide a [delegated property](https://kotlinlang.org/docs/reference/delegated-properties.html) 
to any GraphQL model which chooses to include that field in their query.

To create a GraphQL object, subclass the `QModel` class, and add as the type argument for the class the 
GraphQL type which you want to use for your model. For example, consider the following schema:

```
    type Person {
      name: String
    }
```

To make a GraphQL query on this type, here is how you would do it:


```
    object : QModel<Person>(Person)
```

Thats it!


## Adding fields to your GraphQL models

To add a field, you will need to add a property to your `QModel` object 
which delegates to the generated GraphQL object. Here is an example. GraphQL schema:

```
    type Person {
      name: String
    }
```

Kotlin code:

```
    object : QModel<Person>(Person) {

      val personName by model.name

    }

```

Note that this is the same as declaring an actual class like so:

```
    class PersonModel : QModel<Person>(Person) {

      val personName by model.name

    }

```

The property `personName` gets its type information from the `Person` object property delegate provider.
In this case, it is of type `kotlin.String`. You can also explicitly declare its type like so:


    val personName: String by model.name


## Default values on scalar fields

If you try to access the `personName` field in the previous section you will throw a `NullPointerException`
since the query has not been executed! In order to make this avoidable, the library allows you to invoke 
all scalar GraphQL properties and declare default values for the field.

To do this in your model declaration, declare your GraphQL model for `Person` like in the previous sections,
but this time adding a code block on the field and assigning a default value to the property:

```
    class PersonModel : QModel<Person>(Person) {

      val personName by model.name {
        default = "Me"
      }

    }

```

Now, calling `PersonModel().personName` returns `"Me"`!
