package com.amw.lottodemoapp.csv

import java.util.ArrayList
import com.opencsv.CSVReader
import java.io.Reader
import java.nio.file.Files
import java.nio.file.Paths


class MyCSVReader {

    @Throws(Exception::class)
    fun readAllLines(filename : String) : List<String> {
        val reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource(filenameVerification(filename)).toURI()))
        return readAll(reader).map { line -> line.joinToString(prefix = "[", postfix = "]") }
    }

    @Throws(Exception::class)
    fun readAll(reader: Reader): List<Array<String>> {
        val csvReader = CSVReader(reader)
        var list: List<Array<String>> = ArrayList()
        list = csvReader.readAll()
        reader.close()
        csvReader.close()
        return list
    }

    private fun filenameVerification(filename : String) : String {
        return filename
    }



}

