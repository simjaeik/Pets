package com.lacuc.pets.util.retrofit

import com.lacuc.pets.util.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AddTokenInterceptor @Inject constructor(private val tokenManager: TokenManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        tokenManager.getToken()?.let {
            requestBuilder.addHeader("authorization", it)
        }

        return chain.proceed(requestBuilder.build())
    }
}