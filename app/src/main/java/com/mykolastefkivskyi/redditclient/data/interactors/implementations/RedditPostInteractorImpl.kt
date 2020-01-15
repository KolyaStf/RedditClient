package com.mykolastefkivskyi.redditclient.data.interactors.implementations

import android.content.Context
import com.mykolastefkivskyi.redditclient.data.actions.ApiError
import com.mykolastefkivskyi.redditclient.data.actions.Next
import com.mykolastefkivskyi.redditclient.data.entities.Post
import com.mykolastefkivskyi.redditclient.data.interactors.interfaces.RedditPostInteractor
import com.mykolastefkivskyi.redditclient.data.usecase.RedditUseCase
import com.mykolastefkivskyi.redditclient.ui.main.MainPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RedditPostInteractorImpl @Inject
internal constructor(appContext: Context)
    : BaseInteractorImpl(appContext), RedditPostInteractor {

    @Inject
    lateinit var redditUseCase: RedditUseCase

    override fun getPosts(
        afterPage: String,
        next: Next<Pair<String, List<Post>?>>,
        apiError: ApiError
    ) {
        redditUseCase.getPosts(afterPage)
            .map {
                var after = it.listing?.after
                if (after == null) {
                    after = ""
                }
                Pair<String, List<Post>?>(after, it.listing?.posts)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(createObserver(next, apiError))
    }

}