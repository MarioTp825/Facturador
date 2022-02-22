package com.caexlogistics.facturador.utils

import android.content.Context
import com.caexlogistics.facturador.dto.*

class BillHandler(context: Context) {
    private val box = getStore(context).boxFor(BillHeader::class.java)
    private val boxH = getStore(context).boxFor(BillHolder::class.java)
    private val boxB = getStore(context).boxFor(BillBody::class.java)
    private var new: BillHeader? = null
    val getAll get() = box.all

    val nextBill get() = box.count() + 1

    fun add(
        _nit: String,
        _name: String,
        _series: String,
        _billNumber: String,
        items: MutableList<BillBody>
    ) {
        new = BillHeader()
        new!!.apply {
            val hold = BillHolder()
            for (b in items)
                boxB.put(b)
            hold.list = items
            boxH.put(hold)
            nit =  _nit
            name = _name
            series = _series
            billNumber = _billNumber
            body.target = hold
        }
        box.put(new!!)
    }

    fun addItem(product: Product, _quantity: Short) {
        if(new == null) return
        val item = BillBody()
        item.apply {
            quantity = _quantity
            this.product.target = product
        }
        new!!.body.target.list.add(item)
        box.put(new!!)
    }

    fun addAll(items: MutableList<BillBody>) {
        if(new == null) return
        new!!.body.target.list = items
        box.put(new!!)
    }

    fun get(id: Long): BillHeader = box.get(id)

    fun delete(id: Long) {
        box.remove(id)
    }

    fun delete(bill: BillHeader) {
        box.remove(bill)
    }

    companion object {
        private var handler: BillHandler? = null

        fun getInstance(context: Context): BillHandler {
            return if(handler == null){
                handler = BillHandler(context)
                handler!!
            } else handler!!
        }
    }
}