package mx.marco.presentation.screens.favorites

import mx.marco.presentation.screens.home.PokemonMap
import mx.marco.presentation.viewstate.ViewState


data class FavoritiesViewState(
    val input: String = "",
    val isLoginSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val emailError: Int? = null,
    val result: String = "",
    val showDialogError: Boolean = false,
    val listPokemon:List<PokemonMap> = listOf(),
    ) : ViewState()