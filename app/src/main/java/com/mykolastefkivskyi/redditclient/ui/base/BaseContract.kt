package com.mykolastefkivskyi.redditclient.ui.base

import android.content.Context
import android.content.Intent

import androidx.annotation.StringRes

abstract class BaseContract {

    interface BaseActions {
        fun <View : BaseView> onViewAttached(view: View)

        fun onViewDetached()

        fun onViewDestroyed()

        fun release()
    }

    interface BaseView {
        fun showLoading()

        fun hideLoading()

        fun showSnackBar(message: String)

        fun showSnackBar(@StringRes resId: Int)

        fun context(): Context
    }
}
