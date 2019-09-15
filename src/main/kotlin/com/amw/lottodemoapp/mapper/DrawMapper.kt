package com.amw.lottodemoapp.mapper

import com.amw.lottodemoapp.model.Draw
import java.text.SimpleDateFormat
import java.time.ZoneId

class DrawMapper {

    fun toEntity(csvEntry: Array<String>): Draw {
        return Draw(
                Integer.parseInt(csvEntry[0]),
                SimpleDateFormat("dd.MM.yyyy")
                        .parse(csvEntry[1])
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(),
                intArrayOf(Integer.parseInt(csvEntry[2]), Integer.parseInt(csvEntry[3]), Integer.parseInt(csvEntry[4]),
                        Integer.parseInt(csvEntry[5]), Integer.parseInt(csvEntry[6]), Integer.parseInt(csvEntry[7]))
        )
    }
}