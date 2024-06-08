package com.example.kotlin_demo.utils

import okhttp3.Interceptor
import okhttp3.Response
class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenManager.getToken()
        val request = chain.request().newBuilder()
            .apply {
                if (token != null) {
                    addHeader("Authorization", "Bearer $token")
                }
            }
            .build()
        return chain.proceed(request)
    }
}