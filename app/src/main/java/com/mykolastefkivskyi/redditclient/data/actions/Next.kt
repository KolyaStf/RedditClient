package com.mykolastefkivskyi.redditclient.data.actions

interface Next<T> {
    fun onNext(data: T)
}