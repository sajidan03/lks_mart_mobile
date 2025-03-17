package com.example.gabut

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import com.example.lks_final.Product
import com.example.lks_final.R

class ProductAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val productList: List<Product>,
    private val onProductSelected: (Product, String) -> Unit
) : ArrayAdapter<Product>(context, layoutResource, productList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(layoutResource, parent, false)

        val imgProduct: ImageView = view.findViewById(R.id.imgProduct)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val btnAdd: Button = view.findViewById(R.id.btnAdd)
        val btnRemove: Button = view.findViewById(R.id.btnRemove)

        val product = productList[position]

        imgProduct.setImageResource(product.image)
        tvName.text = product.name
        tvPrice.text = "Rp. ${product.price}"

        btnAdd.setOnClickListener { onProductSelected(product, "ADD") }
        btnRemove.setOnClickListener { onProductSelected(product, "REMOVE") }

        return view
    }
}
