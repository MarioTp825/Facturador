package com.caexlogistics.facturador.dto

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
class BillBody {
    @Id
    var id: Long = 0
    var quantity: Short = 0
    lateinit var product: ToOne<Product>
}