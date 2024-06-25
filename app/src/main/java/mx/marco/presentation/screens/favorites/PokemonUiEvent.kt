package mx.marco.presentation.screens.favorites

sealed class PokemonUiEvent {
    data object OnLoginSuccess : PokemonUiEvent()
    data class ShowSnackBar(val message: String) : PokemonUiEvent()
}