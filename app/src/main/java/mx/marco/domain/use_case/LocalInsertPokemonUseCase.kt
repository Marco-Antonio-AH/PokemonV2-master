package mx.marco.domain.use_case

import mx.marco.data.local.entity.PokemonFavoriteEntity

interface LocalInsertPokemonUseCase {
    suspend operator fun invoke(
        pokemonFavoriteEntity: PokemonFavoriteEntity
    )
}