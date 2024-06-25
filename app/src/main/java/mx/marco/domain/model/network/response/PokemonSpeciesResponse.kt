package mx.marco.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Language(
    @SerializedName("name")
    val name: String
) : Parcelable


@Parcelize
data class FlavorTextEntry(
    @SerializedName("flavor_text")
    val flavorText: String,
    @SerializedName("language")
    val language: Language
): Parcelable

@Parcelize
data class PokemonSpeciesResponse(
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>
): Parcelable

