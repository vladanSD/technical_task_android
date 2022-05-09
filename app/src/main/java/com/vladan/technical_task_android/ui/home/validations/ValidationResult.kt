package com.vladan.technical_task_android.ui.home.validations

import com.vladan.technical_task_android.model.UiString

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiString?
)
