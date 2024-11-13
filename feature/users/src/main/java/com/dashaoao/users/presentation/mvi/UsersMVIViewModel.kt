package com.dashaoao.users.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashaoao.api.utils.ResultState
import com.dashaoao.users.domain.model.User
import com.dashaoao.users.domain.usecase.GetUsersUseCase
import com.dashaoao.users.presentation.mvi.UsersIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersMVIViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    var users = MutableStateFlow<ResultState<List<User>>>(ResultState.Loading())
        private set

    init {
        getUsers()
    }

    fun onAction(action: UsersIntent) {
        when (action) {
            is UsersIntent.GetUsers -> getUsers()
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase.invoke().collect {
                users.value = it
            }
        }
    }
}
