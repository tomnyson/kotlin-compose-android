package com.example.kotlin_demo.screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kotlin_demo.ViewModel.CartItem
import com.example.kotlin_demo.ViewModel.CartViewModel
import com.example.kotlin_demo.ViewModel.ProductCart
import com.example.kotlin_demo.utils.UserManager

@Composable
fun CartScreen(navController: NavHostController, viewCartModel: CartViewModel) {
    val cartItems by remember { mutableStateOf(viewCartModel.cartItems) }
    val totalAmount by remember { mutableStateOf(viewCartModel.totalAmount) }
    val userContext = UserManager(LocalContext.current)
    val currentUser = userContext.getUser()
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Button(onClick = {
                   navController.navigate("product")
                }) {
                    Text("continue shopping")
                }


                Spacer(modifier = Modifier.height(16.dp))
                Text("Xin chào ${currentUser!!.username}")
                CartList(cartItems, viewCartModel)
                TotalAmount(totalAmount)
            }
        }

@Composable
fun CartList(cartItems: List<CartItem>, cartModelView: CartViewModel) {
    LazyColumn {
        items(cartItems) { cartItem ->
            CartItemView(cartItem, cartModelView)
        }
    }
}

@Composable
fun CartItemView(cartItem: CartItem, cartModelView: CartViewModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(cartItem.product.name)
        Text("${cartItem.product.price} x ${cartItem.quantity}")
        Row {
            IconButton(onClick = { cartModelView.updateQuantity(cartItem.product, cartItem.quantity + 1) }) {
                Icon(Icons.Default.Add, contentDescription = "Increase quantity")
            }
            IconButton(onClick = { cartModelView.updateQuantity(cartItem.product, cartItem.quantity - 1) }) {
                Icon(Icons.Default.Clear, contentDescription = "Decrease quantity")
            }
            IconButton(onClick = { cartModelView.removeFromCart(cartItem.product) }) {
                Icon(Icons.Default.Delete, contentDescription = "Remove from cart")
            }
        }
    }
}

@Composable
fun TotalAmount(totalAmount: Double) {
    Text(
        text = "Total: $totalAmount",
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(16.dp)
    )
}



