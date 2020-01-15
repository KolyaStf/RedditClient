package com.mykolastefkivskyi.redditclient.data.actions

import com.mykolastefkivskyi.redditclient.data.entities.ErrorAPIResponse

interface ApiError : Error {
    fun onError(errorAPIResponse: ErrorAPIResponse)

    fun onNoConnection()
}