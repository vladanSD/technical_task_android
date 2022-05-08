package com.vladan.technical_task_android.ui.base

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.vladan.technical_task_android.R
import com.vladan.technical_task_android.ui.theme.primaryColor

@ExperimentalComposeUiApi
@Composable
fun BaseFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        backgroundColor = primaryColor
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_add_24), contentDescription = null, tint = Color.White)
    }
}