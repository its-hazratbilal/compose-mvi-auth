package com.hazratbilal.mvi.presentation.login

object LoginContract {

    data class State(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val error: String? = null
    )

    sealed interface Intent {
        data class EmailChanged(val email: String) : Intent
        data class PasswordChanged(val password: String) : Intent
        data object LoginClicked : Intent
    }

    sealed interface Effect {
        data object NavigateHome : Effect
        data class ShowError(val message: String) : Effect
    }
}