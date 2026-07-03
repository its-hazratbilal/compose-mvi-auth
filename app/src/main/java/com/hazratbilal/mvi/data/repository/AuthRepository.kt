package com.hazratbilal.mvi.data.repository

import com.hazratbilal.mvi.data.remote.dto.LoginRequest
import com.hazratbilal.mvi.data.remote.dto.LoginResponse
import com.hazratbilal.mvi.data.remote.dto.RegisterRequest
import com.hazratbilal.mvi.data.remote.dto.RegisterResponse
import com.hazratbilal.mvi.utils.Result

interface AuthRepository {

    suspend fun login(request: LoginRequest): Result<LoginResponse>

    suspend fun register(request: RegisterRequest): Result<RegisterResponse>

}