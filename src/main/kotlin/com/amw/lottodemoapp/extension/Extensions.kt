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

val IntArray.Triples : List<Triple<Int, Int, Int>>
    get() {
        val triples = ArrayList<Triple<Int, Int, Int>>()
        for (index in 0..this.size - 3) {
            for (subindex1 in index + 1 .. this.size - 2)
                for (subindex2 in subindex1 + 1 .. this.size -1)
                    triples.add(Triple(this[index], this[subindex1], this[subindex2]))
        }
        return triples
    }