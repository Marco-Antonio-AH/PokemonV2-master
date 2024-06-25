package mx.marco.data.use_case

import mx.marco.data.local.entity.PokemonFavoriteEntity
import mx.marco.domain.repository.PokemonLocalRepository
import mx.marco.domain.use_case.LocalPokemonUpdateUseCase

class LocalPokemonUpdateUseCaseImpl (
    private val localRepository: PokemonLocalRepository
): LocalPokemonUpdateUseCase {
    override suspend fun invoke(
        pokemonFavoriteEntity: PokemonFavoriteEntity
    ) {
        localRepository.updatePokemon(pokemonFavoriteEntity)
    }
}