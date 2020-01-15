package com.mykolastefkivskyi.redditclient.data.entities

import com.google.gson.annotations.SerializedName

data class Reddit(
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("data")
    val listing: Listing? = null
)
