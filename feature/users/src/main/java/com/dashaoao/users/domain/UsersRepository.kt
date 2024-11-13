package com.dashaoao.users.domain

import com.dashaoao.api.utils.ResultState
import com.dashaoao.users.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsers(): Flow<ResultState<List<User>>>
}
