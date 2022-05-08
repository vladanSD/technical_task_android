package com.vladan.technical_task_android.ui.home

import androidx.lifecycle.ViewModel
import com.vladan.technical_task_android.model.Gender
import com.vladan.technical_task_android.model.User
import com.vladan.technical_task_android.model.UserStatus

class HomeViewModel : ViewModel() {

    val usermock = listOf(
        User(1, "ime", "email", Gender.FEMALE, UserStatus.ACTIVE),
        User(2, "asdfas", "email", Gender.FEMALE, UserStatus.ACTIVE),
        User(3, "imafsdfasdfe", "email", Gender.FEMALE, UserStatus.ACTIVE),
        User(4, "fasdfas", "email", Gender.FEMALE, UserStatus.ACTIVE),
        User(5, "asdfa", "email", Gender.FEMALE, UserStatus.ACTIVE),
        User(6, "aaaa", "email", Gender.FEMALE, UserStatus.ACTIVE),
    )
}