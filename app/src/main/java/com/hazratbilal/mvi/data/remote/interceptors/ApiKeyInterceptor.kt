package com.hazratbilal.mvi.data.remote.interceptors

import com.hazratbilal.mvi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class ApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .addHeader("x-api-key", BuildConfig.REQRES_API_KEY)
            .build()

        return chain.proceed(request)
    }
}