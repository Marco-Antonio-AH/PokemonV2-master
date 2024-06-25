package mx.marco.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_favorite_entity")
data class PokemonFavoriteEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val sprites: String,
    val description: String,
    val abilities: String,
    val stats: String,
    val types: String
)
