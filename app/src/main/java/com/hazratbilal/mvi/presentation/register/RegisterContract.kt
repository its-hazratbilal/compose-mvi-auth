package com.hazratbilal.mvi.presentation.register

interface RegisterContract {

    data class State(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false
    )

    sealed interface Intent {
        data class NameChanged(val name: String) : Intent
        data class EmailChanged(val email: String) : Intent
        data class PasswordChanged(val password: String) : Intent
        data object RegisterClicked : Intent
    }

    sealed interface Effect {
        data object NavigateHome : Effect
        data class ShowError(val message: String) : Effect
    }
}