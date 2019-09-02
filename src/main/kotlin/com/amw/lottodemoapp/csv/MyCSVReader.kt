package com.amw.lottodemoapp.csv

import com.amw.lottodemoapp.mapper.Mapper
import com.amw.lottodemoapp.model.Draw
import com.amw.lottodemoapp.repository.LottoNumberRepository
import java.io.Reader
import java.nio.file.Files
import java.nio.file.Paths
import com.opencsv.CSVReaderBuilder
import com.opencsv.CSVParserBuilder




class MyCSVReader {

    @Throws(Exception::class)
    fun convertAllToEntities(filename: String, repo : LottoNumberRepository) : List<Draw> {
        val reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource(filenameVerification(filename)).toURI()))
        val mapper = Mapper()
        return readAll(reader).map { mapper.toEntity(it, repo)}
    }

    var parser = CSVParserBuilder()
            .withSeparator(';')
            .withIgnoreQuotations(true)
            .build()

    @Throws(Exception::class)
    fun readAllLines(filename : String) : List<String> {
        val reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource(filenameVerification(filename)).toURI()))
        return readAll(reader).map { it.joinToString(prefix = "[", postfix = "]", separator = "•") }
    }

    @Throws(Exception::class)
    fun readAll(reader: Reader): List<Array<String>> {
        var csvReader = CSVReaderBuilder(reader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build()
        var list: List<Array<String>>
        list = csvReader.readAll()
        reader.close()
        csvReader.close()
        return list
    }

    private fun filenameVerification(filename : String) : String {
        return filename
    }
}

