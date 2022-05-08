package com.vladan.technical_task_android.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vladan.technical_task_android.R
import com.vladan.technical_task_android.ui.base.BaseProgressBar
import com.vladan.technical_task_android.ui.base.BaseTopBar

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@Composable
fun Home(
    vm: HomeViewModel = hiltViewModel()
) {
    val state = vm.uiState.value

    Scaffold(
        topBar = { BaseTopBar(title = R.string.home_screen_title, actions = { if (state.requestInProgress) BaseProgressBar() }) }) {
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(vertical = 20.dp)) {
            items(state.users, key = { it.id }) {
                UserHolder(user = it, onLongPress = { Log.i("OVDE", it.name) }, modifier = Modifier)
            }
        }
    }
}