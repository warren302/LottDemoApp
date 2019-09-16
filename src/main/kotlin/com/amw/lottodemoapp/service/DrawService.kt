package com.amw.lottodemoapp.service

import com.amw.lottodemoapp.csv.MyCSVReader
import com.amw.lottodemoapp.model.Draw
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.annotation.PostConstruct
import kotlin.collections.ArrayList


@Service
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

    fun findDrawWithDate(date : LocalDate) = drawList.firstOrNull { it.dateOfDraw == date }

    fun findByNumbers(args : Set<Int>) {
       
    }

    private fun generateTwoDim() {
        twoDim = Array(50) {Array(50) { mutableSetOf<Draw>()} }
    }

    private fun uploadTwoDim() {
        drawList.forEach{element -> getPairs(element).forEach{twoDim[it.first][it.second].add(element)}}
    }

    private fun printTwoDim() {
        for (row in twoDim) {
            for (cell in row) {
                print(cell.size.toString() + " ")
            }
            println()
        }
    }

    private fun getPairs(draw: Draw): List<Pair<Int, Int>> {

        var pairs = ArrayList<Pair<Int, Int>>()
        for (index in 0..draw.numbers.size - 2) {
            for (subindex in index + 1 until draw.numbers.size) {
                pairs.add(Pair(draw.numbers[index], draw.numbers[subindex]))
            }
        }
        return pairs
    }




}