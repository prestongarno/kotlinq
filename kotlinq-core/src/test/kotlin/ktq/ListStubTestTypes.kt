/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ktq
/*
import com.prestongarno.ktq.QSchemaType.QTypeList
import com.prestongarno.ktq.QSchemaType.QScalar
import com.prestongarno.ktq.adapters.StringDelegate

/**
 * Example generated Configuration */
object Configuration : QType {
  val dependencies by QTypeList.stub<ProjImpl, DependenciesArgs> { DependenciesArgs(it) }

  class DependenciesArgs(args: ArgBuilder) : ArgBuilder by args {

    fun first(value: Int) = apply { addArg("first", value) }
    fun after(value: String) = apply { addArg("after", value) }
    fun startAt(value: Int) = apply { addArg("startAt", value) }
  }
}

interface Project : QType {
  val name: StringDelegate<ArgBuilder>
}

object ProjImpl : Project {
  override val name by QScalar.stringStub()
}

class ConfigModel : QModel<Configuration>(Configuration) {
  val depends by model.dependencies.config {
    first(20)
  }.querying { ProjectModel() }
}
class ProjectModel : QModel<ProjImpl>(ProjImpl)

fun main(args: Array<String>) {
  ConfigModel().depends
/*  QCompiler.initialize()
      .schema(File("/home/preston/IdeaProjects/ktq/src/test/resources/graphql.schema.graphqls"))
      .packageName("com.prestongarno.ktq.github")
      .compile()
      .value { println(it) }*/
}*/
