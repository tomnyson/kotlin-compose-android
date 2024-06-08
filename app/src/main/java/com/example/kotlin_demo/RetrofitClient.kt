package com.example.kotlin_demo

import ApiService
import android.content.Context
import com.example.kotlin_demo.utils.AuthInterceptor
import com.example.kotlin_demo.utils.TokenManager
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object ApiClient {
    private val BASE_URL="https://climbing-grouper-mildly.ngrok-free.app"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService: ApiService = retrofit.create(ApiService::class.java)

}

object ApiClientAuth {
    private const val BASE_URL = "https://climbing-grouper-mildly.ngrok-free.app"

    private lateinit var retrofit: Retrofit

    fun getClient(context: Context): Retrofit {
        val tokenManager = TokenManager(context)
        val authInterceptor = AuthInterceptor(tokenManager)

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        if (!::retrofit.isInitialized) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
        return retrofit
    }

    val apiService: ApiService = ApiClientAuth.retrofit.create(ApiService::class.java)
}