package com.mykolastefkivskyi.redditclient.ui.main.adapter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mykolastefkivskyi.redditclient.R
import com.mykolastefkivskyi.redditclient.data.entities.Post
import java.util.*
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class PostListAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val postList = ArrayList<Post>()

    private var isLoaderVisible = false
    private var clickListener: Listener? = null

    interface Listener {
        fun onPostClick(postUrl: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> PostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false),
                clickListener
            )
            else -> LoadViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_NORMAL -> {
                val viewHolder = holder as PostViewHolder
                viewHolder.bind(postList[position])
            }
            VIEW_TYPE_LOADING -> {
                // Do nothing
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == postList.size - 1) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun addLoading() {
        isLoaderVisible = true
        notifyItemInserted(postList.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        val position = postList.size - 1
        postList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setItems(posts: List<Post>) {
        postList.addAll(posts)
        notifyDataSetChanged()
    }

    fun setClickListener(itemClickListener: Listener) {
        this.clickListener = itemClickListener
    }

    internal class PostViewHolder(itemView: View, private val mHolderClickListener: Listener?) :
        ViewHolder(itemView) {

        private val subRedditTv: TextView = itemView.findViewById(R.id.item_post_subreddit_tv)
        private val authorNameTimeTv: TextView = itemView.findViewById(R.id.item_post_author_date_tv)
        private val titleTv: TextView = itemView.findViewById(R.id.item_post_title_tv)
        private val thumbnailIv: ImageView = itemView.findViewById(R.id.item_post_thumbnail_iv)
        private val commentCountTv: TextView = itemView.findViewById(R.id.item_post_comment_count_tv)
        private val ratingTv: TextView = itemView.findViewById(R.id.item_post_rating_tv)

        private lateinit var post: Post

        init {
            itemView.setOnClickListener {
                mHolderClickListener?.onPostClick(post.data?.postUrl!!)
            }
        }

        fun bind(post: Post) {
            this.post = post
            subRedditTv.text = post.data?.subReddit
            titleTv.text = post.data?.title
            commentCountTv.text = post.data?.commentsCount.toString()
            ratingTv.text = post.data?.rating.toString()
            setAuthorAndPostTime()

            Glide
                .with(itemView.context)
                .load(post.data?.thumbnail)
                .apply(
                    RequestOptions.bitmapTransform(RoundedCorners(10))
                        .placeholder(R.drawable.ic_reddit_seeklogo)
                        .error(R.drawable.ic_reddit_seeklogo)
                )
                .into(thumbnailIv)
        }

        private fun setAuthorAndPostTime() {
            val postTime =
                post.data?.created?.let {
                    DateUtils.getRelativeTimeSpanString(it * 1000, System.currentTimeMillis(), 0)
                }

            val result = String.format(
                itemView.context.getString(R.string.author_date_template),
                post.data?.authorNickname,
                postTime
            )

            authorNameTimeTv.text = result
        }
    }

    internal class LoadViewHolder(itemLayoutView: View) : ViewHolder(itemLayoutView)

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_NORMAL = 1
    }

}