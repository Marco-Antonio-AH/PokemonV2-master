package mx.marco.data.use_case


import mx.marco.util.Resource
import mx.marco.domain.model.network.response.PokemonResponse
import mx.marco.domain.model.network.response.PokemonResponseLimitList
import mx.marco.domain.repository.PokemonRepository
import mx.marco.domain.use_case.PokemonLimitListUseCase
import mx.marco.domain.use_case.PokemonUseCase


class PokemonLimitListUseCaseImpl(
    private val pokemonRepository: PokemonRepository
): PokemonLimitListUseCase {

    override suspend fun invoke( offset: Int, limit : Int): Resource<PokemonResponseLimitList> {
        return pokemonRepository.getLimitList( offset,limit)
    }



}