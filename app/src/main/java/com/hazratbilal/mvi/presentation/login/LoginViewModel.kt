package com.hazratbilal.mvi.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazratbilal.mvi.data.remote.dto.LoginRequest
import com.hazratbilal.mvi.utils.Result
import com.hazratbilal.mvi.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginContract.State())
    val state: StateFlow<LoginContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LoginContract.Effect>()
    val effect: SharedFlow<LoginContract.Effect> = _effect.asSharedFlow()

    fun onIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.EmailChanged -> {
                updateState { copy(email = intent.email) }
            }

            is LoginContract.Intent.PasswordChanged -> {
                updateState { copy(password = intent.password) }
            }

            LoginContract.Intent.LoginClicked -> {
                login()
            }
        }
    }

    private fun updateState(reducer: LoginContract.State.() -> LoginContract.State) {
        _state.value = _state.value.reducer()
    }

    private fun login() {
        val currentState = _state.value

        if (currentState.email.isBlank()) {
            sendEffect(LoginContract.Effect.ShowError("Please enter email"))
            return
        }

        if (currentState.password.isBlank()) {
            sendEffect(LoginContract.Effect.ShowError("Please enter password"))
            return
        }

        viewModelScope.launch {
            updateState { copy(isLoading = true, error = null) }

            when (
                val result = repository.login(
                    LoginRequest(
                        email = currentState.email,
                        password = currentState.password
                    )
                )
            ) {
                is Result.Success -> {
                    sendEffect(LoginContract.Effect.NavigateHome)
                }

                is Result.Error -> {
                    sendEffect(LoginContract.Effect.ShowError(result.message))
                }
            }

            updateState { copy(isLoading = false) }
        }
    }

    private fun sendEffect(effect: LoginContract.Effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}