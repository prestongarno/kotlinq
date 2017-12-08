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

package com.prestongarno.kotlinq.core.fragments

import com.facebook.Character
import com.facebook.Droid
import com.facebook.Human
import com.facebook.LengthUnit
import com.facebook.Starship
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.custom.StringScalarMapper
import com.prestongarno.kotlinq.core.getFragments
import com.prestongarno.kotlinq.core.primitives.eq
import com.prestongarno.kotlinq.core.stubs.InterfaceListStub
import org.junit.Test

open class BaseDroidModel : QModel<Droid>(Droid) {

  val name by model.name

  val function by model.primaryFunction

  val id by model.id.map(StringScalarMapper { it })

}

class DroidModel(
    friendsFragments: InterfaceListStub<Character, ArgumentSpec>.() -> Unit
) : BaseDroidModel() {

  val friends by model.friends {}.apply(friendsFragments)
}

open class BaseHumanModel : QModel<Human>(Human) {

  val name by model.name

  val spaceShip by model.starships.query(::BaseStarshipModel)

  val id by model.id.map(StringScalarMapper { it })

}

class HumanModel(
    friendsFragments: InterfaceListStub<Character, ArgumentSpec>.() -> Unit
) : BaseHumanModel() {

  val friends by model.friends {  }.apply(friendsFragments)

}

class BaseStarshipModel : QModel<Starship>(Starship) {

  val name by model.name

  val length by model.length(Starship.LengthArgs()) {
    config { unit = LengthUnit.FOOT }
  }

  val id by model.id.map(StringScalarMapper { it })

}

class Traversal {

  @Test fun makeSomeFragments() {

    val invoker1 = {
      HumanModel {
        on {
          HumanModel {
            on(::BaseHumanModel)
            on(::BaseDroidModel)
          }
        }
      }
    }

    val createDroid = {
      DroidModel {
        on(invoker1)
        on {
          DroidModel {
            HumanModel {
              DroidModel {
                HumanModel {
                  DroidModel {
                    DroidModel {
                      BaseDroidModel()
                      BaseHumanModel()
                    }
                    on(::BaseHumanModel)
                    on(::BaseDroidModel)
                  }
                  on(::BaseHumanModel)
                  on(::BaseDroidModel)
                }
                on(::BaseHumanModel)
                on(::BaseDroidModel)
              }
              on(::BaseHumanModel)
              on(::BaseDroidModel)
            }
            on(::BaseHumanModel)
            on(::BaseDroidModel)
          }
        }
      }
    }

    val createHuman = {
      HumanModel {
        on(createDroid)
        on(invoker1)
      }
    }

    val max = 6

    for (i in 1..10_000) {
      require(createHuman().getFragments().count() == max)
    }

    createHuman().toGraphql(pretty = true) eq """
        |{
        |  name
        |  starships {
        |    name
        |    length(unit: FOOT)
        |    id
        |  }
        |  id
        |  friends {
        |    ... on Droid {
        |      name
        |      primaryFunction
        |      id
        |      friends {
        |        ... on Human {
        |          name
        |          starships {
        |            name
        |            length(unit: FOOT)
        |            id
        |          }
        |          id
        |          friends {
        |            ... on Human {
        |              name
        |              starships {
        |                name
        |                length(unit: FOOT)
        |                id
        |              }
        |              id
        |              friends {
        |                ... on Human {
        |                  name
        |                  starships {
        |                    name
        |                    length(unit: FOOT)
        |                    id
        |                  }
        |                  id
        |                }
        |                ... on Droid {
        |                  name
        |                  primaryFunction
        |                  id
        |                }
        |              }
        |            }
        |          }
        |        }
        |        ... on Droid {
        |          name
        |          primaryFunction
        |          id
        |          friends {
        |            ... on Human {
        |              name
        |              starships {
        |                name
        |                length(unit: FOOT)
        |                id
        |              }
        |              id
        |            }
        |            ... on Droid {
        |              name
        |              primaryFunction
        |              id
        |            }
        |          }
        |        }
        |      }
        |    }
        |    ... on Human {
        |      name
        |      starships {
        |        name
        |        length(unit: FOOT)
        |        id
        |      }
        |      id
        |      friends {
        |        ... on Human {
        |          name
        |          starships {
        |            name
        |            length(unit: FOOT)
        |            id
        |          }
        |          id
        |          friends {
        |            ... on Human {
        |              name
        |              starships {
        |                name
        |                length(unit: FOOT)
        |                id
        |              }
        |              id
        |            }
        |            ... on Droid {
        |              name
        |              primaryFunction
        |              id
        |            }
        |          }
        |        }
        |      }
        |    }
        |  }
        |}
        """.trimMargin("|")
  }
}
