package com.example.kotlin_demo.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlin_demo.ViewModel.ProductDetailViewModel
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.kotlin_demo.ViewModel.CartViewModel
import com.example.kotlin_demo.ViewModel.ProductCart

@Composable
fun ProductDetailScreen(navController: NavHostController, productId: String,
                        viewCartModel: CartViewModel
                        ) {
    val viewModel: ProductDetailViewModel = viewModel()
    val product by viewModel.product
    val isLoading by viewModel.isLoading
    Log.d("test", productId)
    LaunchedEffect(productId) {
        Log.d("test", productId)
        viewModel.fetchDetailProduct(productId)
        Log.d("test", product.toString())
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Product Detail", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Product ID: ${product?._id}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Product Name: ${product?.name}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Product Price: ${product?.price}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
//                    cartViewModel.addToCart(product)
                    val product = product?.let { ProductCart(it._id, product!!.name, product!!.price) }
                    if (product != null) {
                        Log.d("test", "go here")
                        viewCartModel.addToCart(product)
                    }

                }) {
                Text("Add to Cart")
            }

            Button(onClick = {
                navController.navigate("cart")
            }) {
                Text("Go to Cart")
            }
}}}