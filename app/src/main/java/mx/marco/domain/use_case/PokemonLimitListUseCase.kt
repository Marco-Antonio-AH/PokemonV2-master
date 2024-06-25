package mx.marco.domain.use_case


import mx.marco.domain.model.network.response.PokemonResponseLimitList
import mx.marco.util.Resource

interface PokemonLimitListUseCase {
    suspend operator fun invoke( offset: Int, limit : Int): Resource<PokemonResponseLimitList>
}