package com.vladan.technical_task_android.ui.home.validations

import com.vladan.technical_task_android.R
import com.vladan.technical_task_android.model.UiString

class ValidateName {

    fun execute(name: String): ValidationResult {
        if (name.isBlank())
            return ValidationResult(
                false,
                UiString.LocalResourceString(R.string.name_blank_error)
            )
        if (name.length < 2) {
            return ValidationResult(
                false,
                UiString.LocalResourceString(R.string.name_valid_error)
            )
        }
        return ValidationResult(true, null)
    }
}