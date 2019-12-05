package com.amw.lottodemoapp.service

import com.amw.lottodemoapp.csv.MyCSVReader
import com.amw.lottodemoapp.extension.Pairs
import com.amw.lottodemoapp.extension.Triples
import com.amw.lottodemoapp.model.Draw
import com.amw.lottodemoapp.model.Element
import com.amw.lottodemoapp.model.SearchResult
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.annotation.PostConstruct


@Service
class DrawService {

    val CSV_FILENAME = "dl.csv"


    lateinit var drawList : List<Draw>
    lateinit var twoDim : Array<Array<MutableSet<Draw>>>
    lateinit var threeDim : Array<Array<Array<MutableSet<Draw>>>>

    @PostConstruct
    fun process() : String {
        val csvReader = MyCSVReader()
        drawList = csvReader.convertAllDrawToEntities(CSV_FILENAME)
        prepareTwoDim()
        prepareThreeDim()
        return "Done"
    }

    fun findDrawWithDate(date : LocalDate) = drawList.firstOrNull { it.dateOfDraw == date }

    fun findByNumbers(args : Set<Int>) = drawList.filter { args.all { arg -> it.numbers.contains(arg) } }

    fun findByQuery(args : Array<String>) = getRequestedNumberOfResults(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[3]))

    private fun prepareTwoDim() {
        println("---------- preparation of numbers two dimensional matrix started-------------------------------------------------------------------------")
        val start = System.currentTimeMillis()
        generateTwoDim()
        uploadTwoDim()
        printTwoDim()
        println("---------- preparation of numbers two dimensional matrix finished in ${System.currentTimeMillis() - start} ms ---------------------------")
    }

    private fun generateTwoDim() {
        twoDim = Array(49) {Array(49) { mutableSetOf<Draw>()} }
    }

    private fun uploadTwoDim() {
        drawList.forEach{element -> element.numbers.Pairs.forEach{
            twoDim[it.first - 1][it.second - 1].add(element)
            twoDim[it.second -1 ][it.first - 1].add(element)
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

    private fun prepareThreeDim() {
        println("---------- preparation of numbers three dimensional matrix started-------------------------------------------------------------------------")
        val start = System.currentTimeMillis()
        generateThreeDim()
        uploadThreeDim()
        println("---------- preparation of numbers three dimensional matrix finished in ${System.currentTimeMillis() - start} ms ---------------------------")
    }

    private fun generateThreeDim() {
        threeDim = Array(49) {Array(49) {Array(49) { mutableSetOf<Draw>()} } }
    }

    private fun uploadThreeDim() {
        drawList.forEach{element -> element.numbers.Triples.forEach{
            threeDim[it.first - 1][it.second - 1][it.third - 1].add(element)
            threeDim[it.first - 1][it.third - 1][it.second - 1].add(element)
            threeDim[it.second - 1][it.first - 1][it.third - 1].add(element)
            threeDim[it.second - 1][it.third - 1][it.first - 1].add(element)
            threeDim[it.third - 1][it.first - 1][it.second - 1].add(element)
            threeDim[it.third - 1][it.second - 1][it.first - 1].add(element)
        }}
    }

    private fun getRequestedNumberOfResults(edge : String, counter : Int, associate : Int) : List<SearchResult> {
        val listOfResults = twoDim[associate - 1].mapIndexed { index, set -> SearchResult(Element.fromPair(Pair(associate, index + 1)), set)}.sortedBy { it.size }
        return when (edge.toUpperCase()) {
            "TOP"       -> listOfResults.takeLast(counter)
            "BOTTOM"    -> listOfResults.take(counter)
            else        -> throw IllegalArgumentException("Parameter $edge not recognised!")
        }
    }

}