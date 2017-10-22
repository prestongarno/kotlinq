package com.prestongarno.ktq

import com.prestongarno.ktq.QSchemaType.QTypeList
import com.prestongarno.ktq.QSchemaType.QScalar
import com.prestongarno.ktq.adapters.StringDelegate

/**
 * Example generated Configuration */
object Configuration : QSchemaType {
  val dependencies by QTypeList.stub<ProjImpl, DependenciesArgs> { DependenciesArgs(it) }

  class DependenciesArgs(args: ArgBuilder) : ArgBuilder by args {

    fun first(value: Int) = apply { addArg("first", value) }
    fun after(value: String) = apply { addArg("after", value) }
    fun startAt(value: Int) = apply { addArg("startAt", value) }
  }
}

interface Project : QSchemaType {
  val name: StringDelegate<ArgBuilder>
}

object ProjImpl : Project {
  override val name by QScalar.stringStub()
}

class ConfigModel : QModel<Configuration>(Configuration) {
  val depends by model.dependencies.config {
    first(20)
  }.init { ProjectModel() }
}
class ProjectModel : QModel<ProjImpl>(ProjImpl)

fun main(args: Array<String>) {
  ConfigModel().depends
/*  QCompiler.initialize()
      .schema(File("/home/preston/IdeaProjects/ktq/src/test/resources/graphql.schema.graphqls"))
      .packageName("com.prestongarno.ktq.github")
      .compile()
      .value { println(it) }*/
}
