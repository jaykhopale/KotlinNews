package com.jayantkhopale.kotlinreddit.data


import com.google.gson.annotations.SerializedName

data class MediaEmbedX(
    @SerializedName("content")
    val content: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("scrolling")
    val scrolling: Boolean?,
    @SerializedName("width")
    val width: Int?
)