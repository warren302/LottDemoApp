package com.amw.lottodemoapp.mapper

import com.amw.lottodemoapp.model.LottoNumber
import com.amw.lottodemoapp.repository.DrawRepository

class LottoNumberMapper {

    fun toEntity(csvEntry: Array<String>, repo: DrawRepository): Set<LottoNumber> {
        val draw = repo.findByLotNumber(Integer.parseInt(csvEntry[0]))
        return setOf<LottoNumber>(
                LottoNumber(-1, Integer.parseInt(csvEntry[2]), draw),
                LottoNumber(-1, Integer.parseInt(csvEntry[3]), draw),
                LottoNumber(-1, Integer.parseInt(csvEntry[4]), draw),
                LottoNumber(-1, Integer.parseInt(csvEntry[5]), draw),
                LottoNumber(-1, Integer.parseInt(csvEntry[6]), draw),
                LottoNumber(-1, Integer.parseInt(csvEntry[7]), draw))
    }

}