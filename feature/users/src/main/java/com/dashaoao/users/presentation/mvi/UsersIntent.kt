package com.dashaoao.users.presentation.mvi

sealed class UsersIntent {
    object GetUsers: UsersIntent()
}
