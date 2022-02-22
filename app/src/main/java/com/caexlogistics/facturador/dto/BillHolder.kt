package com.caexlogistics.facturador.dto

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class BillHolder {
    @Id
    var id: Long = 0
    var list = mutableListOf<BillBody>()
}