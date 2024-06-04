package com.example.kotlin_demo.screen

import ErrorResponse
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_demo.ApiClient
import com.example.kotlin_demo.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import LoginRequest
import RegisterRequest
import UserData
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.IOException

@Composable
fun RegisterForm(navController: NavHostController) {
    val context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(painter = painterResource(id = R.drawable.baseline_account_box_24), contentDescription = "Logo")
        Text(text = "Register", fontSize = 50.sp , fontWeight = FontWeight.Bold )
        TextField(value = userName, onValueChange = { userName = it }, label = { Text(text = "UserName") }, modifier = Modifier.padding(10.dp))
        TextField(value = password, onValueChange = { password = it }, label = { Text(text = "Password") }, modifier = Modifier.padding(10.dp))
        TextField(value = email, onValueChange = { email = it }, label = { Text(text = "Email") }, modifier = Modifier.padding(10.dp))
        Button(onClick = {
            Log.d("test", "$userName $password")
            try {
                val user = RegisterRequest(userName, password, email)
                Log.d("test", user.toString())
                ApiClient.apiService.register(user).enqueue(object: Callback<UserData> {
                    override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                        if (response.isSuccessful) {
                          result = "OK"
                        } else {
                            val errorBody = response.errorBody()?.string()
                            if (errorBody != null) {
                                try {
                                    // Attempt to parse the error body as a JSON object
                                    val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                                    result = errorResponse.message
                                } catch (e: JsonSyntaxException) {
                                    // Handle JSON parsing error
                                    e.printStackTrace()
                                    result = "An error occurred while parsing the error response."
                                } catch (e: IOException) {
                                    // Handle IO error
                                    e.printStackTrace()
                                    result = "An error occurred while reading the error response."
                                } catch (e: Exception) {
                                    // Handle any other exceptions
                                    e.printStackTrace()
                                    result = "An unexpected error occurred."
                                }
                            } else {
                                // Handle case where errorBody is null
                                result = "An unknown error occurred."
                            }
                        }
                    }

                    override fun onFailure(call: Call<UserData>, t: Throwable) {
//                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
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
        Text(result)
    }
}