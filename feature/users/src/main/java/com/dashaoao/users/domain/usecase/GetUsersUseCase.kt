package com.dashaoao.users.domain.usecase

import com.dashaoao.api.utils.ResultState
import com.dashaoao.users.domain.UsersRepository
import com.dashaoao.users.domain.model.User
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(private val repository: UsersRepository) {
    suspend operator fun invoke(): Flow<ResultState<List<User>>> {
        return repository.getUsers()
    }
}
