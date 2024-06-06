package com.example.kotlin_demo.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kotlin_demo.ViewModel.CartModelView
import com.example.kotlin_demo.ViewModel.CartMuiableModelView
import com.example.kotlin_demo.ViewModel.ProductDetailViewModel
import kotlinx.coroutines.flow.forEach

@Composable
fun CartScreen(navController: NavHostController) {
//    val viewModel: CartModelView = viewModel()
    val viewModel: CartMuiableModelView = viewModel()
    //state flow
//    val cartItems by viewModel.cartItems.collectAsState()
//    val totalAmount by viewModel.totalAmount.collectAsState()
    // state live
    val cartItems = viewModel.cartItems
    val totalAmount = viewModel.totalAmount
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Cart", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))

        cartItems.forEach { cartItem ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = cartItem.product.name, modifier = Modifier.weight(1f))
                Text(text = "Quantity: ${cartItem.quantity}")
                Button(onClick = {
                    viewModel.updateQuantity(
                        cartItem.product,
                        cartItem.quantity + 1
                    )
                }) {
                    Text("+")
                }
                Button(onClick = {
                    viewModel.updateQuantity(
                        cartItem.product,
                        cartItem.quantity - 1
                    )
                }) {
                    Text("-")
                }
                Button(onClick = { viewModel.removeFromCart(cartItem.product) }) {
                    Text("Remove")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Total Amount: $$totalAmount", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("checkout") }) {
            Text("Proceed to Checkout")
        }
    }
}