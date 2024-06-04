package com.example.kotlin_demo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_demo.screen.LoginScreen
import com.example.kotlin_demo.screen.ProductScreen
import com.example.kotlin_demo.screen.RegisterForm

@Composable
fun Route() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "product") {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterForm(navController) }
        composable("product") { ProductScreen(navController) }
    }
}
