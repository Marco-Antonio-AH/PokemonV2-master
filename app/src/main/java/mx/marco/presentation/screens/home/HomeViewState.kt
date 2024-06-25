package mx.marco.presentation.screens.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import mx.marco.domain.model.network.response.PokemonResponse
import mx.marco.domain.model.network.response.PokemonResponseLimitList
import mx.marco.presentation.viewstate.ViewState

data class Act2ViewState(
    val search: String = "",
    val input2: String = "",
    val input3: String = "",
    val input4: String = "",
    val isSuccess: Boolean = false,
    val isAllSuccess: Boolean = false,
    val isLoading4: Boolean = false,
    val emailError: Int? = null,
    val result: String = "",
    val isLoading: Boolean = false,
    val pokemon: PokemonResponse? = null,
    val pokemon2: PokemonResponseLimitList? = null,
    val listPokemon:List<PokemonMap> = listOf(),
    val filteredListPokemon:List<PokemonMap> = listOf(),
    val pokemonListLimitState : List<ListLimitPokemon>? = listOf()
) : ViewState()
//ListDescription(id = 0, description = "")
@Parcelize
data class ListDescription(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("list")
    val description: String = "",
) : Parcelable

@Parcelize
data class ListLimitPokemon(
    var nombre: String = "",
    ):Parcelable

