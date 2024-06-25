package mx.marco.data.use_case

import kotlinx.coroutines.flow.Flow
import mx.marco.data.local.entity.PokemonFavoriteEntity
import mx.marco.domain.repository.PokemonLocalRepository
import mx.marco.domain.use_case.LocalPokemonGetAllUseCase
import mx.marco.domain.use_case.LocalPokemonGetUseCase

class LocalPokemonGetUseCaseImpl (
    private val localRepository: PokemonLocalRepository
): LocalPokemonGetUseCase {
    override fun invoke(id: Int): Flow<PokemonFavoriteEntity> {
        return localRepository.getFavoritePokemon(id)
    }
}