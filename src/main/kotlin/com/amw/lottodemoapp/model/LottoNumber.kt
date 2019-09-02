package com.amw.lottodemoapp.model

import javax.persistence.*

@Entity
data class LottoNumber(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = -1,

        @Column
        var value: Int

)

