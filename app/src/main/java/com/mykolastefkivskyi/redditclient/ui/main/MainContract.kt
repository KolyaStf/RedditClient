package com.mykolastefkivskyi.redditclient.ui.main

import com.mykolastefkivskyi.redditclient.data.entities.Post
import com.mykolastefkivskyi.redditclient.ui.base.BaseContract

class MainContract : BaseContract() {
    interface Actions : BaseActions {
        fun getPosts()
    }

    interface Views : BaseView {
        fun setPostsPage(posts: List<Post>)
    }
}