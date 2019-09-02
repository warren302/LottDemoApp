package com.amw.lottodemoapp.mapper

import com.amw.lottodemoapp.model.Draw
import com.amw.lottodemoapp.repository.LottoNumberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.time.ZoneId

class Mapper {

    fun toEntity(csvEntry : Array<String>, repo : LottoNumberRepository) : Draw {
        return Draw( -1,
                Integer.parseInt(csvEntry[0]),
                SimpleDateFormat("dd.MM.yyyy")
                        .parse(csvEntry[1])
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(),
                mutableSetOf(repo.findOneByValue(Integer.parseInt(csvEntry[2])),
                        repo.findOneByValue(Integer.parseInt(csvEntry[3])),
                        repo.findOneByValue(Integer.parseInt(csvEntry[4])),
                        repo.findOneByValue(Integer.parseInt(csvEntry[5])),
                        repo.findOneByValue(Integer.parseInt(csvEntry[6])),
                        repo.findOneByValue(Integer.parseInt(csvEntry[7])))
        )

    }
}