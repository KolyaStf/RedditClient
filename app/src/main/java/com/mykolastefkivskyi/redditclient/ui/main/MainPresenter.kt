package com.mykolastefkivskyi.redditclient.ui.main

import android.content.Context
import com.mykolastefkivskyi.redditclient.data.actions.Next
import com.mykolastefkivskyi.redditclient.data.entities.Post
import com.mykolastefkivskyi.redditclient.data.interactors.interfaces.RedditPostInteractor
import com.mykolastefkivskyi.redditclient.ui.base.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject
internal constructor(appContext: Context) : BasePresenter<MainContract.Views>(appContext),
    MainContract.Actions {

    @Inject
    lateinit var redditPostInteractor: RedditPostInteractor

    private var afterPage: String = ""

    override fun getPosts() {
        if (afterPage == "")
            view?.showLoading()

        redditPostInteractor.getPosts(afterPage, object : Next<Pair<String, List<Post>?>> {
            override fun onNext(data: Pair<String, List<Post>?>) {
                if (isViewAttached) {
                    afterPage = data.first
                    view?.hideLoading()
                    data.second?.let { view?.setPostsPage(it) }
                }
            }
        }, handleApiError())
    }
}