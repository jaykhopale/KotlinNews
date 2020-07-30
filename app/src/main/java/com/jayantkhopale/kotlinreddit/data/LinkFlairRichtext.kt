package com.jayantkhopale.kotlinreddit.data


import com.google.gson.annotations.SerializedName

data class LinkFlairRichtext(
    @SerializedName("e")
    val e: String?,
    @SerializedName("t")
    val t: String?
)