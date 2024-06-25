package mx.marco.presentation.screens.home

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import mx.marco.domain.model.network.response.PokemonSpeciesResponse
import mx.marco.domain.use_case.PokemonLimitListUseCase
import mx.marco.domain.use_case.PokemonListUseCase
import mx.marco.domain.use_case.PokemonSpeciesUseCase
import mx.marco.presentation.screens.favorites.PokemonUiEvent
import mx.marco.presentation.viewmodel.BaseViewModel
import mx.marco.util.Resource
import mx.marco.domain.use_case.PokemonUseCase
import javax.inject.Inject
import kotlin.Result.Companion.success

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase,
    private val pokemonSpeciesUseCase: PokemonSpeciesUseCase,
    private val pokemonListUseCase: PokemonListUseCase,
    private val pokemonLimitListUseCase: PokemonLimitListUseCase,
    application: Application,
) : BaseViewModel(application) {

    var state by mutableStateOf(Act2ViewState())
        private set

    private val _uiEvent = Channel<PokemonUiEvent>()


    init {
        initViewState(Act2ViewState())
        fetchPokemonById()
        limitList()
    }


    fun onEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.UpdateSearch -> updateSearch(event.newText)
            else -> {}
        }
    }

    private fun extractEnglishDescription(pokemonSpecies: PokemonSpeciesResponse): String {
        val englishFlavorTextEntry = pokemonSpecies.flavorTextEntries.find { entry ->
            entry.language.name == "es"
        }
        return englishFlavorTextEntry?.flavorText ?: "Descripción no disponible"
    }

    fun updateSearch(newSearchValue: String) {

        val listFilter =  filtrarPokemon(
            state.listPokemon,
            newSearchValue
        )
        state = state.copy(
            search = newSearchValue,
            filteredListPokemon = listFilter
        )


    }

    fun filtrarPokemon(pokemonList: List<PokemonMap>, terminoBusqueda: String): List<PokemonMap> {
        return pokemonList.filter { it.name.contains(terminoBusqueda, ignoreCase = true) || it.types.any { tipo -> tipo.contains(terminoBusqueda, ignoreCase = true) } }
    }


    private fun limitList(){
        viewModelScope.launch {

            when (val response = pokemonLimitListUseCase.invoke()) {
                is Resource.Error -> {
                    state = state.copy(isLoading = false)
                    success(false)
                }
                is Resource.Success -> {
                    response.data?.let { data ->
                        state = state.copy(isLoading = false)
                        val listLimitPokemons = data.results.map { result ->
                            ListLimitPokemon(
                                name = result.name,
                                url = result.url
                            )
                        }
                        state = state.copy(
                            pokemonListLimitState = listLimitPokemons
                        )
                        println("${state.pokemonListLimitState} lista")
                    }
                }
            }
        }
    }

    private fun fetchPokemonById() {
        for (i in 1..1302) {
            var pokemon by mutableStateOf(PokemonMap())
            viewModelScope.launch {
                try {
                    when (val response = pokemonSpeciesUseCase.invoke(i)) {
                        is Resource.Error -> {
                            _uiEvent.send(PokemonUiEvent.ShowSnackBar(response.message ?: ""))
                        }

                        is Resource.Success -> {
                            val pokemonDescription: String = response.data?.let {
                                extractEnglishDescription(it)
                            } ?: ""
                            pokemon.description = pokemonDescription
                        }
                    }

                } catch (e: Exception) {
                    println("error: $e")
                }

                try {
                    when (val response = pokemonUseCase.invoke(i)) {
                        is Resource.Error -> {
                            _uiEvent.send(PokemonUiEvent.ShowSnackBar(response.message ?: ""))
                        }
                        is Resource.Success -> {
                            response.data.also {
                                pokemon.number = it!!.id
                                pokemon.name = it.name
                                pokemon.stripe = it.sprites.frontDefault
                                pokemon.types = it.types.map { type -> type.type.name }
                                pokemon.typeStats = it.stats.map { type ->
                                    "${type.stat.name}: ${type.base_stat}"
                                }
                                pokemon.abilities = it.abilities.map { Abilities-> Abilities.ability.name }
                            }
                            state = state.copy(
                                pokemon = response.data
                            )
                        }
                    }
                } catch (e: Exception) {
                    println("error: $e")
                }
                val listPokemon = state.listPokemon.plus(pokemon)
                state = state.copy(
                    listPokemon = listPokemon
                )

            }

        }
    }


}



