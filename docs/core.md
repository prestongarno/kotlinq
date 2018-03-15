# <b><span style="color:#f442c2">Core (kotlinq-core)</span></b>

## <span style="color:#f442c2">About</span>

This is the core module which provides a set of classes to compose & extend in functionality (i.e. for creating a DSL). Below is a quick overview so that you can connect the concepts when working with any of the DSL modules. Main concepts:

1. **Fragment**: Main interface for composing a GraphQL request
2. **Printer**: Highly configurable class for controlling the actual GraphQL request format. Don't like curly braces? Want to decorate GraphQL types in your request with custom properties? The Printer provides an easy-to-use interface via Builders for easily and safely doing things like this.
3. **Graph Visitor**: An interface for traversing a GraphQL request structure

## <span style="color:#f442c2">Fragment</span>

A Fragment, just like in the GraphQL spec, is essentially a 

* Type name
* *Selection Set*

The *Selection Set* is a declaration specifying fields from that type. They can be either:

1. Scalars (i.e. primitives, or your own custom ones)
2. Other fragments

A Fragment is a recursively defined data structure. In kotlinq-core, fragments and their properties do not rely on JVM reflection, but rather uses its own, simplified reflection system to enforce type safety.

## <span style="color:#f442c2">Printer</span>

A GraphQL request printer is composed of a

1. PrintingConfiguration: Specification for the format of the request. Provided configurations are *pretty print* and *non-pretty* (single line, optimized for space)
2. MetaStrategy: Specification for intercepting & transforming the printing of the actual structure of a request

Example usage:

```
 val printer = Printer.fromConfiguration(PrintingConfiguration.PRETTY)
      .toBuilder()
      .metaStrategy(MetaStrategy.builder()
          .includeId()
          .includeTypename()
          .include("TODO") { fragment ->
             fragment.typeName == "User"
          }
          .build())
      .indent("\t")
      .build()
```

The above `printer` shows how to format your requests as pretty-printed with tab indents, and also adds a property called "TODO" to a fragment selection set when the fragment is of type `User`.

Note that fragments themselves can be easily formatted for standard day-to-day usage:

```
  val fragment = ...
  val stringQuery = fragment.toGraphQl(
      pretty = true,
      idAndTypeName = true)
```

## <span style="color:#f442c2">Graph Visitor</span>

This is an interface for analyzing fragment structure. Internally, the Printer and many other features use this interface under the hood. For example, here is the implementation of the Fragment's overload of the `in` operator (for checking if a fragment is defined within another one, e.g. `if (fragment in otherFragment) doSmth();`):


```
  operator fun Fragment.contains(other: Fragment): Boolean {
    var result = false

    GraphVisitor.builder()
        .onNotifyEnter {
          other != it || let {
            result = true
            false // stop traversing, found match
          }
        }
        .build()
        .let(::traverse)

    return result
  }
```
