package com.shatazone.postit.network.response

import com.google.gson.annotations.SerializedName
import com.shatazone.postit.network.data.Meta

data class ResponseList<out T> (
    @SerializedName("meta")
    val meta: Meta,

    @SerializedName("data")
    val data: List<T>
)