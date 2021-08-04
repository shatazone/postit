package com.shatazone.postit.network.data

import com.google.gson.annotations.SerializedName

data class Comment (
    @SerializedName("id")
    val id: Int,

    @SerializedName("post_id")
    val postId: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val emain: String,

    @SerializedName("body")
    val body: String,
)