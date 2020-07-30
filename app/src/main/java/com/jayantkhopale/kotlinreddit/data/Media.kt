package com.jayantkhopale.kotlinreddit.data


import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("oembed")
    val oembed: Oembed,
    @SerializedName("type")
    val type: String
)