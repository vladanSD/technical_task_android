package com.vladan.technical_task_android.ui.base

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle

@ExperimentalComposeUiApi
@Composable
fun BaseTextButton(onClick: () -> Unit, text: String, style: TextStyle? = null, modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    TextButton(
        onClick = {
            onClick().also {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
        },
        modifier = modifier
    ) {
        Text(
            text,
            style = style ?: MaterialTheme.typography.body1
        )
    }
}