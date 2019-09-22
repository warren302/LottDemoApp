package com.amw.lottodemoapp.service

import com.amw.lottodemoapp.csv.MyCSVReader
import com.amw.lottodemoapp.model.Draw
import com.amw.lottodemoapp.model.SearchResult
import org.apache.commons.lang3.SystemUtils
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.logging.Logger
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
        prepareTwoDim()
        return "Done"
    }

    fun findDrawWithDate(date : LocalDate) = drawList.firstOrNull { it.dateOfDraw == date }

    fun findByNumbers(args : Set<Int>) : List<Draw> {
       return drawList.filter { args.all { arg -> it.numbers.contains(arg) } }
    }

    fun findByQuery(args : Array<String>) : List<SearchResult> {

        return getRequestedNumberOfResults(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[3]))
    }

    private fun prepareTwoDim() {
        println("---------- preparation of numbers two dimensional matrix started-------------------------------------------------------------------------")
        val start = System.currentTimeMillis()
        generateTwoDim()
        uploadTwoDim()
        printTwoDim()
        val elapsedTime = System.currentTimeMillis() - start
        println("---------- preparation of numbers two dimensional matrix finished in ${System.currentTimeMillis() - start} ms ---------------------------")
    }

    private fun generateTwoDim() {
        twoDim = Array(50) {Array(50) { mutableSetOf<Draw>()} }
    }

    private fun uploadTwoDim() {
        drawList.forEach{element -> getPairs(element).forEach{
            twoDim[it.first][it.second].add(element)
            twoDim[it.second][it.first].add(element)
        }}
    }

    private fun printTwoDim() {
        for (row in twoDim) {
            for (cell in row) {
                print(cell.size.toString() + " ")
            }
            println()
        }
    }

    private fun getRequestedNumberOfResults(edge : String, counter : Int, associate : Int) : List<SearchResult> {
        var listOfResults = twoDim[associate].mapIndexed { index, mutableSet -> SearchResult(Pair(associate,index), mutableSet)}.sortedBy { it.size }
        return when (edge.toUpperCase()) {
            "TOP"       -> listOfResults.takeLast(counter)
            "BOTTOM"    -> listOfResults.take(counter)
            else        -> throw IllegalArgumentException("Parameter $edge not recognised!")
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