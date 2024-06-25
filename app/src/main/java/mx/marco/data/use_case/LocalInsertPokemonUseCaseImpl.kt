package mx.marco.data.use_case

import mx.marco.data.local.entity.PokemonFavoriteEntity
import mx.marco.domain.repository.PokemonLocalRepository
import mx.marco.domain.use_case.LocalInsertPokemonUseCase

class LocalInsertPokemonUseCaseImpl (
    private val localRepository: PokemonLocalRepository
): LocalInsertPokemonUseCase {
    override suspend fun invoke(
        pokemonFavoriteEntity: PokemonFavoriteEntity
    ) {
        localRepository.insertUser(pokemonFavoriteEntity)
    }
}