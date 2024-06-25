package mx.marco.data.use_case

import mx.marco.domain.model.network.response.PokemonListResponse
import mx.marco.domain.model.network.response.PokemonSpeciesResponse
import mx.marco.domain.repository.PokemonRepository
import mx.marco.domain.use_case.PokemonListUseCase
import mx.marco.util.Resource


class PokemonListUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : PokemonListUseCase {

    override suspend fun invoke(): Resource<PokemonListResponse> {
        return pokemonRepository.getList()
    }

}
