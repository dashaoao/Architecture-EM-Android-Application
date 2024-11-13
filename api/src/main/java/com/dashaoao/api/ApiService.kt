package com.dashaoao.api

import com.dashaoao.api.model.ChatDto
import com.dashaoao.api.model.MessageDto
import com.dashaoao.api.model.UserDto
import retrofit2.Response

interface ApiService {
    suspend fun getUsers(): Response<List<UserDto>>
    suspend fun getUser(): Response<UserDto>
    suspend fun getMessages(chat: ChatDto): Response<List<MessageDto>>
    suspend fun sendMessage(messageDTO: MessageDto): Response<MessageDto>
    suspend fun getChat(firstUserId: String, secondUserId: String): Response<ChatDto>
    suspend fun getCurrentUser(): Response<UserDto>
}
