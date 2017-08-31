package com.prestongarno.ktq

object Configuration : QSchemaType {
  val dependencies : ListInitStub<ProjImpl> = typeListStub<ProjImpl>()

  class DependenciesArgs(args: TypeArgBuilder = TypeArgBuilder.create<Project, DependenciesArgs>())
    : TypeArgBuilder by args {

    fun first(value: Int) = apply { addArg("first", value) }
    fun after(value: String) = apply { addArg("after", value) }
    fun startAt(value: Int) = apply { addArg("startAt", value) }
  }
}

interface Project : QSchemaType {
  val name : Stub<String>
}

object ProjImpl : Project {
  override val name: Stub<String> = stub()
}

class ConfigModel : QModel<Configuration>(Configuration::class) {
  val depends by model.dependencies
      .init { ProjectModel() }
}
class ProjectModel : QModel<ProjImpl>(ProjImpl::class)

fun main(args: Array<String>) {
  ConfigModel().depends
}