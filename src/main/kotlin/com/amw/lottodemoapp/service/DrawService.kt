package com.amw.lottodemoapp.service

import com.amw.lottodemoapp.csv.MyCSVReader
import com.amw.lottodemoapp.model.Draw
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import kotlin.collections.ArrayList


@Component
class DrawService {

    val CSV_FILENAME = "dl.csv"

    lateinit var drawList : List<Draw>
    lateinit var twoDim : Array<Array<MutableSet<Draw>>>


    @PostConstruct
    fun process() : String {
        val csvReader = MyCSVReader()
        drawList = csvReader.convertAllDrawToEntities(CSV_FILENAME)
        generateTwoDim()
        uploadTwoDim()
        printTwoDim()
        return "Done"
    }

    private fun generateTwoDim() {
        twoDim = Array(50, {Array(50, { mutableSetOf<Draw>()})})
    }

    private fun uploadTwoDim() {
        drawList.forEach{element -> getPairs(element).forEach{twoDim[it.first][it.second].add(element)}}
    }

    private fun printTwoDim() {
        for (index in 0..twoDim.size - 1) {
            val line = twoDim[index]
            for (subindex in 0..line.size - 1) {
                val mySet = line[subindex]
                print(mySet.size.toString() + " ")
            }
            println()
        }
    }


    private fun getPairs(draw: Draw): List<Pair<Int, Int>> {

        var pairs = ArrayList<Pair<Int, Int>>()
        for (index in 0..draw.numbers.size - 2) {
            for (subindex in index + 1..draw.numbers.size - 1) {
                pairs.add(Pair(draw.numbers[index], draw.numbers[subindex]))
            }
        }
        return pairs
    }




}