package com.amw.lottodemoapp.extension

val IntArray.Pairs : List<Pair<Int, Int>>
    get() {
        val pairs = ArrayList<Pair<Int, Int>>()
        for (index in 0..this.size - 2) {
            for (subindex in index + 1 until this.size) {
                pairs.add(Pair(this[index], this[subindex]))
            }
        }
        return pairs
    }
