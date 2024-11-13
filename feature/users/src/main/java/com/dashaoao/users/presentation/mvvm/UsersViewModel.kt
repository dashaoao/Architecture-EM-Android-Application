package com.dashaoao.users.presentation.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashaoao.api.utils.ResultState
import com.dashaoao.users.domain.model.User
import com.dashaoao.users.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _users = MutableStateFlow<ResultState<List<User>>>(ResultState.Loading())
    val users: StateFlow<ResultState<List<User>>> = _users

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase.invoke().collect {
                _users.value = it
            }
        }
    }
}
