package mx.marco.data.use_case

import mx.marco.util.Resource
import mx.marco.domain.model.network.response.PokemonResponse
import mx.marco.domain.repository.PokemonRepository
import mx.marco.domain.use_case.PokemonUseCase


class PokemonUseCaseImpl(
    private val pokemonRepository: PokemonRepository
): PokemonUseCase {

    override suspend fun invoke(pokemonId: String): Resource<PokemonResponse> {
        return pokemonRepository.getPokemon(pokemonId)
    }



}