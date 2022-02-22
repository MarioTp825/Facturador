package com.caexlogistics.facturador.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.caexlogistics.facturador.adapters.ProductAdapter
import com.caexlogistics.facturador.databinding.FragmentAddProductBinding
import com.caexlogistics.facturador.dto.Product
import com.caexlogistics.facturador.utils.*

class AddProduct : Fragment() {
    private var _binding:FragmentAddProductBinding? = null
    private lateinit var store:ProductHandler
    private lateinit var adapter: ProductAdapter
    private val products = mutableListOf<Product>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProductBinding.inflate(layoutInflater)
        store = ProductHandler.getInstance(requireContext())
        products.addAll(store.getAll)
        adapter = ProductAdapter(products, requireContext())

        binding.apply {
            rvProducts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvProducts.adapter = adapter

            btnAddProduct.setOnClickListener { addProduct() }
        }

        return binding.root
    }

    private fun addProduct() {
        if(!validData()) return
        binding.apply {
            store.add(
                etProductName.text.toString(),
                spProductCategory.selectedItemPosition,
                etProductPrice.text.toString().toFloat()
            )
            etProductName.clear()
            etProductPrice.clear()
            spProductCategory.clear()
        }
        products.clear()
        products.addAll(store.getAll)
        adapter.notifyDataSetChanged()
    }

    private fun validData() = binding.let {
        it.etProductName.isNotEmpty()
                && it.etProductPrice.isNotEmpty()
                && it.etProductPrice.floatRangeValue(1f,50f)
    }

}