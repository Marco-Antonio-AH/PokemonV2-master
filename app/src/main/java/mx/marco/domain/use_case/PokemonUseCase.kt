package mx.marco.domain.use_case

import mx.marco.domain.model.network.response.PokemonResponse
import mx.marco.util.Resource


interface PokemonUseCase {
    suspend operator fun invoke(pokemonId: Int): Resource<PokemonResponse>

}