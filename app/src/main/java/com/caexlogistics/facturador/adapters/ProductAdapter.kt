package com.caexlogistics.facturador.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caexlogistics.facturador.R
import com.caexlogistics.facturador.databinding.ItemRvProductBinding
import com.caexlogistics.facturador.dto.Product
import com.caexlogistics.facturador.utils.ProductHandler

class ProductAdapter(private val products: MutableList<Product>, context: Context) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private val handler = ProductHandler.getInstance(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            ItemRvProductBinding.inflate(LayoutInflater.from(parent.context))
        ) {
        handler.delete(products[it])
        products.removeAt(it)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position], position)
    }

    override fun getItemCount() = products.size

    class ViewHolder(private val view: ItemRvProductBinding, private val onDelete: (Int) -> Unit) : RecyclerView.ViewHolder(view.root) {
        private var index = 0

        init {
            view.cvItem.setOnClickListener { onDelete(index) }
        }

        fun bind(product: Product, index: Int) {
            this.index = index
            view.apply {
                tvCategory.text = root.context.resources.getStringArray(R.array.category)[product.Category]
                tvProductName.text = product.Name
            }
        }
    }
}