package com.dashaoao.chat.domain

import com.dashaoao.api.utils.ResultState
import com.dashaoao.chat.domain.model.Message
import com.dashaoao.chat.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getUser(id: String): Flow<ResultState<User>>
    suspend fun getMessages(userId: String): Flow<ResultState<List<Message>>>
    suspend fun sendMessage(userId: String, message: String): Flow<ResultState<Message>>
}
