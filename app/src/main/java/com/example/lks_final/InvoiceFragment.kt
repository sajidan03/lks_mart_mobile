package com.example.lks_final

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lks_final.InvoiceAdapter
import com.example.lks_final.Product
import com.example.lks_final.R

class InvoiceFragment : Fragment() {

    private lateinit var rvInvoice: RecyclerView
    private lateinit var tvTotalInvoice: TextView
    private lateinit var btnSave: Button
    private lateinit var btnShare: Button
    private lateinit var btnFinish: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_invoice, container, false)

        rvInvoice = view.findViewById(R.id.rvInvoice)
        tvTotalInvoice = view.findViewById(R.id.tvTotalInvoice)
        btnSave = view.findViewById(R.id.btnSave)
        btnShare = view.findViewById(R.id.btnShare)
        btnFinish = view.findViewById(R.id.btnFinish)

        rvInvoice.layoutManager = LinearLayoutManager(requireContext())

        val cartItems = arguments?.getParcelableArrayList<Product>("CART_ITEMS") ?: listOf()
        val totalAmount = arguments?.getInt("TOTAL_AMOUNT", 0) ?: 0

        val adapter = InvoiceAdapter(cartItems)
        rvInvoice.adapter = adapter

        tvTotalInvoice.text = "Total Belanja: Rp. $totalAmount"

        btnFinish.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

    companion object {
        fun newInstance(cartItems: ArrayList<Product>, totalAmount: Int) =
            InvoiceFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList("CART_ITEMS", cartItems)
                    putInt("TOTAL_AMOUNT", totalAmount)
                }
            }
    }
}
