package com.amw.lottodemoapp.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.persistence.FetchType


@Entity
data class Draw(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = -1,

        @get: NotNull
        var lotNumber: Int,

        @get: NotNull
        var dateOfDraw: LocalDate,

        @ManyToMany(cascade = [CascadeType.REMOVE], fetch = FetchType.LAZY)
        var numbers: MutableSet<LottoNumber>? = null
)