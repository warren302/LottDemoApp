package com.amw.lottodemoapp.csv

import com.amw.lottodemoapp.mapper.DrawMapper
import com.amw.lottodemoapp.model.Draw
import java.io.Reader
import java.nio.file.Files
import java.nio.file.Paths
import com.opencsv.CSVReaderBuilder
import com.opencsv.CSVParserBuilder




class MyCSVReader {

    @Throws(Exception::class)
    fun convertAllDrawToEntities(filename: String) : List<Draw> {
        val reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource(filenameVerification(filename)).toURI()))
        val mapper = DrawMapper()
        return readAll(reader).map { mapper.toEntity(it)}
    }

    var parser = CSVParserBuilder()
            .withSeparator(';')
            .withIgnoreQuotations(true)
            .build()

    @Throws(Exception::class)
    fun readAll(reader: Reader): List<Array<String>> {
        val csvReader = CSVReaderBuilder(reader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build()
        val list: List<Array<String>>
        list = csvReader.readAll()
        reader.close()
        csvReader.close()
        return list
    }

    private fun filenameVerification(filename : String) : String {
        return filename
    }
}

