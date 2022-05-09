package com.vladan.technical_task_android.ui.base

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun BaseTopBar(@StringRes title: Int, actions: @Composable RowScope.() -> Unit = {}) {
    TopAppBar(
        title = { Text(text = stringResource(title)) },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = actions
    )
}

@Composable
fun BaseProgressBar() {
    Box(modifier = Modifier.size(40.dp)) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(26.dp)
                .padding(end = 4.dp)
                .align(Alignment.Center)
        )
    }
}