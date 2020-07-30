package com.jayantkhopale.kotlinreddit.data


import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("data")
    val articleData: ArticleData,
    @SerializedName("kind")
    val kind: String?
)