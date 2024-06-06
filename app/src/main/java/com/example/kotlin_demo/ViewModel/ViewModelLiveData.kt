package com.example.kotlin_demo.ViewModel
import Product
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
class CartMuiableModelView : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    private var _totalAmount by mutableStateOf(0.0)
    val totalAmount: Double get() = _totalAmount

    fun addToCart(product: Product) {
        val existingItem = _cartItems.find { it.product._id == product._id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            _cartItems.add(CartItem(product, 1))
        }
        calculateTotalAmount()
    }

    fun removeFromCart(product: Product) {
        val existingItem = _cartItems.find { it.product._id == product._id }
        if (existingItem != null) {
            _cartItems.remove(existingItem)
        }
        calculateTotalAmount()
    }

    fun updateQuantity(product: Product, quantity: Int) {
        val existingItem = _cartItems.find { it.product._id == product._id }
        if (existingItem != null) {
            if (quantity > 0) {
                existingItem.quantity = quantity
            } else {
                _cartItems.remove(existingItem)
            }
        }
        calculateTotalAmount()
    }

    private fun calculateTotalAmount() {
        _totalAmount = _cartItems.sumOf { it.product.price * it.quantity }
    }
}
