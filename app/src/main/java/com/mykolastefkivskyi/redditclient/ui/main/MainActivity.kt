package com.mykolastefkivskyi.redditclient.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mykolastefkivskyi.redditclient.R
import com.mykolastefkivskyi.redditclient.RedditPostApp
import com.mykolastefkivskyi.redditclient.data.entities.Post
import com.mykolastefkivskyi.redditclient.data.network.RestApiService
import com.mykolastefkivskyi.redditclient.ui.base.BaseActivity
import com.mykolastefkivskyi.redditclient.ui.main.adapter.PaginationScrollListener
import com.mykolastefkivskyi.redditclient.ui.main.adapter.PostListAdapter


class MainActivity : BaseActivity<MainContract.Actions>(), MainContract.Views,
    PostListAdapter.Listener {

    private lateinit var postsRv: RecyclerView
    private lateinit var postsAdapter: PostListAdapter
    private var currentPage = PAGE_START
    private var isLastPage = false
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as RedditPostApp).mainComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPostRv()
        actions?.getPosts()
    }

    override fun onDestroy() {
        (application as RedditPostApp).releaseMainComponent()
        super.onDestroy()
    }

    private fun initPostRv() {
        postsRv = findViewById(R.id.main_activity_posts_rv)
        val layoutManager = LinearLayoutManager(this)
        postsRv.layoutManager = layoutManager
        postsAdapter = PostListAdapter()
        postsAdapter.setClickListener(this)
        postsRv.adapter = postsAdapter
        postsRv.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage++
                // mocking network delay for API call
                Handler().postDelayed({ actions?.getPosts() }, 1000)
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })
    }

    override fun setPostsPage(posts: List<Post>) {
        isLoading = false
        postsAdapter.setItems(posts)
        if (currentPage != TOTAL_PAGES) {
            postsAdapter.addLoading()
        } else {
            isLastPage = true
            postsAdapter.removeLoading()
        }
    }

    override fun onPostClick(postUrl: String) {
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        val customTabsIntent: CustomTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(RestApiService.BASE_URL + postUrl))
    }

    companion object {
        @JvmStatic
        fun start(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }

        const val PAGE_START = 1
        const val TOTAL_PAGES = 5
    }
}
