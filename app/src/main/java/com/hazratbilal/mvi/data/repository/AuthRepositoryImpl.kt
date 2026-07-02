package com.hazratbilal.mvi.data.repository

import com.hazratbilal.mvi.data.remote.AuthApi
import com.hazratbilal.mvi.data.remote.LoginRequest
import com.hazratbilal.mvi.data.remote.LoginResponse
import com.hazratbilal.mvi.data.remote.NetworkResult
import com.hazratbilal.mvi.data.remote.RegisterRequest
import com.hazratbilal.mvi.data.remote.RegisterResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(
        request: LoginRequest
    ): NetworkResult<LoginResponse> {

        return try {
            val response = api.login(request)

            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun register(
        request: RegisterRequest
    ): NetworkResult<RegisterResponse> {

        return try {
            val response = api.register(request)

            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }

        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Unknown error")
        }
    }
}