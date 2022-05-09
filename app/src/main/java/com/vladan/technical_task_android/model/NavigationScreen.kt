package com.vladan.technical_task_android.model

sealed class NavigationScreen(val route: String) {
    object HomeScreen : NavigationScreen("home")
}
