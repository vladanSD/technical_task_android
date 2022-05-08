package com.vladan.technical_task_android.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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