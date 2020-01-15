package com.mykolastefkivskyi.redditclient.data.entities

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("data")
    val data: SubReddit? = null
)
