package com.vladan.technical_task_android.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vladan.technical_task_android.R

@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@Composable
fun Home(
    vm: HomeViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.home_screen_title)) },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(vm.usermock) {
                UserHolder(user = it, onLongPress = {}, modifier = Modifier)
            }
        }
    }
}