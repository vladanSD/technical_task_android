package com.vladan.technical_task_android.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladan.technical_task_android.model.UiString
import com.vladan.technical_task_android.model.UserRequest
import com.vladan.technical_task_android.repository.UserRepository
import com.vladan.technical_task_android.ui.home.validations.ValidateEmail
import com.vladan.technical_task_android.ui.home.validations.ValidateName
import com.vladan.technical_task_android.util.retrofit_resource.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    @Inject
    lateinit var validateName: ValidateName

    @Inject
    lateinit var validateEmail: ValidateEmail

    private val _uiState = mutableStateOf(HomeUIState())
    val uiState: State<HomeUIState> = _uiState

    private val _userCreationEventChannel = Channel<UserCreation>()
    val userCreationEvents = _userCreationEventChannel.receiveAsFlow()

    private val _toastChannel = Channel<UiString>()
    val toastEvents = _toastChannel.receiveAsFlow()

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.DeleteUser -> {
                deleteUser(event.id)
            }
            is HomeScreenEvent.TypeName -> {
                typeName(event.value)
            }
            is HomeScreenEvent.TypeEmail -> {
                typeEmail(event.value)
            }
            is HomeScreenEvent.ValidateAndCreate -> {
                validate()
            }
            HomeScreenEvent.ClearTypedNameAndEmail -> {
                clearTypedNameAndEmail()
            }
        }
    }

    private fun validate() {
        val nameResult = validateName.execute(_uiState.value.userName)
        val emailResult = validateEmail.execute(_uiState.value.userEmail)

        listOf(nameResult, emailResult).any { !it.successful }.let {
            if (it) {
                _uiState.value = _uiState.value.copy(userNameErrorMessage = nameResult.errorMessage, userEmailErrorMessage = emailResult.errorMessage)
                return
            }
        }

        createUser()
    }

    private fun clearTypedNameAndEmail() {
        _uiState.value = _uiState.value.copy(userName = "", userEmail = "", userNameErrorMessage = null, userEmailErrorMessage = null)
    }

    private fun typeName(value: String) {
        _uiState.value = _uiState.value.copy(userName = value, userNameErrorMessage = null)
    }

    private fun typeEmail(value: String) {
        _uiState.value = _uiState.value.copy(userEmail = value, userEmailErrorMessage = null)
    }

    private fun deleteUser(id: Int) {
        userRepository.deleteUser(id).apply {
            viewModelScope.launch {
                flow.onEach {
                    when (it.status) {
                        Status.SUCCESS -> _uiState.value = _uiState.value.copy(requestInProgress = false)
                        Status.ERROR -> {
                            _uiState.value = _uiState.value.copy(requestInProgress = false)
                            it.error?.message?.let { _toastChannel.send(UiString.ApiResourceString(it)) }
                        }
                        Status.LOADING -> _uiState.value = _uiState.value.copy(requestInProgress = true)
                    }
                }.launchIn(this)
                run()
            }
        }
    }

    private fun createUser() {
        userRepository.createUser(UserRequest(_uiState.value.userName, _uiState.value.userEmail)).apply {
            viewModelScope.launch {
                flow.onEach {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _uiState.value = _uiState.value.copy(requestInProgress = false)
                            _userCreationEventChannel.send(UserCreation.Success)
                        }
                        Status.ERROR -> {
                            //mapping fields with errors
                            var nameError: UiString? = null
                            var emailError: UiString? = null
                            it.error?.let {
                                nameError = it.errors.firstOrNull { it.field == "name" }?.let { UiString.ApiResourceString(it.message) }
                                emailError = it.errors.firstOrNull { it.field == "email" }?.let { UiString.ApiResourceString(it.message) }
                            }

                            //post the results of error mapping
                            _uiState.value = _uiState.value.copy(requestInProgress = false, userNameErrorMessage = nameError, userEmailErrorMessage = emailError)

                            //send toast if there is global error message or if there is no any of mapped values
                            it.error?.message.let { message ->
                                if (message != null) {
                                    _toastChannel.send(UiString.ApiResourceString(message))
                                } else if (!listOf(nameError, emailError).any { it != null }) it.error?.errors?.firstOrNull()
                                    ?.let { _toastChannel.send(UiString.ApiResourceString("${it.field} ${it.message}")) }
                            }
                        }
                        Status.LOADING -> _uiState.value = _uiState.value.copy(requestInProgress = true)
                    }
                }.launchIn(this)
                run()
            }
        }
    }

    init {
        userRepository.users.apply {
            viewModelScope.launch {
                flow.onEach {
                    when (it.status) {
                        Status.SUCCESS -> _uiState.value = _uiState.value.copy(users = it.data ?: emptyList(), requestInProgress = false)
                        Status.ERROR -> {
                            _uiState.value = _uiState.value.copy(requestInProgress = false)
                            it.error?.message?.let { _toastChannel.send(UiString.ApiResourceString(it)) }
                        }
                        Status.LOADING -> _uiState.value = _uiState.value.copy(requestInProgress = true)
                    }
                }.launchIn(this)
                run()
            }
        }
    }
}

sealed class HomeScreenEvent {
    data class DeleteUser(val id: Int) : HomeScreenEvent()
    data class TypeName(val value: String) : HomeScreenEvent()
    data class TypeEmail(val value: String) : HomeScreenEvent()
    object ClearTypedNameAndEmail : HomeScreenEvent()
    object ValidateAndCreate : HomeScreenEvent()
}

sealed class UserCreation {
    object Success : UserCreation()
}

//TODO erori / unit testovi / internet konekcija / animacija za skrol