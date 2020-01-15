package com.mykolastefkivskyi.redditclient.data.entities

import com.google.gson.annotations.SerializedName

data class Listing(
    @SerializedName("modhash")
    val modhash: String? = null,
    @SerializedName("children")
    val posts: List<Post>? = null,
    @SerializedName("after")
    val after: String? = null,
    @SerializedName("before")
    val before: Any? = null
)