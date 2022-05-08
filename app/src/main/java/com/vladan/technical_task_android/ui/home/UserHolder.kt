package com.vladan.technical_task_android.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.vladan.technical_task_android.model.User

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun UserHolder(user: User, onLongPress: (User) -> Unit, modifier: Modifier) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 20.dp, end = 20.dp),
        shape = RectangleShape,
        elevation = 10.dp,
        onClick = {}
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .combinedClickable(
                    onLongClick = { onLongPress(user) },
                    onClick = {}
                ))
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 15.dp)
            ) {
                Text(text = user.name, style = MaterialTheme.typography.body1, maxLines = 1)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = user.email, style = MaterialTheme.typography.body2, maxLines = 1)
            }
        }
    }
}