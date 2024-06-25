package mx.marco.presentation.screens.favorites

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.marco.data.local.entity.PokemonFavoriteEntity
import mx.marco.domain.use_case.LocalPokemonGetAllUseCase
import mx.marco.presentation.viewmodel.BaseViewModel
import mx.marco_ah.R
import mx.marco.presentation.screens.home.PokemonMap
import javax.inject.Inject

@HiltViewModel
class FavoritiesViewModel @Inject constructor(
    application: Application,
    private val localPokemonGetAllUseCase: LocalPokemonGetAllUseCase,
) : BaseViewModel(application) {

    var state by mutableStateOf(FavoritiesViewState())
        private set

    private val _uiEvent = Channel<PokemonUiEvent>()


    init {
        initViewState(FavoritiesViewState())
        getAllPokemon()
    }

    fun onEvent(event: FavoritiesViewEvent) {
        when (event) {
            is FavoritiesViewEvent.OnEmailChange -> onTextChange(event.newText)
            is FavoritiesViewEvent.UpdateResult -> updateResult(event.newText)
            FavoritiesViewEvent.OnHiddenDialogError -> showDialogLogout(false)
            FavoritiesViewEvent.OnShowDialogError -> showDialogLogout(true)
            is FavoritiesViewEvent.Login -> loginUser()

            else -> {}
        }
    }

    private fun getAllPokemon() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                localPokemonGetAllUseCase.invoke().collect {
                    state = state.copy(listPokemon = favoritePokemonListToMap(it))
                }
            }
        }
    }

    private fun loginUser() {
        val result = validateForm()
        if (!result)
            state = state.copy(
                result = "",
            )
        state = state.copy(
            isLoginSuccess = result,
        )

    }

    private fun onTextChange(newText: String) {
        state = state.copy(
            input = newText
        )
    }

    private fun updateResult(newText: String) {
        state = state.copy(
            result = newText
        )
    }

    private fun validate(input: String): Int? {
        var error: Int? = null
        val num = input.toIntOrNull()

        if (num == null) {
            error = R.string.act1_error_required
        } else if (num < 1 || num > 100) {
            error = R.string.act1_error_format
        }
        return error
    }

    private fun validateForm(): Boolean {
        val error: Int? = validate(state.input)
        state = state.copy(
            emailError = error,
        )
        return error == null
    }

    private fun showDialogLogout(it: Boolean) {
        state = state.copy(
            showDialogError = it
        )
    }

    fun favoritePokemonListToMap(favoriteList: List<PokemonFavoriteEntity>): List<PokemonMap> {
        return favoriteList.map { favorite ->
            PokemonMap(
                name = favorite.name,
                number = favorite.id,
                description = favorite.description, // Debes definir cómo obtener la descripción del Pokémon
                stripe = favorite.sprites, // Define cómo obtener esta información
                types = favorite.types.split("\n"), // Define cómo obtener los tipos del Pokémon
                typeStats = favorite.stats.split("\n"), // Define cómo obtener las estadísticas del Pokémon
                abilities = favorite.abilities.split("\n") // Define cómo obtener las habilidades del Pokémon
            )
        }
    }
}



