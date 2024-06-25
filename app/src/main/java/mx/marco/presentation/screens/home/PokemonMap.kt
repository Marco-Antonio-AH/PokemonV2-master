package mx.marco.presentation.screens.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonMap(
    var name: String = "",
    var number: Int = 0,
    var description: String = "",
    var stripe: String = "",
    var types: List<String> = listOf(),
    var typeStats: List<String> = listOf(),
    var abilities: List<String> = listOf()

) : Parcelable

