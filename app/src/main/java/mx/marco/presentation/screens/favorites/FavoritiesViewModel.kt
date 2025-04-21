package mx.marco.presentation.screens.favorites

import android.app.Application
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import mx.marco.data.local.entity.PokemonFavoriteEntity
import mx.marco.domain.use_case.LocalPokemonGetAllUseCase
import mx.marco.presentation.screens.home.PokemonMap
import mx.marco.presentation.viewmodel.BaseViewModel
import mx.marco_ah.R
import javax.inject.Inject

@HiltViewModel
class FavoritiesViewModel @Inject constructor(
    application: Application,
    private val localPokemonGetAllUseCase: LocalPokemonGetAllUseCase,
) : BaseViewModel(application) {

    private val _uiState = MutableStateFlow(FavoritiesViewState())
    val uiState: StateFlow<FavoritiesViewState> = _uiState.asStateFlow()

    init {
        initViewState(FavoritiesViewState())
        loadFavorites()
    }

    fun onEvent(event: FavoritiesViewEvent) {
        when (event) {
            is FavoritiesViewEvent.OnEmailChange -> updateInput(event.newText)
            is FavoritiesViewEvent.UpdateResult -> updateResult(event.newText)
            FavoritiesViewEvent.OnHiddenDialogError -> showErrorDialog(false)
            FavoritiesViewEvent.OnShowDialogError -> showErrorDialog(true)
            is FavoritiesViewEvent.Login -> performLogin()
            FavoritiesViewEvent.GetRandomPokemon -> TODO()
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            localPokemonGetAllUseCase()
                .map { favoritePokemonListToMap(it) }
                .flowOn(Dispatchers.IO)
                .collect { list ->
                    _uiState.value = _uiState.value.copy(
                        listPokemon = list
                    )
                }
        }
    }

    private fun performLogin() {
        val isValid = validateForm()
        _uiState.value = _uiState.value.copy(
            emailError = if (isValid) null else _uiState.value.emailError,
            result = if (isValid) _uiState.value.result else "",
            isLoginSuccess = isValid
        )
    }

    private fun updateInput(newText: String) {
        _uiState.value = _uiState.value.copy(
            input = newText
        )
    }

    private fun updateResult(newText: String) {
        _uiState.value = _uiState.value.copy(
            result = newText
        )
    }

    private fun showErrorDialog(show: Boolean) {
        _uiState.value = _uiState.value.copy(
            showDialogError = show
        )
    }

    private fun validate(input: String): Int? {
        val num = input.toIntOrNull() ?: return R.string.act1_error_required
        return if (num in 1..100) null else R.string.act1_error_format
    }

    private fun validateForm(): Boolean {
        val errorRes = validate(_uiState.value.input)
        _uiState.value = _uiState.value.copy(
            emailError = errorRes
        )
        return errorRes == null
    }

    private fun favoritePokemonListToMap(
        favoriteList: List<PokemonFavoriteEntity>
    ): List<PokemonMap> = favoriteList.map { favorite ->
        PokemonMap(
            name = favorite.name,
            number = favorite.id,
            description = favorite.description,
            stripe = favorite.sprites,
            types = favorite.types.split("\n"),
            typeStats = favorite.stats.split("\n"),
            abilities = favorite.abilities.split("\n")
        )
    }
}
