package mx.marco.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonResponseLimitList(

    @SerializedName("results") var results  : ArrayList<ResultsResponse> = arrayListOf()

): Parcelable


