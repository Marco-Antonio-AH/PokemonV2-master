package mx.marco.domain.use_case

import kotlinx.coroutines.flow.Flow
import mx.marco.data.local.entity.PokemonFavoriteEntity

interface LocalPokemonGetUseCase {
    operator fun invoke(id: Int): Flow<PokemonFavoriteEntity>
}