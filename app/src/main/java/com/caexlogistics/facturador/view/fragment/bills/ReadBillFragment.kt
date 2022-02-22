package com.caexlogistics.facturador.view.fragment.bills

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caexlogistics.facturador.adapters.BillAdapter
import com.caexlogistics.facturador.adapters.BillProductAdapter
import com.caexlogistics.facturador.databinding.DialogBillBinding
import com.caexlogistics.facturador.databinding.FragmentReadBillBinding
import com.caexlogistics.facturador.utils.BillHandler

class ReadBillFragment : Fragment() {
    private var _binding: FragmentReadBillBinding? = null
    private lateinit var dialogBinding: DialogBillBinding
    private val binding get() = _binding!!
    private lateinit var dialog: AlertDialog

    private lateinit var bHandler: BillHandler
    private val adapter = BillAdapter(mutableListOf()) {
        dialogBinding.apply {
            tvBillNameDialog.text = it.name
            tvBillNitDialog.text = it.nit
            tvBillNumberDialog.text = it.billNumber
            tvBillSeriesDialog.text = it.series
            rvBillProductsDialog.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val adapter = BillProductAdapter(it.body.target.list)
            rvBillProductsDialog.adapter = adapter
            tvBillTotalDialog.text = "Q.${adapter.total.toString()}"
        }
        dialog.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialogBinding = DialogBillBinding.inflate(LayoutInflater.from(requireContext()))
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogBinding.root)
        builder.setCancelable(true)
        dialog = builder.create()
        val window = dialog.window
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        _binding = FragmentReadBillBinding.inflate(layoutInflater)
        bHandler = BillHandler.getInstance(requireContext())
        adapter.billList = bHandler.getAll
        binding.rvBills.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvBills.adapter = adapter
        return binding.root
    }
}