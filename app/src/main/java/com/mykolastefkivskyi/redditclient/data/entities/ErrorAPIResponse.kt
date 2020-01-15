package com.mykolastefkivskyi.redditclient.data.entities

class ErrorAPIResponse(
    var statusCode: Int,
    var error: String?,
    override var message: String?
) : Throwable()
