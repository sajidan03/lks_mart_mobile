package com.example.gabut

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.lks_final.InvoiceFragment
import com.example.lks_final.Product
import com.example.lks_final.R

class TrxFragment : Fragment() {

    private lateinit var lvProducts: ListView
    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnCheckout: Button
    private lateinit var tvTotal: TextView

    private var cartItems = mutableListOf<Product>()
    private var totalAmount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_trx, container, false)

        lvProducts = view.findViewById(R.id.lvProducts)
        etSearch = view.findViewById(R.id.etSearch)
        btnSearch = view.findViewById(R.id.btnSearch)
        btnCheckout = view.findViewById(R.id.btnCheckout)
        tvTotal = view.findViewById(R.id.tvTotal)

        fetchProducts()

        btnCheckout.setOnClickListener {
            if (cartItems.isEmpty()) {
                Toast.makeText(requireContext(), "Pilih terlebih dahulu item!", Toast.LENGTH_SHORT).show()
            } else {
                // Buat instance dari InvoiceFragment dengan data yang dikirim
                val invoiceFragment = InvoiceFragment.newInstance(ArrayList(cartItems), totalAmount)

                // Ganti fragment saat tombol checkout diklik
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, invoiceFragment) // Ganti dengan ID container di XML
                    .addToBackStack(null) // Agar bisa kembali ke TrxFragment
                    .commit()
            }
        }


        return view
    }

    private fun fetchProducts() {
        val productList = listOf(
            Product("Fanta Orange", 7000, R.drawable.fanta_orange),
            Product("Fanta Strawberry", 7000, R.drawable.fanta_orange),
            Product("Oreo", 7000, R.drawable.fanta_orange),
            Product("Coca-Cola", 7000, R.drawable.fanta_orange)
        )

        val adapter = ProductAdapter(requireContext(), R.layout.item_product, productList) { product, action ->
            when (action) {
                "ADD" -> {
                    cartItems.add(product)
                    totalAmount += product.price
                }
                "REMOVE" -> {
                    cartItems.remove(product)
                    totalAmount -= product.price
                }
            }
            tvTotal.text = "Total Belanja: Rp. $totalAmount"
        }

        lvProducts.adapter = adapter
    }
}
