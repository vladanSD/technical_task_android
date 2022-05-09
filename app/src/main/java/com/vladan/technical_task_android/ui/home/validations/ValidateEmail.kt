package com.vladan.technical_task_android.ui.home.validations

import android.util.Patterns
import com.vladan.technical_task_android.R
import com.vladan.technical_task_android.model.UiString

class ValidateEmail {

    fun execute(email: String): ValidationResult {
        if (email.isBlank())
            return ValidationResult(
                false,
                UiString.LocalResourceString(R.string.email_blank_error)
            )
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                false,
                UiString.LocalResourceString(R.string.email_valid_error)
            )
        }
        return ValidationResult(true, null)
    }
}