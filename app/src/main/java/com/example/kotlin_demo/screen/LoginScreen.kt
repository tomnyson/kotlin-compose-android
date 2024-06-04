package com.example.kotlin_demo.screen

import LoginRequest
import UserData
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_demo.ui.theme.KotlindemoTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.kotlin_demo.ApiClient
import com.example.kotlin_demo.R

//class LoginScreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            KotlindemoTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val navController = rememberNavController()
////                    LoginForm()
//                    RegisterForm(navController)
//                }
//
//            }
//        }
//    }
//}


@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        
        Image(painter = painterResource(id = R.drawable.baseline_account_box_24), contentDescription = "Logo")
        Text(text = "Login", fontSize = 50.sp , fontWeight = FontWeight.Bold )
        TextField(value = userName, onValueChange = { userName = it }, label = { Text(text = "UserName") }, modifier = Modifier.padding(10.dp))
        TextField(value = password, onValueChange = { password = it }, label = { Text(text = "Password") })
        Button(onClick = {
            Log.d("test", "$userName $password")
            try {
                val user = LoginRequest(userName, password)
                Log.d("test", user.toString())
                ApiClient.apiService.login(user).enqueue(object: Callback<UserData> {
                    override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                        Toast.makeText(context, response.body()?.data?.username, Toast.LENGTH_LONG).show()
//                        result = if (response.isSuccessful) "Login successful" else "Login failed"
                    }

                    override fun onFailure(call: Call<UserData>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            } catch (e: Exception) {
                Log.d("test", e.message.toString())
            }



        }

            ,colors = ButtonDefaults.buttonColors(containerColor = Color.Gray))
        {
            Text(text = "Login")

        }

        Spacer(modifier = Modifier.height(8.dp))
        // Create a clickable text link
//        TextAllowClick(navController)
        Text(result)
    }
}



@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun LoginFormPreview3() {
    val navController = rememberNavController()
    KotlindemoTheme {
        LoginScreen(navController)
    }
}

@Composable
fun  TextAllowClick(navController: NavHostController, title: String, link: String) {
    val annotatedString = buildAnnotatedString {
        pushStringAnnotation(
            tag = "URL",
            annotation = "register"
        )
        withStyle(style = SpanStyle(color = Color.Blue)) {
            append(title)
        }
        pop()
    }

    Text(
        text = annotatedString,
        modifier = Modifier.clickable {
            annotatedString.getStringAnnotations(tag = "URL", start = 0, end = annotatedString.length)
                .firstOrNull()?.let { annotation ->
                    navController.navigate(annotation.item)
                }
        }
    )
}