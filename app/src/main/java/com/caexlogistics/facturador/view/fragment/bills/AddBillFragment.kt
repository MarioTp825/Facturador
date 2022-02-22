package com.caexlogistics.facturador.view.fragment.bills

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import com.caexlogistics.facturador.adapters.BillProductAdapter
import com.caexlogistics.facturador.databinding.FragmentAddBillBinding
import com.caexlogistics.facturador.dto.Product
import com.caexlogistics.facturador.utils.*

class AddBillFragment : Fragment() {
    private var _binding: FragmentAddBillBinding? = null
    private val binding get() = _binding!!
    private lateinit var pHandler: ProductHandler
    private lateinit var bHandler: BillHandler
    private var products = mutableMapOf<String, Product>()
    private lateinit var adapter: BillProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBillBinding.inflate(layoutInflater)
        pHandler = ProductHandler.getInstance(requireContext())
        bHandler = BillHandler.getInstance(requireContext())


        binding.apply {
            spProducts.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, getProducts())
            rvProductBill.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = BillProductAdapter(mutableListOf())
            rvProductBill.adapter = adapter
            btnAddProductBill.setOnClickListener {
                if(!validateItem() || spProducts.isEmpty()) return@setOnClickListener
                adapter.addItem(
                    products[spProducts.selectedItem.toString()]!!,
                    etProductQuantity.text.toString().toShort()
                )
                tvAddBillTotal.text = "Q. ${adapter.total}"
                etProductQuantity.clear()
                spProducts.clear()
            }

            btnAddBill.setOnClickListener {
                if(!validateData()) return@setOnClickListener

                bHandler.add(
                    etBillNit.text.toString(),
                    etBillName.text.toString(),
                    "0A",
                    bHandler.nextBill.toString(),
                    adapter.getItems()
                )
                etBillName.clear()
                etBillNit.clear()
                adapter.clear()
                tvAddBillTotal.text = "Q. 0.00"
            }
        }

        return binding.root
    }

    private fun validateItem() = binding.etProductQuantity.rangeValue(0,5)

    private fun validateData() = binding.let {
        it.etBillName.isNotEmpty() && it.etBillNit.isNotEmpty() && it.etBillNit.isNit() && adapter.isNotEmpty()
    }

    private fun getProducts(): List<String> {
        val prods = pHandler.getAll
        val list = mutableListOf<String>()
        for (p in prods) {
            list.add(p.Name)
            products[p.Name] = p
        }
        return list
    }
}