package com.prestongarno.transpiler

/**
 * Created by preston on 7/20/17.
 */

class QLParser(rootQueryType: String, rootMutationType: String) {
}

enum class Keywords(mapper: Consumer<QDefinedType>) {
	TYPE(() -> ),
	INTERFACE,
	UNION,
	INPUT;

	abstract <T : QDefinedType> fun createIr() : (T) {

	}
}

