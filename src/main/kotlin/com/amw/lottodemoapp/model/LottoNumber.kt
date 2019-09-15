package com.amw.lottodemoapp.model

import javax.persistence.*

@Entity
data class LottoNumber(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = -1,

        var value: Int,

        @ManyToOne
        @JoinColumn(name ="draw_id")
        var draw: Draw

)

