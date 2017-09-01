package com.prestongarno.transpiler.qlang.spec

import com.squareup.kotlinpoet.TypeSpec
import com.prestongarno.ktq.QInput
import com.squareup.kotlinpoet.*

class QInputType(name: String, fields: List<QField>) : QStatefulType(name, fields) {
  override fun toKotlin(): TypeSpec = create()

  private fun create(): TypeSpec =
      if (kotlinSpec != null) kotlinSpec!! else
        TypeSpec.classBuilder(this.name)
            .addModifiers(KModifier.DATA)
            .addSuperinterface(QInput::class)
            .addProperties(fields.map {
              PropertySpec.builder(it.name, ClassName.bestGuess(it.type.name), KModifier.PRIVATE)
                  .mutable(it.nullable)
                  .initializer(if (it.nullable) "null" else it.name)
                  .build()
            })
            .primaryConstructor(createConstructor(fields.filterNot { it.nullable }))
            .addFunctions(fields.filter { it.nullable }.map {
              FunSpec.builder(it.name)
                  .addParameter(ParameterSpec.builder("model", ClassName.bestGuess(it.type.name))
                      .build())
                  .addCode(CodeBlock.builder().addStatement("return apply { ${it.name} = model }").build())
                  .build()
            }).build()

  private fun createConstructor(params: List<QField>): FunSpec = FunSpec.constructorBuilder()
      .addParameters(params.map {
        ParameterSpec.builder(it.name, ClassName.bestGuess(it.type.name)).build()
      }).build()
}
