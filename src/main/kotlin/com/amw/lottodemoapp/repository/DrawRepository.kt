package com.amw.lottodemoapp.repository

import com.amw.lottodemoapp.model.Draw
import com.amw.lottodemoapp.model.LottoNumber
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DrawRepository : JpaRepository<Draw, Long> {
    fun findOneByDateOfDraw(date: LocalDate) : Draw?
    fun findByNumbersIn(numbers : Array<LottoNumber>) : List<Draw>

    @Query("SELECT d FROM Draw d WHERE d.numbers IN ?1")
    fun findBy2NumbersIn(number1 : Array<LottoNumber>, number2  : Array<LottoNumber>) : List<Draw>

}