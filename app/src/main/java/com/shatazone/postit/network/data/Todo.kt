package com.shatazone.postit.network.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class Todo(
    @SerializedName("id")
    val id: Int,

    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("title")
    val title: String?,

    @SerializedName("due_on")
    val duoOn: Date,

    @SerializedName("status")
    val status: String,
)