package com.vladan.technical_task_android.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val gender: Gender,
    val status: UserStatus
)

enum class Gender {
    @Json(name = "male")
    MALE,

    @Json(name = "female")
    FEMALE
}

enum class UserStatus {
    @Json(name = "active")
    ACTIVE,

    @Json(name = "inactive")
    INACTIVE
}