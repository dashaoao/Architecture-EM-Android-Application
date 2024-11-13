package com.dashaoao.chat.domain.usecase

import com.dashaoao.api.utils.ResultState
import com.dashaoao.chat.domain.ChatRepository
import com.dashaoao.chat.domain.model.Message
import kotlinx.coroutines.flow.Flow

class GetMessagesUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(userId: String): Flow<ResultState<List<Message>>> {
        return repository.getMessages(userId)
    }
}
