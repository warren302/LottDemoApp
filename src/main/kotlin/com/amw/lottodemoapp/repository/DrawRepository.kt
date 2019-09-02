package com.amw.lottodemoapp.repository

import com.amw.lottodemoapp.model.Draw
import com.amw.lottodemoapp.model.LottoNumber
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DrawRepository : JpaRepository<Draw, Long> {
    fun findOneByDateOfDraw(date: LocalDate) : Draw?
    fun findByNumbersIn(numbers : Set<LottoNumber>) : List<Draw>

}