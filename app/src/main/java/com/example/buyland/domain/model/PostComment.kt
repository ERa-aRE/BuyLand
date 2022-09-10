package com.example.buyland.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class PostComment(
    @SerializedName("Unique_code")val Unique_code :String = "PlatformV199325694,",
    @SerializedName("UserId") val UsersId : String = "1,",
    @SerializedName("ServiserId")val ServiserId : String = "3,",
    @SerializedName("TextNazar_")val TextNazar_ : String,
    @SerializedName("NumRank")val NumRank :String,
    @SerializedName("OrderId")val OrderId : String = "30098"
)
