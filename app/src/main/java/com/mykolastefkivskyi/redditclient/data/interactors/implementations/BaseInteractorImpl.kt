package com.mykolastefkivskyi.redditclient.data.interactors.implementations

import android.content.Context
import com.google.gson.GsonBuilder
import com.mykolastefkivskyi.redditclient.data.actions.ApiError
import com.mykolastefkivskyi.redditclient.data.actions.Complete
import com.mykolastefkivskyi.redditclient.data.actions.Next
import com.mykolastefkivskyi.redditclient.data.actions.Error
import com.mykolastefkivskyi.redditclient.data.entities.ErrorAPIResponse
import com.mykolastefkivskyi.redditclient.data.interactors.interfaces.BaseInteractor
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.util.ArrayList

open class BaseInteractorImpl(val appContext: Context) : BaseInteractor {

    private val disposableHashMap: HashMap<String, List<Disposable>> = hashMapOf()

    override fun release() {
        for (tag in disposableHashMap.keys) {
            release(tag)
        }
    }

    override fun release(tag: String) {
        val disposables = disposableHashMap[tag]?.toMutableList()
        if (disposables != null) {
            for (disposable in disposables) {
                if (!disposable.isDisposed) {
                    disposable.dispose()
                }
            }
            disposables.clear()
        }
    }

    internal fun <T> createObserver(next: Next<T>, error: Error?): Observer<T> {
        return createObserver(next, error, null, "")
    }

    private fun addDisposable(disposable: Disposable, tag: String) {
        var disposables: MutableList<Disposable>? = disposableHashMap[tag]?.toMutableList()
        if (disposables == null) {
            disposables = ArrayList()
        }
        disposables.add(disposable)
        disposableHashMap[tag] = disposables
    }

    private fun <T> createObserver(
        next: Next<T>?,
        error: Error?,
        complete: Complete?,
        tag: String
    ): Observer<T> {
        return object : Observer<T> {
            override fun onSubscribe(d: Disposable) {
                addDisposable(d, tag)
            }

            override fun onNext(t: T) {
                next?.onNext(t)
            }

            override fun onError(e: Throwable) {
                if (error != null) {
                    if (error is ApiError) {
                        if (e is HttpException) {
                            val gson = GsonBuilder().create()
                            val responseBody = e.response().errorBody()
                            if (responseBody != null) {
                                try {
                                    var errorAPIResponse: ErrorAPIResponse? =
                                        gson.fromJson<ErrorAPIResponse>(
                                            responseBody.string(),
                                            ErrorAPIResponse::class.java
                                        )

                                    if (errorAPIResponse == null) {
                                        errorAPIResponse =
                                            ErrorAPIResponse(e.code(), e.message(), e.message())
                                    }
                                    error.onError(errorAPIResponse)
                                } catch (ex: IOException) {
                                    ex.printStackTrace()
                                    error.onError(ex)
                                }

                            }
                        } else if (e is ConnectException) {
                            error.onNoConnection()
                        } else {
                            error.onError(e)
                        }
                    } else {
                        error.onError(e)
                    }
                }
            }

            override fun onComplete() {
                complete?.onComplete()
            }
        }
    }
}