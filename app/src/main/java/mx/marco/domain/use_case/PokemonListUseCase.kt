package mx.marco.domain.use_case

import mx.marco.domain.model.network.response.PokemonListResponse
import mx.marco.util.Resource

interface PokemonListUseCase {
    suspend operator fun invoke(): Resource<PokemonListResponse>
}