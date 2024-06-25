package mx.marco.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import mx.marco.data.local.entity.PokemonFavoriteEntity

@Dao
interface PokemonFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(userEntity: PokemonFavoriteEntity)


    @Query("SELECT * FROM pokemon_favorite_entity WHERE id = :id")
    fun getFavoritePokemon(id: Int): Flow<PokemonFavoriteEntity>

    @Query("SELECT * from pokemon_favorite_entity")
    fun getAllFavoritePokemon(): Flow<List<PokemonFavoriteEntity>>

    @Update
    fun updatePokemon(userEntity: PokemonFavoriteEntity)
}