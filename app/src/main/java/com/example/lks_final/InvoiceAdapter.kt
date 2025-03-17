package com.example.lks_final

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lks_final.Product
import com.example.lks_final.R

class InvoiceAdapter(private val cartItems: List<Product>) :
    RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_invoice, parent, false)
        return InvoiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: InvoiceViewHolder, position: Int) {
        val item = cartItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = cartItems.size

    inner class InvoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvItemName: TextView = itemView.findViewById(R.id.tvItemName)
        private val tvItemPrice: TextView = itemView.findViewById(R.id.tvItemPrice)

        fun bind(product: Product) {
            tvItemName.text = product.name
            tvItemPrice.text = "Rp. ${product.price}"
        }
    }
}
