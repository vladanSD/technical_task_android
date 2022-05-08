package com.vladan.technical_task_android.ui.home

import com.vladan.technical_task_android.model.User

data class HomeUIState(
    val users: List<User> = emptyList(),
    val requestInProgress: Boolean = false
)