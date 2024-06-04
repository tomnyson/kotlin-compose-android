package com.example.kotlin_demo.ViewModel
import Product
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.kotlin_demo.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {
    private val _products = mutableStateOf<List<Product>>(emptyList())
    val products: State<List<Product>> get() = _products
    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading
    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                ApiClient.apiService.getProduct().enqueue(object: Callback<List<Product>> {
                    override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
//                        result = if (response.isSuccessful) "Login successful" else "Login failed"
                        if(response.isSuccessful) {
                            _products.value = response.body() ?: emptyList()
                            _isLoading.value = false
                        }else {
                            Log.d("ProductViewModel", "Error: ${response.errorBody()?.string()}")
                        }
                    }

                    override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    }
                })
            } catch (e: Exception) {
                _isLoading.value = false
                Log.d("test", e.message.toString())
            }
        }
    }
}