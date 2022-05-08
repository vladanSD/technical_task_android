package com.vladan.technical_task_android.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladan.technical_task_android.repository.UserRepository
import com.vladan.technical_task_android.util.retrofit_resource.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {

    private val _uiState = mutableStateOf(HomeUIState())
    val uiState: State<HomeUIState> = _uiState

    init {
        userRepository.users.apply {
            viewModelScope.launch {
                flow.onEach {
                    when (it.status) {
                        Status.SUCCESS -> _uiState.value = _uiState.value.copy(users = it.data ?: emptyList(), requestInProgress = false)
                        Status.ERROR -> _uiState.value = _uiState.value.copy(requestInProgress = false)
                        Status.LOADING -> _uiState.value = _uiState.value.copy(requestInProgress = true)
                    }
                }.launchIn(this)
                run()
            }
        }
    }
}