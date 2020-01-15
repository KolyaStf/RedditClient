package com.mykolastefkivskyi.redditclient.data.entities

import com.google.gson.annotations.SerializedName

data class SubReddit(
    @SerializedName("subreddit")
    val subReddit: String? = null,
    @SerializedName("author_fullname")
    val authorName: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("created")
    val created: Long? = null,
    @SerializedName("author")
    val authorNickname: String? = null,
    @SerializedName("permalink")
    val postUrl: String? = null,
    @SerializedName("num_comments")
    val commentsCount: Int? = null,
    @SerializedName("ups")
    val rating: Int? = null
)