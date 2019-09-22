package com.amw.lottodemoapp.model

import kotlin.collections.LinkedHashMap

data class SearchResult(val pair: Pair<Int, Int>, val draws : MutableSet<Draw>) {
    val size = draws.size
    val associatedNumbers = createAssociatedNumbers()
    val associatedPairs = 1

    private fun createAssociatedNumbers(): Map<Int, Int> {
        var result = LinkedHashMap<Int, Int>()
        draws.forEach { element ->
            element.numbers
                    .filter { it !in setOf(pair.first, pair.second) }
                    .forEach { number ->
                if (result.containsKey(number))
                    result.put(number, result.getValue(number) + 1)
                else
                    result.put(number, 1)
            }
        }
        return result.entries.sortedBy { it.value }.associateBy({it.key}, {it.value})
    }
}