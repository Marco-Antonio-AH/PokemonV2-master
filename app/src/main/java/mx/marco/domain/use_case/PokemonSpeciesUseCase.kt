package mx.marco.domain.use_case


import mx.marco.domain.model.network.response.PokemonResponse
import mx.marco.domain.model.network.response.PokemonSpeciesResponse
import mx.marco.util.Resource


interface PokemonSpeciesUseCase {
    suspend operator fun invoke(pokemonId: String): Resource<PokemonSpeciesResponse>
}