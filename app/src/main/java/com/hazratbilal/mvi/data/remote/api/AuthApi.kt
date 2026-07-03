package com.hazratbilal.mvi.data.remote.api

import com.hazratbilal.mvi.data.remote.dto.LoginRequest
import com.hazratbilal.mvi.data.remote.dto.LoginResponse
import com.hazratbilal.mvi.data.remote.dto.RegisterRequest
import com.hazratbilal.mvi.data.remote.dto.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

}