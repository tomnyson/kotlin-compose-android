package com.example.kotlin_demo.ViewModel
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
data class ProductCart(val _id: String, val name: String, val price: Double)
data class CartItem(val product: com.example.kotlin_demo.ViewModel.ProductCart, var quantity: Int)

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    private var _totalAmount by mutableStateOf(0.0)
    val totalAmount: Double get() = _totalAmount

    fun addToCart(product: ProductCart?) {
        val existingItem = _cartItems.find { it.product._id == product?._id  }
        if (existingItem != null) {
            if (product != null) {
                updateQuantity(product, existingItem.quantity + 1)
            }
            Log.i("CartMutableModelView", "Item quantity updated ${_cartItems.size}")
        } else {
            product?.let { CartItem(it, 1) }?.let { _cartItems.add(it) }
            Log.i("CartMutableModelView", "New item added")
        }
        printCart()
        calculateTotalAmount()
    }

    fun removeFromCart(product: com.example.kotlin_demo.ViewModel.ProductCart) {
        val existingItem = _cartItems.find { it.product._id == product._id }
        if (existingItem != null) {
            _cartItems.remove(existingItem)
            calculateTotalAmount()
        }
    }

    fun updateQuantity(product: com.example.kotlin_demo.ViewModel.ProductCart, quantity: Int) {
        val updatedItems = _cartItems.map {
            if (it.product._id == product._id) {
                it.copy(quantity = quantity)
            } else {
                it
            }
        }.toList()
        _cartItems.clear()
        _cartItems.addAll(updatedItems)
        calculateTotalAmount()
    }

    private fun calculateTotalAmount() {
        _totalAmount = _cartItems.sumOf { it.product.price * it.quantity }
    }

    private fun printCart() {
        Log.i("CartMutableModelView", "Current cart items:")
        _cartItems.forEach {
            Log.i("CartMutableModelView", "Item: ${it.product.name}, Quantity: ${it.quantity}, Total: ${it.product.price * it.quantity}")
        }
    }
}