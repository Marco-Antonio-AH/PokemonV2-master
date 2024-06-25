package mx.marco.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.marco.data.local.entity.PokemonFavoriteEntity

@Database(
    entities = [PokemonFavoriteEntity::class],
    version = 1,
)
abstract class DatabasePokemon: RoomDatabase() {


    abstract fun favoritePokemonDao(): PokemonFavoriteDao

}