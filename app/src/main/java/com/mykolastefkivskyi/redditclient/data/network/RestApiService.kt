package com.mykolastefkivskyi.redditclient.data.network

import com.mykolastefkivskyi.redditclient.data.entities.Reddit
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApiService {

    @GET(TOP_POST)
    fun getPosts(
        @Query(QUERY_PAGINATE_AFTER) nextPage: String,
        @Query(QUERY_LIMIT) limit: Int = DEFAULT_PAGE_SIZE
    ): Observable<Reddit>

    companion object {
        const val BASE_URL = "https://www.reddit.com/"
        const val TOP_POST = "top.json"
        const val DEFAULT_PAGE_SIZE = 10
        const val QUERY_PAGINATE_AFTER = "after"
        const val QUERY_LIMIT = "limit"
    }
}