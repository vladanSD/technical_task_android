package com.vladan.technical_task_android.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Error(
    @Json(name = "status_code")
    val statusCode: Int,
    @Json(name = "error_message")
    val errorMessage: String,
)