package com.mykolastefkivskyi.redditclient.data.interactors.interfaces

import com.mykolastefkivskyi.redditclient.data.actions.ApiError
import com.mykolastefkivskyi.redditclient.data.actions.Next
import com.mykolastefkivskyi.redditclient.data.entities.Post

interface RedditPostInteractor {
    fun getPosts(afterPage: String, next: Next<Pair<String, List<Post>?>>, apiError: ApiError)
}