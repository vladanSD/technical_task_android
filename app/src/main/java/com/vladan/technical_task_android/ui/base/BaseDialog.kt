package com.vladan.technical_task_android.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladan.technical_task_android.R
import com.vladan.technical_task_android.ui.theme.primaryColor

@ExperimentalComposeUiApi
@Composable
fun BaseDeleteDialog(
    title: String,
    text: String,
    negativeButtonText: String,
    positiveButtonText: String,
    onClickNegativeButton: () -> Unit,
    onClickPositiveButton: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(onDismissRequest = { onDismissRequest() },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = title, style = MaterialTheme.typography.caption.copy(fontSize = 16.sp, color = Color.Black))
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = text, style = MaterialTheme.typography.body1, textAlign = TextAlign.Center)
            }
        },
        buttons = {
            Row(modifier = Modifier.fillMaxWidth()) {
                BaseTextButton(
                    onClick = { onClickNegativeButton() },
                    text = negativeButtonText,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    style = MaterialTheme.typography.body1.copy(color = Color.Black)
                )
                BaseTextButton(
                    onClick = { onClickPositiveButton() },
                    text = positiveButtonText,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(color = Color.Red),
                    style = MaterialTheme.typography.body1.copy(color = Color.White)
                )
            }
        }
    )
}


@ExperimentalComposeUiApi
@Composable
fun BaseCreateUserDialog(
    onClickNegativeButton: () -> Unit,
    onClickPositiveButton: () -> Unit,
    onDismissRequest: () -> Unit,
    nameValue: String,
    onNameChange: (String) -> Unit,
    emailValue: String,
    onEmailChange: (String) -> Unit,
    nameError: String?,
    emailError: String?
) {
    val focusRequester = remember { FocusRequester() }

    AlertDialog(onDismissRequest = { onDismissRequest() },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = stringResource(R.string.create_user_dialog_title), style = MaterialTheme.typography.caption.copy(fontSize = 16.sp, color = Color.Black))
                Spacer(modifier = Modifier.height(20.dp))
                BaseInputWithError(
                    text = nameValue,
                    label = stringResource(R.string.name_label),
                    onTextChange = { onNameChange(it) },
                    errorMessage = nameError,
                    modifier = Modifier,
                    columnModifier = Modifier,
                    onNextClick = { focusRequester.requestFocus() },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(12.dp))
                BaseInputWithError(
                    text = emailValue,
                    label = stringResource(R.string.email_label),
                    onTextChange = { onEmailChange(it) },
                    modifier = Modifier.focusRequester(focusRequester),
                    errorMessage = emailError,
                    columnModifier = Modifier,
                    onNextClick = {},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                Spacer(modifier = Modifier.height(40.dp))
            }
        },
        buttons = {
            Row(modifier = Modifier.fillMaxWidth()) {
                BaseTextButton(
                    onClick = { onClickNegativeButton() },
                    text = stringResource(id = R.string.cancel),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    style = MaterialTheme.typography.body1.copy(color = Color.Black)
                )
                BaseTextButton(
                    onClick = { onClickPositiveButton() },
                    text = stringResource(R.string.create),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(color = primaryColor),
                    style = MaterialTheme.typography.body1.copy(color = Color.White)
                )
            }
        }
    )
}