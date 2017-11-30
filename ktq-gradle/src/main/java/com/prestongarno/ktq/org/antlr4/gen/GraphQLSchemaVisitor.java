// Generated from /home/preston/IdeaProjects/ktq-gradle/src/main/resources/GraphQLSchema.g4 by ANTLR 4.7
package com.prestongarno.ktq.org.antlr4.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GraphQLSchemaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GraphQLSchemaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#graphqlSchema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGraphqlSchema(GraphQLSchemaParser.GraphqlSchemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#typeDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDef(GraphQLSchemaParser.TypeDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#implementationDefs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplementationDefs(GraphQLSchemaParser.ImplementationDefsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#inputTypeDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputTypeDef(GraphQLSchemaParser.InputTypeDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#interfaceDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceDef(GraphQLSchemaParser.InterfaceDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#scalarDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarDef(GraphQLSchemaParser.ScalarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#unionDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionDef(GraphQLSchemaParser.UnionDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#unionTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionTypes(GraphQLSchemaParser.UnionTypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#enumDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumDef(GraphQLSchemaParser.EnumDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#scalarName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarName(GraphQLSchemaParser.ScalarNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#fieldDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDef(GraphQLSchemaParser.FieldDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#fieldArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldArgs(GraphQLSchemaParser.FieldArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#fieldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldName(GraphQLSchemaParser.FieldNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(GraphQLSchemaParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#typeSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSpec(GraphQLSchemaParser.TypeSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#listType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListType(GraphQLSchemaParser.ListTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#nullable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullable(GraphQLSchemaParser.NullableContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(GraphQLSchemaParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#defaultValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultValue(GraphQLSchemaParser.DefaultValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(GraphQLSchemaParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#enumValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumValue(GraphQLSchemaParser.EnumValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#arrayValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayValue(GraphQLSchemaParser.ArrayValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#objectValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectValue(GraphQLSchemaParser.ObjectValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLSchemaParser#objectField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectField(GraphQLSchemaParser.ObjectFieldContext ctx);
}