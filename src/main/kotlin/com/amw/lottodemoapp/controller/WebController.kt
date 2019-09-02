package com.amw.lottodemoapp.controller

import com.amw.lottodemoapp.csv.MyCSVReader
import com.amw.lottodemoapp.model.Draw
import com.amw.lottodemoapp.model.LottoNumber
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
        val numbers = Array(49) {i -> LottoNumber(-1, i + 1) }
        numbers.forEach {  lottoNumberRepo.save(it) }
        val csvReader = MyCSVReader()
        repository.saveAll(csvReader.convertAllToEntities(CSV_FILENAME, lottoNumberRepo))
        return "Done"
    }

    @RequestMapping("/date/{date}")
    fun findByDrawDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date : LocalDate)
            = repository.findOneByDateOfDraw(date)

    @RequestMapping("/numbers/{numbers}")
    fun findByNumbers(@PathVariable("numbers") numbers : Array<String>) : List<Draw> {
        val ar = numbers.map{lottoNumberRepo.findOneByValue(Integer.parseInt(it))}.toSet()
        return repository.findByNumbersIn(ar)
    }



}