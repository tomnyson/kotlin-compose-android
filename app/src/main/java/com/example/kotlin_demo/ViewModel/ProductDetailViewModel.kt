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

class ProductDetailViewModel : ViewModel() {
    private val _product = mutableStateOf<Product?>(null)
    val product: State<Product?> get() = _product
    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    fun fetchDetailProduct(productId: String) {
        viewModelScope.launch {
            try {
                ApiClient.apiService.getProductDetail(productId).enqueue(object: Callback<Product> {
                    override fun onResponse(call: Call<Product>, response: Response<Product>) {
//                        result = if (response.isSuccessful) "Login successful" else "Login failed"
                        if(response.isSuccessful) {
                            Log.d("test", response.body().toString())
                            _product.value = response.body()
                            _isLoading.value = false
                        }else {
                            Log.d("ProductViewModel", "Error: ${response.errorBody()?.string()}")
                        }
                    }

                    override fun onFailure(call: Call<Product>, t: Throwable) {
                    }
                })
            } catch (e: Exception) {
                _isLoading.value = false
                Log.d("test", e.message.toString())
            }
        }
    }
}