package com.example.wishlistapp

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val items: List<WishlistItem>) :
    RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.text1)
        val priceTextView: TextView = itemView.findViewById(R.id.text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Using a simple built-in layout for each item
        val wishlistItemView = inflater.inflate(R.layout.simple_list_item_2, parent, false)
        return ViewHolder(wishlistItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name
        // Formatting the price to show as a currency
        holder.priceTextView.text = String.format("$%.2f", item.price)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}