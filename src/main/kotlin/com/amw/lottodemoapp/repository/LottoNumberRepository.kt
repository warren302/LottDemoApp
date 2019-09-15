package com.amw.lottodemoapp.repository

import com.amw.lottodemoapp.model.Draw
import com.amw.lottodemoapp.model.LottoNumber
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LottoNumberRepository : JpaRepository<LottoNumber, Long>{
    fun findAllByDraw(draw : Draw) : List<LottoNumber>

}