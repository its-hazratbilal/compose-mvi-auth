package com.hazratbilal.mvi.data.repository

import com.hazratbilal.mvi.data.remote.api.AuthApi
import com.hazratbilal.mvi.data.remote.dto.LoginRequest
import com.hazratbilal.mvi.data.remote.dto.LoginResponse
import com.hazratbilal.mvi.data.remote.dto.RegisterRequest
import com.hazratbilal.mvi.data.remote.dto.RegisterResponse
import com.hazratbilal.mvi.utils.Result
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(request: LoginRequest): Result<LoginResponse> =
        safeApiCall { api.login(request) }

    override suspend fun register(request: RegisterRequest): Result<RegisterResponse> =
        safeApiCall { api.register(request) }

    private suspend fun <T> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                Result.Success(body)
            } else {
                val errorMessage = response.errorBody()?.string()?.takeIf { it.isNotBlank() }
                    ?: "Request failed (${response.code()})"
                Result.Error(errorMessage)
            }
        } catch (e: IOException) {
            Result.Error("Network error, please check your connection", e)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error", e)
        }
    }
}