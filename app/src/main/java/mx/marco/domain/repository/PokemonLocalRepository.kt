package mx.marco.domain.repository

import kotlinx.coroutines.flow.Flow
import mx.marco.data.local.entity.PokemonFavoriteEntity

interface PokemonLocalRepository {

    suspend fun insertUser(pokemonEntity: PokemonFavoriteEntity)



    fun getFavoritePokemon(id: Int): Flow<PokemonFavoriteEntity>

    fun getAllFavoritePokemon(): Flow<List<PokemonFavoriteEntity>>


    suspend fun updatePokemon(pokemonEntity: PokemonFavoriteEntity)
}