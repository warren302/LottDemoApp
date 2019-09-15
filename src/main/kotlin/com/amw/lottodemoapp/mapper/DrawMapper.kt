package com.amw.lottodemoapp.mapper

import com.amw.lottodemoapp.model.Draw
import java.text.SimpleDateFormat
import java.time.ZoneId

class DrawMapper {

    fun toEntity(csvEntry : Array<String>) : Draw {
        return Draw( -1,
                Integer.parseInt(csvEntry[0]),
                SimpleDateFormat("dd.MM.yyyy")
                        .parse(csvEntry[1])
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
        )
    }
}