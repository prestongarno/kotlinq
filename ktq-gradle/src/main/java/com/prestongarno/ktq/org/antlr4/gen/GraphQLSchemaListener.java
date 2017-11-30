// Generated from /home/preston/IdeaProjects/ktq-gradle/src/main/resources/GraphQLSchema.g4 by ANTLR 4.7
package com.prestongarno.ktq.org.antlr4.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GraphQLSchemaParser}.
 */
public interface GraphQLSchemaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#graphqlSchema}.
	 * @param ctx the parse tree
	 */
	void enterGraphqlSchema(GraphQLSchemaParser.GraphqlSchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#graphqlSchema}.
	 * @param ctx the parse tree
	 */
	void exitGraphqlSchema(GraphQLSchemaParser.GraphqlSchemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#typeDef}.
	 * @param ctx the parse tree
	 */
	void enterTypeDef(GraphQLSchemaParser.TypeDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#typeDef}.
	 * @param ctx the parse tree
	 */
	void exitTypeDef(GraphQLSchemaParser.TypeDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#implementationDefs}.
	 * @param ctx the parse tree
	 */
	void enterImplementationDefs(GraphQLSchemaParser.ImplementationDefsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#implementationDefs}.
	 * @param ctx the parse tree
	 */
	void exitImplementationDefs(GraphQLSchemaParser.ImplementationDefsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#inputTypeDef}.
	 * @param ctx the parse tree
	 */
	void enterInputTypeDef(GraphQLSchemaParser.InputTypeDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#inputTypeDef}.
	 * @param ctx the parse tree
	 */
	void exitInputTypeDef(GraphQLSchemaParser.InputTypeDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#interfaceDef}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDef(GraphQLSchemaParser.InterfaceDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#interfaceDef}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDef(GraphQLSchemaParser.InterfaceDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#scalarDef}.
	 * @param ctx the parse tree
	 */
	void enterScalarDef(GraphQLSchemaParser.ScalarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#scalarDef}.
	 * @param ctx the parse tree
	 */
	void exitScalarDef(GraphQLSchemaParser.ScalarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#unionDef}.
	 * @param ctx the parse tree
	 */
	void enterUnionDef(GraphQLSchemaParser.UnionDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#unionDef}.
	 * @param ctx the parse tree
	 */
	void exitUnionDef(GraphQLSchemaParser.UnionDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#unionTypes}.
	 * @param ctx the parse tree
	 */
	void enterUnionTypes(GraphQLSchemaParser.UnionTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#unionTypes}.
	 * @param ctx the parse tree
	 */
	void exitUnionTypes(GraphQLSchemaParser.UnionTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#enumDef}.
	 * @param ctx the parse tree
	 */
	void enterEnumDef(GraphQLSchemaParser.EnumDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#enumDef}.
	 * @param ctx the parse tree
	 */
	void exitEnumDef(GraphQLSchemaParser.EnumDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#scalarName}.
	 * @param ctx the parse tree
	 */
	void enterScalarName(GraphQLSchemaParser.ScalarNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#scalarName}.
	 * @param ctx the parse tree
	 */
	void exitScalarName(GraphQLSchemaParser.ScalarNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#fieldDef}.
	 * @param ctx the parse tree
	 */
	void enterFieldDef(GraphQLSchemaParser.FieldDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#fieldDef}.
	 * @param ctx the parse tree
	 */
	void exitFieldDef(GraphQLSchemaParser.FieldDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#fieldArgs}.
	 * @param ctx the parse tree
	 */
	void enterFieldArgs(GraphQLSchemaParser.FieldArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#fieldArgs}.
	 * @param ctx the parse tree
	 */
	void exitFieldArgs(GraphQLSchemaParser.FieldArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#fieldName}.
	 * @param ctx the parse tree
	 */
	void enterFieldName(GraphQLSchemaParser.FieldNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#fieldName}.
	 * @param ctx the parse tree
	 */
	void exitFieldName(GraphQLSchemaParser.FieldNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(GraphQLSchemaParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(GraphQLSchemaParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#typeSpec}.
	 * @param ctx the parse tree
	 */
	void enterTypeSpec(GraphQLSchemaParser.TypeSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#typeSpec}.
	 * @param ctx the parse tree
	 */
	void exitTypeSpec(GraphQLSchemaParser.TypeSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#listType}.
	 * @param ctx the parse tree
	 */
	void enterListType(GraphQLSchemaParser.ListTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#listType}.
	 * @param ctx the parse tree
	 */
	void exitListType(GraphQLSchemaParser.ListTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#nullable}.
	 * @param ctx the parse tree
	 */
	void enterNullable(GraphQLSchemaParser.NullableContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#nullable}.
	 * @param ctx the parse tree
	 */
	void exitNullable(GraphQLSchemaParser.NullableContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(GraphQLSchemaParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(GraphQLSchemaParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(GraphQLSchemaParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(GraphQLSchemaParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(GraphQLSchemaParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(GraphQLSchemaParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#enumValue}.
	 * @param ctx the parse tree
	 */
	void enterEnumValue(GraphQLSchemaParser.EnumValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#enumValue}.
	 * @param ctx the parse tree
	 */
	void exitEnumValue(GraphQLSchemaParser.EnumValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#arrayValue}.
	 * @param ctx the parse tree
	 */
	void enterArrayValue(GraphQLSchemaParser.ArrayValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#arrayValue}.
	 * @param ctx the parse tree
	 */
	void exitArrayValue(GraphQLSchemaParser.ArrayValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#objectValue}.
	 * @param ctx the parse tree
	 */
	void enterObjectValue(GraphQLSchemaParser.ObjectValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#objectValue}.
	 * @param ctx the parse tree
	 */
	void exitObjectValue(GraphQLSchemaParser.ObjectValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLSchemaParser#objectField}.
	 * @param ctx the parse tree
	 */
	void enterObjectField(GraphQLSchemaParser.ObjectFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLSchemaParser#objectField}.
	 * @param ctx the parse tree
	 */
	void exitObjectField(GraphQLSchemaParser.ObjectFieldContext ctx);
}