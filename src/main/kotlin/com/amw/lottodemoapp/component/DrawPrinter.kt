package com.amw.lottodemoapp.component

import com.amw.lottodemoapp.csv.MyCSVReader
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DrawPrinter : CommandLineRunner {


    override fun run(vararg args: String?) {
        var filename : String = "dl.csv"
        val reader =  MyCSVReader()
        reader.readAllLines(filename).forEach{println(it)}
    }


}