package mx.marco.presentation.screens.home

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import mx.marco.domain.model.network.response.PokemonSpeciesResponse
import mx.marco.domain.use_case.PokemonLimitListUseCase
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
    private val pokemonLimitListUseCase: PokemonLimitListUseCase,
    application: Application,
) : BaseViewModel(application) {

    var state by mutableStateOf(Act2ViewState())
        private set

    private val _uiEvent = Channel<PokemonUiEvent>()

    private var limit = 10
    private var offset = 0
    private var searchJob: Job? = null
    private var fetchDetailsJobs: List<Job>? = null
    private var searchTimeoutJob: Job? = null

    init {
        initViewState(Act2ViewState())
        loadPokemon()
    }

    private fun cancelFetchDetailsJobs() {
        fetchDetailsJobs?.forEach { it.cancel() }
        fetchDetailsJobs = null
    }
    fun onEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.UpdateSearch -> {
                updateSearch(event.newText)
                searchJob?.cancel()
                searchTimeoutJob?.cancel()
                if (event.newText.isEmpty()) {
                    searchTimeoutJob = viewModelScope.launch {
                        delay(4000)
                        clearSearch()
                    }
                } else {
                    searchJob = viewModelScope.launch {
                        delay(300)
                        searchAllPokemon(event.newText)
                    }
                }
            }
        }
    }
    private fun clearSearch() {
        state = state.copy(search = "", filteredListPokemon = emptyList())
    }

    private fun extractEnglishDescription(pokemonSpecies: PokemonSpeciesResponse): String {
        val englishFlavorTextEntry = pokemonSpecies.flavorTextEntries.find { entry ->
            entry.language.name == "es"
        }
        return englishFlavorTextEntry?.flavorText ?: "Descripción no disponible"
    }

    private fun updateSearch(newSearchValue: String) {
        val listFilter = pokemonFilter(state.listPokemon, newSearchValue)
        state = state.copy(
            search = newSearchValue,
            filteredListPokemon = listFilter
        )
    }

    private fun pokemonFilter(pokemonList: List<PokemonMap>, search: String) = pokemonList.filter {
            it.name.contains(search, ignoreCase = true) ||
                    it.types.any { type -> type.contains(search, ignoreCase = true) }
        }



    private fun loadPokemon() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            when (val response = pokemonLimitListUseCase.invoke(offset = offset, limit = limit)) {
                is Resource.Error -> {
                    state = state.copy(isLoading = false)
                    success(false)
                }
                is Resource.Success -> {
                    response.data?.results?.let { results ->
                        fetchDetailsJobs = results.map { result ->
                            launch {
                                fetchPokemonDetails(result.name)
                            }
                        }
                        fetchDetailsJobs?.joinAll()
                        state = state.copy(
                            pokemon2 = response.data
                        )
                        offset += limit
                    }
                }
            }
            state = state.copy(isLoading = false)
        }
    }


    fun loadMorePokemon(context: Context) {
        if (!state.isLoading) {
            Toast.makeText(context, "Cargando más Pokémon", Toast.LENGTH_SHORT).show()
            loadPokemon()
        }
    }

    private suspend fun fetchPokemonDetails(name: String) {
        val pokemon = PokemonMap()
        try {
            when (val pokemonResponse = pokemonUseCase.invoke(name)) {
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
            when (val speciesResponse = pokemonSpeciesUseCase.invoke(name)) {
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
            cancelFetchDetailsJobs()

            when (val response = pokemonLimitListUseCase.invoke(offset = 0, limit = 1300)) {
                is Resource.Error -> {
                    _uiEvent.send(PokemonUiEvent.ShowSnackBar(response.message ?: "Error al buscar Pokémon"))
                }
                is Resource.Success -> {
                    val results = response.data?.results?.filter {
                        it.name.contains(query, ignoreCase = true)
                    } ?: emptyList()


                    fetchDetailsJobs = results.map { result ->
                        viewModelScope.launch {
                            try {
                                val pokemonResponse = pokemonUseCase.invoke(result.name)
                                val speciesResponse = pokemonSpeciesUseCase.invoke(result.name)


                                if (pokemonResponse is Resource.Success && speciesResponse is Resource.Success) {
                                    val pokemon = PokemonMap().apply {
                                        number = pokemonResponse.data!!.id
                                        name = pokemonResponse.data.name
                                        stripe = pokemonResponse.data.sprites.frontDefault
                                        types = pokemonResponse.data.types.map { type -> type.type.name }
                                        typeStats = pokemonResponse.data.stats.map { stat ->
                                            "${stat.stat.name}: ${stat.base_stat}"
                                        }
                                        abilities = pokemonResponse.data.abilities.map { ability -> ability.ability.name }
                                        description = speciesResponse.data?.let {
                                            extractEnglishDescription(
                                                it
                                            )
                                        }.toString()
                                    }


                                    state = state.copy(
                                        filteredListPokemon = state.filteredListPokemon + pokemon
                                    )
                                } else if (pokemonResponse is Resource.Error) {
                                    _uiEvent.send(PokemonUiEvent.ShowSnackBar(pokemonResponse.message ?: ""))
                                } else if (speciesResponse is Resource.Error) {
                                    _uiEvent.send(PokemonUiEvent.ShowSnackBar(speciesResponse.message ?: ""))
                                }
                            } catch (e: Exception) {
                                println("Error fetching details for ${result.name}: $e")
                            }
                        }
                    }


                    fetchDetailsJobs?.joinAll()
                }
            }
        }
    }
}
