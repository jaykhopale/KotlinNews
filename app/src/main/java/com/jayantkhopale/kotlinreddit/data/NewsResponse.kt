package com.jayantkhopale.kotlinreddit.data


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("kind")
    val kind: String?
)