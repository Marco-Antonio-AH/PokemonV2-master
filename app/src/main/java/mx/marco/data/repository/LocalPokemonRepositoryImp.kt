package mx.marco.data.repository

import kotlinx.coroutines.flow.Flow
import mx.marco.data.local.PokemonFavoriteDao
import mx.marco.data.local.entity.PokemonFavoriteEntity
import mx.marco.domain.repository.PokemonLocalRepository

class LocalPokemonRepositoryImp (
    private val favoritePokemonDao: PokemonFavoriteDao,
): PokemonLocalRepository {
    override suspend fun insertUser(pokemonEntity: PokemonFavoriteEntity) {
        favoritePokemonDao.insertPokemon(pokemonEntity)
    }



    override fun getFavoritePokemon(id: Int): Flow<PokemonFavoriteEntity> {
        return favoritePokemonDao.getFavoritePokemon(id)
    }

    override fun getAllFavoritePokemon(): Flow<List<PokemonFavoriteEntity>> {
        return favoritePokemonDao.getAllFavoritePokemon()
    }

    override suspend fun updatePokemon(pokemonEntity: PokemonFavoriteEntity) {
        favoritePokemonDao.updatePokemon(pokemonEntity)
    }

}