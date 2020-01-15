package com.mykolastefkivskyi.redditclient.data.interactors.interfaces

interface BaseInteractor {
    fun release()

    fun release(tag: String)
}