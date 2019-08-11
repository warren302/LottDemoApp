package com.amw.lottodemoapp.model

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
data class Draw (

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    val id : Long = 0,

    @get: NotNull
    val lotNumber : Int,

    @get: NotNull
    val dateOfDraw : LocalDate,

    @get: NotNull
    val numbers : UIntArray
)