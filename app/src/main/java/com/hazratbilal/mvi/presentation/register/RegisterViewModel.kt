package com.hazratbilal.mvi.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazratbilal.mvi.data.remote.dto.RegisterRequest
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
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterContract.State())
    val state: StateFlow<RegisterContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<RegisterContract.Effect>()
    val effect: SharedFlow<RegisterContract.Effect> = _effect.asSharedFlow()

    fun onIntent(intent: RegisterContract.Intent) {
        when (intent) {
            is RegisterContract.Intent.NameChanged -> {
                updateState {
                    copy(name = intent.name)
                }
            }

            is RegisterContract.Intent.EmailChanged -> {
                updateState {
                    copy(email = intent.email)
                }
            }

            is RegisterContract.Intent.PasswordChanged -> {
                updateState {
                    copy(password = intent.password)
                }
            }

            RegisterContract.Intent.RegisterClicked -> {
                register()
            }
        }
    }

    private fun updateState(reducer: RegisterContract.State.() -> RegisterContract.State) {
        _state.value = _state.value.reducer()
    }

    private fun register() {

        val currentState = _state.value

        if (currentState.name.isBlank()) {
            sendEffect(RegisterContract.Effect.ShowError("Please enter name"))
            return
        }

        if (currentState.email.isBlank()) {
            sendEffect(RegisterContract.Effect.ShowError("Please enter email"))
            return
        }

        if (currentState.password.isBlank()) {
            sendEffect(RegisterContract.Effect.ShowError("Please enter password"))
            return
        }

        viewModelScope.launch {

            updateState {
                copy(isLoading = true)
            }

            when (
                val result = repository.register(
                    RegisterRequest(
                        email = currentState.email,
                        password = currentState.password
                    )
                )
            ) {

                is Result.Success -> {
                    sendEffect(RegisterContract.Effect.NavigateHome)
                }

                is Result.Error -> {
                    sendEffect(RegisterContract.Effect.ShowError(result.message))
                }
            }

            updateState {
                copy(isLoading = false)
            }
        }
    }

    private fun sendEffect(effect: RegisterContract.Effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}