package com.prestongarno.ktq


/** The entrypoint of a runtime API which provides the data
 */
class QClient {
	companion object {
		fun <K : QType> submitTask(init: () -> K): K {
			val inst = init.invoke()
			TODO()
		}
	}
}
