package mx.marco.domain.use_case

import kotlinx.coroutines.flow.Flow
import mx.marco.data.local.entity.PokemonFavoriteEntity

interface LocalPokemonGetAllUseCase {
    operator fun invoke(): Flow<List<PokemonFavoriteEntity>>
}