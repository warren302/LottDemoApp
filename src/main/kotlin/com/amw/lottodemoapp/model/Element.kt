package com.amw.lottodemoapp.model

data class Element private constructor(var first : Int, var second : Int, var third : Int) {

    companion object DataProvider {
        fun fromPair(pair : Pair<Int, Int>) : Element {
            return Element(pair.first, pair.second, 0)
        }

        fun fromTriple(triple : Triple<Int, Int, Int>) : Element {
            return Element(triple.first, triple.second, triple.third)
        }
    }
}
