# kotlinq-gradle

[ ![Download](https://api.bintray.com/packages/prestongarno/kotlinq/kotlinq-gradle/images/download.svg?version=0.3.0-RC2) ](https://bintray.com/prestongarno/kotlinq/kotlinq-gradle/0.3.0-RC2/link)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.kotlinq/kotlinq-gradle/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.kotlinq/kotlinq-gradle)
 [ ![jcenter](https://api.bintray.com/packages/prestongarno/kotlinq/kotlinq-gradle/images/download.svg?version=0.3.0-RC2) ](https://bintray.com/prestongarno/kotlinq/kotlinq-gradle/0.3.0-RC2/link)

Generates a static representation of your schema

## How to use

1. Add the plugin to the buildscript classpath (where your kotlin plugin is)

    `classpath 'com.prestongarno.kotlinq:kotlinq-gradle:0.3.0-RC2`

2. If you haven't already, specify either JCenter or maven Central Repository in the buildscript

       repositories {
         jcenter()
       }

3. Apply the plugin

    `apply plugin: 'com.prestongarno.kotlinq'`

4. Configure the plugin (replace the paths & names with your use case) :

```
kotlinq {

  schema {

    target = "$sourceSets.main.resources/starwars.graphqls"

    kotlinFileName = "StarWars.kt"

    packageName = "com.facebook"

    outputDir = "$sourceSets.generated.java"
  }

  schema {
    ...
  }
}
```

5. Add as many schemas as you want within the `kotlinq` block


### ***See the [build.gradle](build.gradle) file for this module to see an example of how to use multiple GraphQL APIs in a project***
