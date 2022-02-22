package com.caexlogistics.facturador.dto

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
class BillHeader {
    @Id var id: Long = 0
    lateinit var nit: String
    lateinit var name: String
    lateinit var series: String
    lateinit var billNumber: String
    lateinit var body: ToOne<BillHolder>
}