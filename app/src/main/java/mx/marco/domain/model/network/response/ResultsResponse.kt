package mx.marco.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultsResponse(
    @SerializedName("name")
    val name : String,

    @SerializedName("url")
    val url  : String,
): Parcelable
