package com.amw.lottodemoapp.model

import com.amw.lottodemoapp.extension.Pairs
import kotlin.collections.LinkedHashMap

data class SearchResult(val pair: Pair<Int, Int>, val draws : Set<Draw>) {
    val size = draws.size
    val associatedNumbers = createAssociatedNumbers()
    val associatedPairs = createAssociatedPairs()

    private fun createAssociatedNumbers(): Map<Int, Int> {
        val result = LinkedHashMap<Int, Int>()
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
        return result.entries.sortedByDescending { it.value }.associateBy({ it.key }, { it.value })
    }

    private fun createAssociatedPairs(): Map<Pair<Int, Int>, Int> {
        val result = LinkedHashMap<Pair<Int, Int>, Int>()
        draws.forEach { element ->
            element.numbers.Pairs
                    .filter { it.first !in setOf(pair.first, pair.second) && it.second !in setOf(pair.first, pair.second) }
                    .forEach { double ->
                        if (result.containsKey(double))
                            result.put(double, result.getValue(double) +1)
                        else
                            result.put(double, 1)
                    }
        }
        return result.entries.sortedByDescending { it.value }.associateBy({ it.key }, { it.value })
    }

}