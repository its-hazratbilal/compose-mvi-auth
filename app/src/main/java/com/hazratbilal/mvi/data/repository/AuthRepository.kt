package com.hazratbilal.mvi.data.repository

import com.hazratbilal.mvi.data.remote.LoginRequest
import com.hazratbilal.mvi.data.remote.LoginResponse
import com.hazratbilal.mvi.data.remote.NetworkResult
import com.hazratbilal.mvi.data.remote.RegisterRequest
import com.hazratbilal.mvi.data.remote.RegisterResponse

interface AuthRepository {

    suspend fun login(request: LoginRequest): NetworkResult<LoginResponse>

    suspend fun register(request: RegisterRequest): NetworkResult<RegisterResponse>

}