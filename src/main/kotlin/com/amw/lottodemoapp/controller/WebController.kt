package com.amw.lottodemoapp.controller

import com.amw.lottodemoapp.csv.MyCSVReader
import com.amw.lottodemoapp.model.Draw
import com.amw.lottodemoapp.repository.DrawRepository
import com.amw.lottodemoapp.repository.LottoNumberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import javax.annotation.PostConstruct

@RestController
@RequestMapping("/API")
class WebController {

    val CSV_FILENAME = "dl.csv"

    @Autowired
    lateinit var repository : DrawRepository

    @Autowired
    lateinit var lottoNumberRepo : LottoNumberRepository

    @PostConstruct
    fun process() : String {
        val csvReader = MyCSVReader()
        repository.saveAll(csvReader.convertAllDrawToEntities(CSV_FILENAME))
        lottoNumberRepo.saveAll(csvReader.convertAllNumberToEntities(CSV_FILENAME, repository))
        return "Done"
    }

    @RequestMapping("/date/{date}")
    fun findByDrawDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date : LocalDate)
            = repository.findOneByDateOfDraw(date)

    @RequestMapping("/numbers/{numbers}")
    fun findByNumbers(@PathVariable("numbers") numbers : Array<String>) : List<Draw> {
        val args = numbers.map{Integer.parseInt(it)}.toSet()
        require(args.size == numbers.size) { "parameters values are duplicated!" }
        return when (args.size) {
            1 -> repository.findAllBy1Number(args.single())
            2 -> repository.findAllDrawBy2Numbers(args.first(), args.last())
            3 -> repository.findAllDrawBy3Numbers(args.first(), args.elementAt(1), args.last())
            4 -> repository.findAllDrawBy4Numbers(args.first(), args.elementAt(1), args.elementAt(2), args.last())
            5 -> repository.findAllDrawBy5Numbers(args.first(), args.elementAt(1), args.elementAt(2), args.elementAt(3), args.last())
            6 -> repository.findAllDrawBy6Numbers(args.first(), args.elementAt(1), args.elementAt(2), args.elementAt(3), args.elementAt(4), args.last())
            else -> {
                throw IllegalArgumentException("Number of arguments = ${args.size} is not handled!")
            }
        }
    }



}