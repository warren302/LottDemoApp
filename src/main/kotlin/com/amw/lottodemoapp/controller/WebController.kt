package com.amw.lottodemoapp.controller

import com.amw.lottodemoapp.model.Draw
import com.amw.lottodemoapp.service.DrawService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/API")
class WebController {

    @Autowired
    lateinit var service: DrawService


    @RequestMapping("/date/{date}", method = [RequestMethod.GET])
    fun findByDrawDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate)
            = service.findDrawWithDate(date)

    @RequestMapping("/numbers/{numbers}", method = [RequestMethod.GET])
    fun findByNumbers(@PathVariable("numbers") numbers: Array<String>): List<Draw> {
        val args = numbers.map { Integer.parseInt(it) }.toSet()
        return service.findByNumbers(args)
    }

    @RequestMapping("/query/{params}", method = [RequestMethod.GET])
    fun findByQuery(@PathVariable("params") params : Array<String>) : List<Draw> {
        return service.findByQuery(params)
    }
}




