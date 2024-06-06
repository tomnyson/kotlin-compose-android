package com.example.kotlin_demo.screen

import Product
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kotlin_demo.ViewModel.ProductViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale

@Composable
fun ProductScreen(navController: NavHostController) {
    val viewModel: ProductViewModel = viewModel()
    val products by viewModel.products
    val isLoading by viewModel.isLoading
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(products.size) { index ->
                    ProductItem(product = products[index], navController)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val imageUrl = product.images.firstOrNull()

        if (imageUrl != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .scale(Scale.FIT)
                    .build(),
                contentDescription = product.name,
                modifier = Modifier
                    .height(128.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate("product/${product._id}")
            },
        ) {
            Icon(Icons.Filled.ShoppingCart, contentDescription = null)
        }
    }


}

