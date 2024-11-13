package com.dashaoao.users.data

import com.dashaoao.api.ApiService
import com.dashaoao.api.utils.EmptyData
import com.dashaoao.api.utils.ResultState
import com.dashaoao.api.utils.ServerError
import com.dashaoao.users.domain.model.User
import com.dashaoao.users.domain.model.toUser
import com.dashaoao.users.domain.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class UsersRepositoryImpl(
    private val service: ApiService
) : UsersRepository {
    override suspend fun getUsers(): Flow<ResultState<List<User>>> {
        return flow {
            emit(ResultState.Loading())
            val res = service.getUsers()
            if (res.isSuccessful) {
                val users = res.body()
                if (users != null) {
                    emit(ResultState.Success(users.map { it.toUser() }))
                } else {
                    emit(ResultState.Error(EmptyData))
                }
            } else
                emit(ResultState.Error(ServerError))
        }
    }
}
