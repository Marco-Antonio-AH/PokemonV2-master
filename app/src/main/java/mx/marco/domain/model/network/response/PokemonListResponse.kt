package mx.marco.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonListResponse(
    @SerializedName("results")
    val results: List<NamedAPIResource>
) : Parcelable

@Parcelize
data class NamedAPIResource(
    @SerializedName("name" )
    val name : String
) : Parcelable