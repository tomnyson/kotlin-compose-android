package com.example.kotlin_demo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_demo.screen.BottomNavigationBar
import com.example.kotlin_demo.screen.LoginScreen
import com.example.kotlin_demo.screen.ProductDetailScreen
import com.example.kotlin_demo.screen.ProductScreen
import com.example.kotlin_demo.screen.RegisterForm
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kotlin_demo.ViewModel.CartViewModel
import com.example.kotlin_demo.screen.CartScreen

sealed class Screen(val route: String, val icon: Int) {
    object Home : Screen("product", R.drawable.baseline_account_box_24)
    object Login : Screen("login", R.drawable.baseline_account_box_24)
    object Register : Screen("register", R.drawable.baseline_account_box_24)
}

@Composable
fun Route() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("cart") { CartScreen(navController, cartViewModel) }
        composable("register") { RegisterForm(navController) }
        composable("product") { ProductScreen(navController) }
        composable("product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            if(productId !=null)  {
                ProductDetailScreen(navController, productId = productId, cartViewModel)
            }

        }
    }

}

//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    Scaffold(
//        bottomBar = {
//            if (currentRoute == Screen.Home.route) {
//                BottomNavigationBar(navController = navController)
//            }
//        }
//    ) { innerPadding ->
//        NavHost(
//            navController,
//            startDestination = Screen.Home.route,
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            composable(Screen.Home.route) { ProductScreen(navController) }
//            composable(Screen.Login.route) { LoginScreen(navController) }
//            composable(Screen.Register.route) { RegisterForm(navController) }
//        }
//    }
//}
//
//@Composable
//fun BottomNavigationBar(navController: NavHostController) {
//    BottomNavigation {
//        val items = listOf(
//            Screen.Home,
//        )
//        items.forEach { screen ->
//            BottomNavigationItem(
//                icon = { Icon(ImageVector.vectorResource(id = screen.icon), contentDescription = screen.route) },
//                label = { Text(screen.route) },
//                selected = false,
//                onClick = {
//                    navController.navigate(screen.route)
//                    {
//                    popUpTo(navController.graph.startDestinationId)
//                    launchSingleTop = true
//                    }
//                }
//            )
//        }
//    }
//}

