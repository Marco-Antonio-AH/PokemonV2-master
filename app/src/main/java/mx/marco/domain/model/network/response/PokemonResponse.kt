package mx.marco.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String = ""
): Parcelable

@Parcelize
data class PokemonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("order")
    val order:Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: Sprites,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("types")
    val types: List<Types>,
    @SerializedName("stats")
    val stats: List<Type>,
    @SerializedName("abilities")
    val abilities: List<Abilities>
): Parcelable

@Parcelize
data class Types(
    @SerializedName("slot")
    val slot: Int = 0,
    @SerializedName("type")
    val type: NamedAPIResource = NamedAPIResource("name") ,

): Parcelable


@Parcelize
data class Type(
    @SerializedName("stat")
    val stat:  NamedAPIResource = NamedAPIResource("name") ,
    @SerializedName("base_stat")
    val base_stat: Int = 0
): Parcelable

@Parcelize
data class Abilities(
    @SerializedName("ability")
    val ability:  NamedAPIResource = NamedAPIResource("name") ,

): Parcelable

