package org.kotlinq.api


interface FragmentContext {
  val fragments: Map<String, Fragment>
}

/**
 * Need to separate [FragmentContext] from [ReifiedFragmentContext] because:
 *
 * ```
 *   queryPerson(id: String) {
 *     node {
 *       name,
 *       DOB,
 *       friends ... etc.
 *     }
 *   }
 * ```
 *
 * is not the same as:
 *
 * ```
 *   query(id: String) {
 *     ... on Person {
 *       name,
 *       DOB,
 *       friends ... etc.
 *     }
 *   }
 * ```
 */
interface ReifiedFragmentContext : Adapter {
  val fragment: Fragment
}