package com.vladan.technical_task_android.model

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiString {
    data class ApiResourceString(val value: String) : UiString()
    data class LocalResourceString(@StringRes val resource: Int) : UiString()

    @Composable
    fun asString(): String {
        return when (this) {
            is ApiResourceString -> this.value
            is LocalResourceString -> stringResource(id = this.resource)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is ApiResourceString -> this.value
            is LocalResourceString -> context.getString(this.resource)
        }
    }
}