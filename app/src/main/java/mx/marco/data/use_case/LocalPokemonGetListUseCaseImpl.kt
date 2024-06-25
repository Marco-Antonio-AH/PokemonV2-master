package mx.marco.data.use_case

import kotlinx.coroutines.flow.Flow
import mx.marco.data.local.entity.PokemonFavoriteEntity
import mx.marco.domain.repository.PokemonLocalRepository
import mx.marco.domain.use_case.LocalPokemonGetAllUseCase

class LocalPokemonGetListUseCaseImpl (
    private val localRepository: PokemonLocalRepository
): LocalPokemonGetAllUseCase {
    override fun invoke(): Flow<List<PokemonFavoriteEntity>> {
        return localRepository.getAllFavoritePokemon()
    }
}