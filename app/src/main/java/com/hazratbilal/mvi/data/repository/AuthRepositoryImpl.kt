package com.hazratbilal.mvi.data.repository

import com.hazratbilal.mvi.data.remote.api.AuthApi
import com.hazratbilal.mvi.data.remote.dto.LoginRequest
import com.hazratbilal.mvi.data.remote.dto.LoginResponse
import com.hazratbilal.mvi.data.remote.dto.RegisterRequest
import com.hazratbilal.mvi.data.remote.dto.RegisterResponse
import com.hazratbilal.mvi.utils.Result
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(
        request: LoginRequest
    ): Result<LoginResponse> {

        return try {
            val response = api.login(request)

            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun register(
        request: RegisterRequest
    ): Result<RegisterResponse> {

        return try {
            val response = api.register(request)

            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(response.message())
            }

        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }
}