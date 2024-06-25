package mx.marco.presentation.screens.pokemondescription

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.marco.data.local.entity.PokemonFavoriteEntity
import mx.marco.domain.use_case.LocalInsertPokemonUseCase
import mx.marco.presentation.viewmodel.BaseViewModel
import mx.marco.presentation.screens.home.PokemonMap
import javax.inject.Inject

@HiltViewModel
class PokemonDescriptionViewModel @Inject constructor(
    application: Application,
    private var localInsertPokemonUseCase: LocalInsertPokemonUseCase,
    ) : BaseViewModel(application) {

    var state by mutableStateOf(PokemonDescriptionViewState())
        private set

    init {
        initViewState(PokemonDescriptionViewState())
    }

    fun onEvent(event: PokemonDescriptionViewEvent) {
        when (event) {
            is PokemonDescriptionViewEvent.SavePokemon -> savePokemon(event.pokemon)
            else -> {}
        }
    }

    private fun savePokemon(pokemon: PokemonMap) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                localInsertPokemonUseCase.invoke(pokemon.toFavoritePokemonEntity())
            }
        }
    }
    private fun PokemonMap.toFavoritePokemonEntity(): PokemonFavoriteEntity {
        val pokemonAbilitiesString = this.abilities.joinToString("\n")
        val typesString =
            this.types.joinToString("\n ")
        val pokemonStatsString = this.typeStats.joinToString("\n")
        return PokemonFavoriteEntity(
            id = this.number,
            name = this.name,
            sprites = this.stripe,
            description = this.description,
            abilities = pokemonAbilitiesString,
            types = typesString,
            stats = pokemonStatsString
        )
    }
}



