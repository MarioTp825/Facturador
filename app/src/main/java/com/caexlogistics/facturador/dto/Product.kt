package com.caexlogistics.facturador.dto

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Product(
    @Id var id: Long = 0,
    var Name: String,
    var Category: Int,
    var price:Float
)
