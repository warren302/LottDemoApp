package com.amw.lottodemoapp.model

import java.time.LocalDate


data class Draw(

        val lotNumber: Int,

        val dateOfDraw: LocalDate,

        val numbers: IntArray
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other !is Draw) return false

                if (lotNumber != other.lotNumber) return false
                if (dateOfDraw != other.dateOfDraw) return false
                if (!numbers.contentEquals(other.numbers)) return false

                return true
        }

        override fun hashCode(): Int {
                var result = lotNumber
                result = 31 * result + dateOfDraw.hashCode()
                result = 31 * result + numbers.contentHashCode()
                return result
        }
}