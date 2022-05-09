package com.vladan.technical_task_android.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRequest(
    val name: String,
    val email: String,
    val gender: Gender = Gender.MALE,
    val status: UserStatus = UserStatus.INACTIVE
)