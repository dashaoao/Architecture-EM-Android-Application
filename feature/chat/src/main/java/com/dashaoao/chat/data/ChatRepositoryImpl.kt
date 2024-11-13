package com.dashaoao.chat.data

import com.dashaoao.api.ApiService
import com.dashaoao.api.utils.EmptyData
import com.dashaoao.api.utils.ResultState
import com.dashaoao.api.utils.ServerError
import com.dashaoao.chat.domain.ChatRepository
import com.dashaoao.chat.domain.model.Message
import com.dashaoao.chat.domain.model.User
import com.dashaoao.chat.domain.model.toChat
import com.dashaoao.chat.domain.model.toMessage
import com.dashaoao.chat.domain.model.toMessageDto
import com.dashaoao.chat.domain.model.toUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ChatRepositoryImpl(
    private val service: ApiService
) : ChatRepository {
    override suspend fun getUser(id: String): Flow<ResultState<User>> {
        return flow {
            emit(ResultState.Loading())
            val res = service.getUser()
            if (res.isSuccessful) {
                val user = res.body()
                if (user != null) {
                    emit(ResultState.Success(user.toUser()))
                } else {
                    emit(ResultState.Error(EmptyData))
                }
            } else
                emit(ResultState.Error(ServerError))
        }
    }

    override suspend fun getMessages(userId: String): Flow<ResultState<List<Message>>> {
        return flow {
            emit(ResultState.Loading())
            val currentUser = service.getCurrentUser().body()
            currentUser?.let {
                val chatState = service.getChat(userId, currentUser.id)
                if (chatState.isSuccessful) {
                    val chat = chatState.body()
                    chat?.let {
                        val res = service.getMessages(chat)
                        if (res.isSuccessful) {
                            val messages = res.body()
                            if (messages != null) {
                                emit(ResultState.Success(messages.map { it.toMessage() }))
                            } else
                                emit(ResultState.Error(EmptyData))
                        } else
                            emit(ResultState.Error(ServerError))
                    }
                } else
                    emit(ResultState.Error(EmptyData))
            }
        }
    }

    override suspend fun sendMessage(userId: String, message: String): Flow<ResultState<Message>> {
        return flow {
            val currentUser = service.getCurrentUser().body()
            currentUser?.let {
                val chatState = service.getChat(userId, currentUser.id)
                if (chatState.isSuccessful) {
                    val chat = chatState.body()
                    chat?.let {
                        val res = service.sendMessage(
                            Message(
                                id = "",
                                chat = chat.toChat(),
                                from = currentUser.toUser(),
                                text = message
                            ).toMessageDto()
                        )
                        if (res.isSuccessful) {
                            val newMessage = res.body()
                            if (newMessage != null) {
                                emit(ResultState.Success(newMessage.toMessage()))
                            } else {
                                emit(ResultState.Error(EmptyData))
                            }
                        } else
                            emit(ResultState.Error(ServerError))
                    }

                } else
                    emit(ResultState.Error(EmptyData))
            }
        }
    }
}
