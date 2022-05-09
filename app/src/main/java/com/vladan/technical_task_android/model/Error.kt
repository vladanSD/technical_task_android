package com.vladan.technical_task_android.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Error(
    val errors: List<ErrorField>,
    val message: String?
)

@JsonClass(generateAdapter = true)
data class ErrorField(
    @Json(name = "field")
    val field: String,
    @Json(name = "message")
    val message: String,
)