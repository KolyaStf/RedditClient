package com.mykolastefkivskyi.redditclient.data.usecase

import com.mykolastefkivskyi.redditclient.data.entities.Reddit
import com.mykolastefkivskyi.redditclient.data.network.RestApiService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditUseCase @Inject
internal constructor() {

    @Inject
    lateinit var restApiService: RestApiService

    fun getPosts(afterPage: String): Observable<Reddit> {
        return restApiService.getPosts(afterPage)
    }
}