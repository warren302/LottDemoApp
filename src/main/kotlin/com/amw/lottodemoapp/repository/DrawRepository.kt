package com.amw.lottodemoapp.repository

import com.amw.lottodemoapp.model.Draw
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DrawRepository : JpaRepository<Draw, Long> {
    fun findOneByDateOfDraw(date: LocalDate) : Draw?
    fun findByLotNumber(lotNumber : Int) : Draw

    @Query("SELECT d FROM Draw d Join LottoNumber n ON n.draw.id = d.id WHERE n.value = ?1")
    fun findAllBy1Number(number1 : Int) : List<Draw>

    @Query("SELECT d FROM Draw d Join LottoNumber n ON n.draw.id = d.id WHERE n.value IN (?1, ?2) GROUP BY d HAVING COUNT(DISTINCT n.value) = 2")
    fun findAllDrawBy2Numbers(number1 : Int, number2 : Int) : List<Draw>

    @Query("SELECT d FROM Draw d Join LottoNumber n ON n.draw.id = d.id WHERE n.value IN (?1, ?2, ?3) GROUP BY d HAVING COUNT(DISTINCT n.value) = 3")
    fun findAllDrawBy3Numbers(n1: Int, n2 : Int, n3 : Int) : List<Draw>

    @Query("SELECT d FROM Draw d Join LottoNumber n ON n.draw.id = d.id WHERE n.value IN (?1, ?2, ?3, ?4) GROUP BY d HAVING COUNT(DISTINCT n.value) = 4")
    fun findAllDrawBy4Numbers(n1: Int, n2 : Int, n3 : Int, n4 : Int) : List<Draw>

    @Query("SELECT d FROM Draw d Join LottoNumber n ON n.draw.id = d.id WHERE n.value IN (?1, ?2, ?3, ?4, ?5) GROUP BY d HAVING COUNT(DISTINCT n.value) = 5")
    fun findAllDrawBy5Numbers(n1: Int, n2 : Int, n3 : Int, n4 : Int, n5 : Int) : List<Draw>

    @Query("SELECT d FROM Draw d Join LottoNumber n ON n.draw.id = d.id WHERE n.value IN (?1, ?2, ?3, ?4, ?5, ?6) GROUP BY d HAVING COUNT(DISTINCT n.value) = 6")
    fun findAllDrawBy6Numbers(n1: Int, n2 : Int, n3 : Int, n4 : Int, n5 : Int, n6 : Int) : List<Draw>


}