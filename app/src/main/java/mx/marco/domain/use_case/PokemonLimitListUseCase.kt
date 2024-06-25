package mx.marco.domain.use_case

import mx.marco.domain.model.network.response.PokemonLimitListResponse
import mx.marco.util.Resource

interface PokemonLimitListUseCase {
    suspend operator fun invoke(): Resource<PokemonLimitListResponse>
}