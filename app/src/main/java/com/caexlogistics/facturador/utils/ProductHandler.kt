package com.caexlogistics.facturador.utils

import android.content.Context
import com.caexlogistics.facturador.dto.Product

class ProductHandler(context: Context) {
    private val box = getStore(context).boxFor(Product::class.java)
    val getAll get() = box.all

    fun add(name: String, category: Int, price: Float) {
        box.put(
            Product(
                0,
                name,
                category,
                price
            )
        )
    }

    fun get(id: Long):Product? = box.get(id)

    fun delete(id: Long) {
        box.remove(id)
    }

    fun delete(product: Product) {
        box.remove(product)
    }

    companion object {
        private var handler: ProductHandler? = null

        fun getInstance(context: Context): ProductHandler {
            return if(handler == null){
                handler = ProductHandler(context)
                handler!!
            } else handler!!
        }
    }
}