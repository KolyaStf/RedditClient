package com.mykolastefkivskyi.redditclient.data.actions

interface Error {
    fun onError(throwable: Throwable)
}