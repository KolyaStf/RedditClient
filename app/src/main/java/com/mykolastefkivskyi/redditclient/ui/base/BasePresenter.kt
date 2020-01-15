package com.mykolastefkivskyi.redditclient.ui.base

import android.content.Context
import android.util.Log
import com.mykolastefkivskyi.redditclient.R
import com.mykolastefkivskyi.redditclient.data.actions.ApiError
import com.mykolastefkivskyi.redditclient.data.entities.ErrorAPIResponse


abstract class BasePresenter<V : BaseContract.BaseView>(appContext: Context) :
    BaseContract.BaseActions {

    protected val TAG = this.javaClass.simpleName

    var appContext: Context? = null
        private set

    protected var view: V? = null

    protected val isViewAttached: Boolean
        get() = view != null

    init {
        this.appContext = appContext.applicationContext
    }

    override fun <View : BaseContract.BaseView> onViewAttached(view: View) {
        Log.d(TAG, "onViewAttached ----> Was Attached")

        this.view = view as V
    }

    override fun onViewDetached() {
        Log.d(TAG, "onViewDetached -----> view was detached")
        view = null
        release()
    }

    override fun onViewDestroyed() {
        Log.d(TAG, "onViewDestroyed ----> view was destroyed")
    }

    override fun release() {
        releaseAppContext()
    }

    protected fun handleApiError(): ApiError {
        return object : ApiError {

            override fun onError(errorAPIResponse: ErrorAPIResponse) {
                if (isViewAttached) {
                    view?.hideLoading()
                    view?.showSnackBar(errorAPIResponse.message.toString())
                }
            }

            override fun onNoConnection() {
                if (isViewAttached) {
                    view?.hideLoading()
                    view?.showSnackBar(R.string.check_internet_connection)
                }
            }

            override fun onError(throwable: Throwable) {
                if (isViewAttached) {
                    view?.hideLoading()
                    view?.showSnackBar(throwable.message!!)
                }
            }
        }
    }

    private fun releaseAppContext() {
        appContext = null
    }
}
