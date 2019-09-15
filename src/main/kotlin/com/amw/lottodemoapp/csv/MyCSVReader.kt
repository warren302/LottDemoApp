package com.amw.lottodemoapp.csv

import com.amw.lottodemoapp.mapper.DrawMapper
import com.amw.lottodemoapp.mapper.LottoNumberMapper
import com.amw.lottodemoapp.model.Draw
import com.amw.lottodemoapp.model.LottoNumber
import com.amw.lottodemoapp.repository.DrawRepository
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

    @Throws(Exception::class)
    fun convertAllNumberToEntities(filename: String, repo : DrawRepository) : List<LottoNumber> {
        val reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource(filenameVerification(filename)).toURI()))
        val mapper = LottoNumberMapper()
        return readAll(reader).flatMap { mapper.toEntity(it, repo)}
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

