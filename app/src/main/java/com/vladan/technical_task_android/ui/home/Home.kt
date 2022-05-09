package com.vladan.technical_task_android.ui.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vladan.technical_task_android.R
import com.vladan.technical_task_android.ui.base.*

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@Composable
fun Home(
    vm: HomeViewModel = hiltViewModel()
) {
    val state = vm.uiState.value
    val context = LocalContext.current

    val deleteDialog: MutableState<Int?> = rememberSaveable { mutableStateOf(null) }
    val createUserDialog: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(context) {
        vm.validationEvents.collect {
            if (it is Validation.Success) {
                createUserDialog.value = false
                vm.onEvent(HomeScreenEvent.CreateUser)
            }
        }
    }

    LaunchedEffect(context) {
        vm.toastEvents.collect {
            Toast.makeText(context, it.asString(context), Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = { BaseTopBar(title = R.string.home_screen_title, actions = { if (state.requestInProgress) BaseProgressBar() }) },
        floatingActionButton = {
            BaseFab {
                vm.onEvent(HomeScreenEvent.ClearTypedNameAndEmail)
                createUserDialog.value = true
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(top = 20.dp, bottom = 80.dp)) {
            items(state.users, key = { it.id }) {
                UserHolder(user = it, onLongPress = { deleteDialog.value = it.id }, modifier = Modifier.animateItemPlacement(animationSpec = tween(durationMillis = 500)))
            }
        }
    }

    deleteDialog.value?.let { userId ->
        BaseDeleteDialog(
            title = stringResource(R.string.dialog_delete_user_title),
            text = stringResource(R.string.delete_user_dialog_text),
            negativeButtonText = stringResource(R.string.cancel),
            positiveButtonText = stringResource(R.string.delete),
            onClickNegativeButton = { deleteDialog.value = null },
            onClickPositiveButton = {
                vm.onEvent(HomeScreenEvent.DeleteUser(userId))
                deleteDialog.value = null
            },
            onDismissRequest = { deleteDialog.value = null })
    }

    if (createUserDialog.value) {
        BaseCreateUserDialog(
            onClickNegativeButton = { createUserDialog.value = false },
            onClickPositiveButton = {
                vm.onEvent(HomeScreenEvent.Validate)
            },
            onDismissRequest = { createUserDialog.value = false },
            nameValue = state.userName,
            onNameChange = { vm.onEvent(HomeScreenEvent.TypeName(it)) },
            emailValue = state.userEmail,
            onEmailChange = { vm.onEvent(HomeScreenEvent.TypeEmail(it)) },
            nameError = state.userNameErrorMessage?.asString(),
            emailError = state.userEmailErrorMessage?.asString()
        )
    }
}