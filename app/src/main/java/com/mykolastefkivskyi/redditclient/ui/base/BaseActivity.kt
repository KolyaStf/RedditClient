package com.mykolastefkivskyi.redditclient.ui.base

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.google.android.material.snackbar.Snackbar
import com.mykolastefkivskyi.redditclient.R

import javax.inject.Inject


abstract class BaseActivity<T : BaseContract.BaseActions> : AppCompatActivity(),
    BaseContract.BaseView {

    var actions: T? = null
    @Inject set

    private var disableTouch: Boolean = false

    private lateinit var pbLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actions?.onViewAttached(this)
    }

    override fun setContentView(layoutResID: Int) {
        val mRootView = View.inflate(this, R.layout.activity_base, null)
        super.setContentView(mRootView)
        val baseLayout = mRootView.findViewById<FrameLayout>(R.id.activity_base_layout)
        pbLoading = mRootView.findViewById(R.id.activity_base_progress)
        layoutInflater.inflate(layoutResID, baseLayout)
    }

    override fun onDestroy() {
        actions?.release()
        actions?.onViewDetached()
        super.onDestroy()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        actions?.onViewDestroyed()
        actions = null
    }

    override fun showLoading() {
        disableTouch = true
        pbLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        disableTouch = false
        pbLoading.visibility = View.GONE
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return !disableTouch && super.dispatchTouchEvent(ev)
    }

    override fun showSnackBar(resId: Int) {
        showSnackBar(getString(resId))
    }

    override fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        val subView = snackbar.view
        val textView = subView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        snackbar.show()
    }

    override fun context(): Context {
        return this
    }
}
