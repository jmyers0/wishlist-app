package com.example.wishlistapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val wishlistItems = mutableListOf<WishlistItem>()
    private lateinit var wishlistAdapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- Your new code starts here ---

        // Get UI components
        val itemNameEt = findViewById<EditText>(R.id.itemNameEt)
        val priceEt = findViewById<EditText>(R.id.priceEt)
        val urlEt = findViewById<EditText>(R.id.urlEt)
        val addItemBtn = findViewById<Button>(R.id.addItemBtn)
        val wishlistRv = findViewById<RecyclerView>(R.id.wishlistRv)

        // Set up the RecyclerView
        wishlistAdapter = WishlistAdapter(wishlistItems)
        wishlistRv.adapter = wishlistAdapter
        wishlistRv.layoutManager = LinearLayoutManager(this)

        // Set up the button click listener
        addItemBtn.setOnClickListener {
            val name = itemNameEt.text.toString()
            val price = priceEt.text.toString().toDoubleOrNull()
            val url = urlEt.text.toString()

            if (name.isNotEmpty() && price != null && url.isNotEmpty()) {
                // Create a new item and add it to the list
                val newItem = WishlistItem(name, price, url)
                wishlistItems.add(newItem)

                // Notify the adapter that an item was inserted
                wishlistAdapter.notifyItemInserted(wishlistItems.size - 1)

                // Clear the input fields
                itemNameEt.text.clear()
                priceEt.text.clear()
                urlEt.text.clear()
            }
        }
    }
}
