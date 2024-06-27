package mx.marco.presentation.screens.home

import android.app.Application
import android.content.Context
import android.widget.Toast
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

    private var limit = 10
    private var offset = 0

    init {
        initViewState(Act2ViewState())
        loadPokemon()
    }

    fun onEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.UpdateSearch -> {
                updateSearch(event.newText)
                searchAllPokemon(event.newText)
            }
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
        val listFilter = filtrarPokemon(state.listPokemon, newSearchValue)
        state = state.copy(
            search = newSearchValue,
            filteredListPokemon = listFilter
        )
    }

    fun filtrarPokemon(pokemonList: List<PokemonMap>, terminoBusqueda: String): List<PokemonMap> {
        return pokemonList.filter {
            it.name.contains(terminoBusqueda, ignoreCase = true) ||
                    it.types.any { tipo -> tipo.contains(terminoBusqueda, ignoreCase = true) }
        }
    }


    private fun loadPokemon() {
        viewModelScope.launch {
            when (val response = pokemonLimitListUseCase.invoke(offset = offset, limit = limit)) {
                is Resource.Error -> {
                    state = state.copy(isLoading = false)
                    success(false)
                }
                is Resource.Success -> {
                    response.data?.results?.let { results ->
                        results.forEach { result ->
                            launch {
                                fetchPokemonDetails(result.name)
                            }
                        }
                    }
                    state = state.copy(
                        pokemon2 = response.data
                    )
                    offset += limit
                }
            }
        }
    }

    fun loadMorePokemon(context: Context) {
        if (!state.isLoading) {
            state = state.copy(isLoading = true)
            Toast.makeText(context, "Cargando más Pokémon", Toast.LENGTH_SHORT).show()
            loadPokemon()
        }
    }

    private suspend fun fetchPokemonDetails(name: String) {
        val pokemon = PokemonMap()
        try {
            val pokemonResponse = pokemonUseCase.invoke(name)
            when (pokemonResponse) {
                is Resource.Error -> {
                    _uiEvent.send(PokemonUiEvent.ShowSnackBar(pokemonResponse.message ?: ""))
                }
                is Resource.Success -> {
                    pokemonResponse.data?.let {
                        pokemon.number = it.id
                        pokemon.name = it.name
                        pokemon.stripe = it.sprites.frontDefault
                        pokemon.types = it.types.map { type -> type.type.name }
                        pokemon.typeStats = it.stats.map { stat ->
                            "${stat.stat.name}: ${stat.base_stat}"
                        }
                        pokemon.abilities = it.abilities.map { ability -> ability.ability.name }
                    }
                }
            }
        } catch (e: Exception) {
            println("error: $e")
        }

        try {
            val speciesResponse = pokemonSpeciesUseCase.invoke(name)
            when (speciesResponse) {
                is Resource.Error -> {
                    _uiEvent.send(PokemonUiEvent.ShowSnackBar(speciesResponse.message ?: ""))
                }
                is Resource.Success -> {
                    speciesResponse.data?.let {
                        val pokemonDescription: String = extractEnglishDescription(it)
                        pokemon.description = pokemonDescription
                    }
                }
            }
        } catch (e: Exception) {
            println("error: $e")
        }

        val listPokemon = state.listPokemon.plus(pokemon)
        state = state.copy(listPokemon = listPokemon, isLoading = false)
    }

    private fun searchAllPokemon(query: String) {
        viewModelScope.launch {
            when (val response = pokemonListUseCase.invoke()) {
                is Resource.Error -> {
                    _uiEvent.send(PokemonUiEvent.ShowSnackBar(response.message ?: "Error al buscar Pokémon"))
                }
                is Resource.Success -> {
                    val results = response.data?.results?.filter {
                        it.name.contains(query, ignoreCase = true)
                    } ?: emptyList()

                    val filteredList = results.map { result ->
                        fetchPokemonDetails(result.name)
                        state.listPokemon.find { it.name == result.name }!!
                    }

                    state = state.copy(
                        filteredListPokemon = filteredList
                    )
                }
            }
        }
    }
}
