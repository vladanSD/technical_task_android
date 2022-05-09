package com.vladan.technical_task_android.ui.home

import com.vladan.technical_task_android.model.UiString
import com.vladan.technical_task_android.model.User

data class HomeUIState(
    val users: List<User> = emptyList(),
    val userName: String = "",
    val userEmail: String = "",
    val userNameErrorMessage: UiString? = null,
    val userEmailErrorMessage: UiString? = null,
    val requestInProgress: Boolean = false
)