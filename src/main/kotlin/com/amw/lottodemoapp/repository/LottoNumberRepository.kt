package com.amw.lottodemoapp.repository

import com.amw.lottodemoapp.model.LottoNumber
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LottoNumberRepository : JpaRepository<LottoNumber, Long>{
    fun findOneByValue(value : Int) : LottoNumber
}