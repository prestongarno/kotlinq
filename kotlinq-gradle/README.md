# kotlinq-gradle

[ ![Download](https://api.bintray.com/packages/prestongarno/kotlinq/kotlinq-gradle/images/download.svg?version=0.3.0-RC1) ](https://bintray.com/prestongarno/kotlinq/kotlinq-gradle/0.3.0-RC1/link)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.ktq/ktq-gradle/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.ktq/ktq-gradle)
 [ ![jcenter](https://api.bintray.com/packages/prestongarno/ktq/ktq-gradle/images/download.svg?version=0.2) ](https://bintray.com/prestongarno/ktq/ktq-gradle/0.2/link)

Generates a static representation of your schema

## How to use

1. Add the plugin to the buildscript classpath (where your kotlin plugin is)

    `classpath 'com.prestongarno.kotlinq:kotlinq-gradle:0.3.0-RC2`
    
2. If you haven't already, specify either JCenter or maven Central Repository in the buildscript

       repositories {
         maven { mavenCentral() }  // Or jcenter()
       }

3. Apply the plugin

    `apply plugin: 'com.prestongarno.kotlinq'`

4. Configure the plugin to target & compile your schema as kotlin by inserting a config block titled `kotlinq` in your `build.gradle`.
See the next section for how to do this


## Configuration

The list below shows all possible options which you can specify in the `kotlinq` configuration closure. Only the first option is required.

<dl>
  <dt>schema</dt>
  <dd>Insert the relative or fully qualified path to your GraphQl schema. Defaults to '$buildDir/generated/kotlinq/'</dd>

  <dt>targetDir</dt>
  <dd>The location for the generated source files. <b>NOTE:</b> <i>The plugin does not support JVM/JS compiling, configuring as dependencies, or adding generated source to to your project classpath!</i> See this <a href="https://stackoverflow.com/questions/20700053/how-to-add-local-jar-file-dependency-to-build-gradle-file">StackOverflow answer</a> for an easy solution</dd>

  <dt>packageName</dt>
  <dd>Insert the package name to use for the generated kotlin types</dd>

  <dt>kotlinName</dt>
  <dd>Insert the file name to use for the generated file. Otherwise, it will use the schema name (changing it to a JVM-compliant name if not already)</dd>
</dl>

## Example configuration

        kotlinq {

          schema = 'src/resources/idl.graphqls'

          targetDir = 'generated/kotlin/src/'

          packageName = 'com.example.graphql'

          kotlinName = 'GraphqlJvmTypes'
        }
