package com.amw.lottodemoapp.model

data class SearchResult(val pair: Pair<Int, Int>, val draws : MutableSet<Draw>) {
}