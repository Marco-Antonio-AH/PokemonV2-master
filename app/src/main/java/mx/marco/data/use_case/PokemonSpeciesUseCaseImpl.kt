package mx.marco.data.use_case

import mx.marco.util.Resource
import mx.marco.domain.model.network.response.PokemonResponse
import mx.marco.domain.model.network.response.PokemonSpeciesResponse
import mx.marco.domain.repository.PokemonRepository

import mx.marco.domain.use_case.PokemonSpeciesUseCase
import mx.marco.domain.use_case.PokemonUseCase


class PokemonSpeciesUseCaseImpl(
    private val pokemonRepository: PokemonRepository
): PokemonSpeciesUseCase {

    override suspend fun invoke(pokemonId: String): Resource<PokemonSpeciesResponse> {
        return pokemonRepository.getSpecies(pokemonId)
    }

}