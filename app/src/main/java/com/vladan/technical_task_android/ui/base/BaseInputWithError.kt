package com.vladan.technical_task_android.ui.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladan.technical_task_android.ui.theme.outlineColor
import com.vladan.technical_task_android.ui.theme.primaryColor

@ExperimentalComposeUiApi
@Composable
fun BaseInputWithError(
    text: String?,
    label: String,
    onTextChange: (String) -> Unit,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
    modifier: Modifier,
    columnModifier: Modifier,
    onNextClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = columnModifier) {
        OutlinedTextField(
            value = text ?: "",
            label = { Text(label, style = MaterialTheme.typography.subtitle2) },
            onValueChange = onTextChange,
            modifier = modifier,
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(color = primaryColor, fontWeight = FontWeight.Normal, fontSize = 15.sp),
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onNext = { onNextClick() },
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ), colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (errorMessage == null) primaryColor else MaterialTheme.colors.error,
                unfocusedBorderColor = if (errorMessage == null) outlineColor else MaterialTheme.colors.error
            )
        )
        errorMessage?.let { text -> if (text.isNotEmpty()) TextError(message = errorMessage) }
    }
}


@Composable
fun TextError(message: String) {
    Text(
        text = message,
        modifier = Modifier.padding(start = 2.dp, top = 2.dp),
        color = MaterialTheme.colors.error
    )
}