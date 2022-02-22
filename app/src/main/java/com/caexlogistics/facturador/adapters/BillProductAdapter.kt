package com.caexlogistics.facturador.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caexlogistics.facturador.databinding.ItemRvBillBinding
import com.caexlogistics.facturador.dto.BillBody
import com.caexlogistics.facturador.dto.Product

class BillProductAdapter(private val items: MutableList<BillBody>): RecyclerView.Adapter<BillProductAdapter.ViewHolder>() {

    val total get() = run {
        var acumulator = 0f
        for (item in items) {
            acumulator += item.quantity * item.product.target.price
        }
        acumulator
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemRvBillBinding.inflate(LayoutInflater.from(parent.context))
    ) {
        items.removeAt(it)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount() = items.size

    fun addItem(product: Product, quantity: Short) {
        val item = BillBody()
        item.quantity = quantity
        item.product.target = product
        items.add(item)
        notifyDataSetChanged()
    }

    fun getItems() = items
    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun isNotEmpty() = items.size != 0

    class ViewHolder(private val view: ItemRvBillBinding, onDelete:(Int) -> Unit): RecyclerView.ViewHolder(view.root) {
        private var index = 0

        init {
            view.cvProductBillItem.setOnLongClickListener {
                onDelete(index)
                true
            }
        }

        fun bind(item: BillBody, index: Int) {
            this.index = index
            view.apply {
                val quantity = item.quantity
                tvBillBodyName.text = item.product.target.Name
                tvBillBodyNumber.text = quantity.toString()
                tvBillBodyPrice.text = (quantity * item.product.target.price).toString()
            }
        }
    }
}