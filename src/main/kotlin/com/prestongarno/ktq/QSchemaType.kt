package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.*
import com.prestongarno.ktq.internal.Grub

// TODO This file and the entire API could use a bit of the DRY principle
interface QSchemaType {

  object QScalar {

     fun <T> stub(): Grub<Stub<T>> = Grub { ScalarStubAdapter<T, ArgBuilder>(it, { it }) }

     fun <T : Any, A : ArgBuilder> configStub(arginit: (ArgBuilder) -> A): Grub<QConfigStub<T, A>>
        = Grub { ScalarStubAdapter<T, A>(it, arginit) }
  }

  object QType {

     fun <T : QSchemaType> stub(): Grub<InitStub<T>> = Grub { TypeStubAdapter<T, QModel<T>, TypeArgBuilder>(it, { it }) }

     fun <T : QSchemaType, A : TypeArgBuilder> configStub(arginit: (TypeArgBuilder) -> A): Grub<QTypeConfigStub<T, A>>
        = Grub { TypeStubAdapter<T, QModel<T>, A>(it, arginit) }
  }

  object QScalarList {

     fun <T> stub(): Grub<ListStub<T>> = Grub { ScalarListAdapter<T, ListArgBuilder>(it, { it }) }

     fun <T : Any, A : ListArgBuilder> configStub(arginit: (ListArgBuilder) -> A): Grub<ListConfig<T, A>>
        = Grub { ScalarListAdapter<T, A>(it, arginit) }
  }

  object QTypeList {

     fun <T : QSchemaType> stub(): Grub<ListInitStub<T>>
        = Grub { TypeListAdapter<T, QModel<T>, TypeListArgBuilder>(it, { it }) }

     fun <T : QSchemaType, A : TypeListArgBuilder> configStub(arginit: (TypeListArgBuilder) -> A): Grub<ListConfigType<T, A>>
        = Grub { TypeListAdapter<T, QModel<T>, A>(it, arginit) }
  }
}

