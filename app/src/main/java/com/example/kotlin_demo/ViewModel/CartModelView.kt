package com.example.kotlin_demo.ViewModel

import Product
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CartItem(val product: Product, var quantity: Int)
class CartModelView: ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> get() = _cartItems.asStateFlow()

    private val _totalAmount = MutableStateFlow(0.0)
    val totalAmount: StateFlow<Double> get() = _totalAmount.asStateFlow()

    fun addToCart(product: Product) {
        _cartItems.update { currentItems ->
            val existingItem = currentItems.find { it.product._id == product._id }
            if (existingItem != null) {
                currentItems.map {
                    if (it.product._id == product._id) {
                        it.copy(quantity = it.quantity + 1)
                    } else {
                        it
                    }
                }
            } else {
                currentItems + CartItem(product, 1)
            }
        }
        calculateTotalAmount()
    }

    fun removeFromCart(product: Product) {
        _cartItems.update { currentItems ->
            currentItems.filterNot { it.product._id == product._id }
        }
        calculateTotalAmount()
        calculateTotalAmount()
    }

    fun updateQuantity(product: Product, quantity: Int) {
        _cartItems.update { currentItems ->
            if (quantity > 0) {
                currentItems.map {
                    if (it.product._id == product._id) {
                        it.copy(quantity = quantity)
                    } else {
                        it
                    }
                }
            } else {
                currentItems.filterNot { it.product._id == product._id }
            }
        }
        calculateTotalAmount()
    }

    private fun calculateTotalAmount() {
        _totalAmount.value = _cartItems.value.sumOf { it.product.price * it.quantity }
    }

}