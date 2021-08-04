package com.shatazone.postit.network.response

import com.google.gson.annotations.SerializedName
import com.shatazone.postit.network.data.Meta

data class Response<out T> (
    @SerializedName("meta")
    val meta: Meta,

    @SerializedName("data")
    val data: T
)