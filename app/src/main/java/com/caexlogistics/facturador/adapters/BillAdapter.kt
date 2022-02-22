package com.caexlogistics.facturador.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caexlogistics.facturador.databinding.ItemRvResumeBillBinding
import com.caexlogistics.facturador.dto.BillHeader

class BillAdapter(private var bills: MutableList<BillHeader>, private val onClick: (BillHeader) -> Unit): RecyclerView.Adapter<BillAdapter.ViewHolder>() {
    var billList get() = bills
        set(value) {
            bills = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ItemRvResumeBillBinding.inflate(LayoutInflater.from(parent.context))) {
        onClick(bills[it])
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bills[position] ,position)
    }

    override fun getItemCount() = bills.size

    class ViewHolder(private val view: ItemRvResumeBillBinding, onClick: (Int) -> Unit ): RecyclerView.ViewHolder(view.root) {
        private var index = 0

        init {
            view.cvItem.setOnClickListener { onClick(index) }
        }

        fun bind(bill: BillHeader, index: Int) {
            this.index = index
            view.apply {
                tvBillName.text = bill.name
                tvBillNumber.text = bill.billNumber
            }
        }
    }
}